package pokezen.models


sealed class VoteEvent(address: String)

case class UpVote(address: String, pokemonName: PokemonName) extends VoteEvent(address)
