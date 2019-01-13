package pokezen.services.pokeapi

import play.api.libs.json._
import play.api.libs.functional.syntax._

import pokezen.{Pokemon, PokemonName, ImageURL, Types, Type, Stats, Stat}


class PokeAPIPokemon(
  name: PokemonName,
  image: ImageURL,
  types: Types,
  baseStats: Stats) extends Pokemon(name, image, types, baseStats)

object PokeAPIPokemon {
  def apply(
      name: PokemonName,
      image: ImageURL,
      types: Types,
      baseStats: Stats): PokeAPIPokemon =
    new PokeAPIPokemon(name, image, types, baseStats)

  implicit val typeReads: Reads[Type] =
    (__ \ "type" \ "name").read[String].map(Type.apply)

  implicit val statReads: Reads[Stat] = (
    (__ \ "stat" \ "name").read[String] and
    (__ \ "base_stat").read[Double]
  )(Stat.apply _)

  implicit val pokeAPIPokemonReads: Reads[PokeAPIPokemon] = (
    (__ \ "name").read[String] and
    (__ \ "sprites" \ "front_default").read[String] and
    (__ \ "types").read[Seq[Type]] and
    (__ \ "stats").read[Seq[Stat]]
  )(
    (name: String, image: String, types: Seq[Type], stats: Seq[Stat]) =>
      PokeAPIPokemon(
        PokemonName(name),
        ImageURL(image),
        Types(types: _*),
        Stats(stats: _*))
  )
}
