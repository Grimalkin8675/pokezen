package pokezen.models


sealed class VoteEvent(address: String, pokemonName: PokemonName)


case class UpVoted(
  address: String,
  pokemonName: PokemonName) extends VoteEvent(address, pokemonName)


case class DownVoted(
  address: String,
  pokemonName: PokemonName) extends VoteEvent(address, pokemonName)


case class ResetVoted(
  address: String,
  pokemonName: PokemonName) extends VoteEvent(address, pokemonName)
