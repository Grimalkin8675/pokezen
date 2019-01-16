package pokezen.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent._
import play.api.libs.json._

import pokezen.{Pokemon, ComparedPokemon, PokemonNames, PokemonName, Type}


trait DetaileableService {
  def pokemonByName(name: PokemonName): Future[Pokemon]
  def pokemonsOfType(pokemonType: Type): Future[PokemonNames]
}

@Singleton
case class PokemonDetails @Inject()(
    detailsService: DetaileableService,
    cc: ControllerComponents,
    ec: ExecutionContext) extends AbstractController(cc) {
  implicit val implicitEc = ec

  def pokemon(pokemonName: String): Action[AnyContent] = {
    def comparePokemon(pokemon: Pokemon): Future[ComparedPokemon] = {
      val futuresNames: Seq[Future[PokemonNames]] =
        pokemon.types.map(detailsService.pokemonsOfType)

      val futureNames: Future[Seq[PokemonNames]] =
        Future.sequence(futuresNames)

      futureNames
        .flatMap(getPokemons)
        .map(ComparedPokemon.compare(pokemon, _: _*))
    }

    def getPokemons(names: Seq[PokemonNames]): Future[Seq[Pokemon]] = {
      val uniqNames: Set[PokemonName] =
        names.foldLeft(Set[PokemonName]())(_ ++ _.toSet)

      val futurePokemons: Set[Future[Pokemon]] =
        uniqNames.map(detailsService.pokemonByName)

      Future.sequence(futurePokemons.toSeq)
    }

    Action.async {
      detailsService.pokemonByName(PokemonName(pokemonName))
        .flatMap(comparePokemon)
        .map(comparedPokemeon => Ok(Json.toJson((comparedPokemeon))))
    }
  }
}
