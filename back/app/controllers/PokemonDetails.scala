package pokezen.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent._
import play.api.libs.json._

import pokezen.{Pokemon, ComparedPokemon, PokemonNames, PokemonName, Type}


trait DetaileableService {
  def pokemonByName(name: PokemonName): Future[Option[Pokemon]]
  def pokemonsOfType(pokemonType: Type): Future[Option[PokemonNames]]
}

@Singleton
case class PokemonDetails @Inject()(
    detailsService: DetaileableService,
    cc: ControllerComponents,
    ec: ExecutionContext) extends AbstractController(cc) {
  implicit val implicitEc = ec

  def pokemon(pokemonName: String): Action[AnyContent] = {
    def comparePokemon(pokemon: Pokemon): Future[ComparedPokemon] = {
      val futuresNames: Seq[Future[Option[PokemonNames]]] =
        pokemon.types.map(detailsService.pokemonsOfType)

      val futureNames: Future[Seq[PokemonNames]] =
        Future.sequence(futuresNames).map(_.flatten)

      futureNames
        .flatMap(getPokemons)
        .map(ComparedPokemon.compare(pokemon, _: _*))
    }

    def getPokemons(names: Seq[PokemonNames]): Future[Seq[Pokemon]] = {
      val uniqNames: Set[PokemonName] =
        names.foldLeft(Set[PokemonName]())(_ ++ _.toSet)

      val futurePokemons: Set[Future[Option[Pokemon]]] =
        uniqNames.map(detailsService.pokemonByName)

      Future.sequence(futurePokemons.toSeq).map(_.flatten)
    }

    Action.async {
      detailsService.pokemonByName(PokemonName(pokemonName))
        .flatMap {
          case Some(pokemon) =>
            comparePokemon(pokemon).map(
              comparedPokemeon => Ok(Json.toJson((comparedPokemeon))))
          case None => Future { NotFound }
        }
    }
  }
}
