package pokezen.modules

import play.api.{Environment, Configuration}
import play.api.inject.Module

import pokezen.controllers.{PokemonsService, Voteable}
import pokezen.services.pokeapi.PokeAPIService
import pokezen.services.mongo.VotesService


class ServicesModule extends Module {
  def bindings(env: Environment, conf: Configuration) = Seq(
    bind[PokemonsService].to[PokeAPIService],
    bind[Voteable].to[VotesService]
  )
}
