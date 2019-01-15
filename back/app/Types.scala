package pokezen

import play.api.libs.json._


case class Types(types: Type*) {
  def map[T](f: Type => T): Seq[T] = this.types.map(f)

  def contains(pokeType: Type): Boolean = this.types.contains(pokeType)
}

object Types {
  implicit val typesWrites: Writes[Types] =
    Writes(pokeTypes => Json.toJson(pokeTypes.types))
}
