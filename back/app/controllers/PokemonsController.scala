package pokezen.controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent._
import play.api.libs.json._

import pokezen._


trait PokemonsService {
  def pokemons: Future[Option[PokemonNames]]
  def pokemonByName(name: PokemonName): Future[Option[Pokemon]]
  def pokemonsOfType(pokemonType: Type): Future[Option[PokemonNames]]
}

@Singleton
case class PokemonsController @Inject()(
  pokemonsService: PokemonsService,
  cc: ControllerComponents
)(
  implicit ec: ExecutionContext
) extends AbstractController(cc) {

  def pokemons: Action[AnyContent] = Action.async {
    pokemonsService.pokemons
      .map {
        case Some(names) => Ok(Json.toJson(names))
        case None => NotFound
      }
  }

  private def comparePokemon(pokemon: Pokemon): Future[ComparedPokemon] = {
    val futuresNames: Seq[Future[Option[PokemonNames]]] =
      pokemon.types.map(pokemonsService.pokemonsOfType)

    val futureNames: Future[Seq[PokemonNames]] =
      Future.sequence(futuresNames).map(_.flatten)

    futureNames
      .flatMap(getPokemons)
      .map(ComparedPokemon.compare(pokemon, _: _*))
  }

  private def getPokemons(names: Seq[PokemonNames]): Future[Seq[Pokemon]] = {
    val uniqNames: Set[PokemonName] =
      names.foldLeft(Set[PokemonName]())(_ ++ _.toSet)

    val futurePokemons: Set[Future[Option[Pokemon]]] =
      uniqNames.map(pokemonsService.pokemonByName)

    Future.sequence(futurePokemons.toSeq).map(_.flatten)
  }

  def pokemon(pokemonName: String): Action[AnyContent] = Action.async {
    pokemonsService.pokemonByName(PokemonName(pokemonName))
      .flatMap {
        case Some(pokemon) =>
          comparePokemon(pokemon)
            .map(comparedPokemeon => Ok(Json.toJson((comparedPokemeon))))
        case None => Future { NotFound }
      }
  }
}
