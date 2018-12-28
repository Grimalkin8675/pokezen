package pokezen.modules

import play.api.{Environment, Configuration}
import play.api.inject.Module

import pokezen.controllers.SearcheableService
import pokezen.services.pokeapi.PokeAPIService


class ServicesModule extends Module {
  def bindings(env: Environment, conf: Configuration) = Seq(
    bind[SearcheableService].to[PokeAPIService]
  )
}
