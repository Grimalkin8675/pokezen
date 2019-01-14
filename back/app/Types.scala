package pokezen


case class Types(types: Type*) {
  def map[T](f: Type => T): Seq[T] = this.types.map(f)

  def contains(pokeType: Type): Boolean = this.types.contains(pokeType)
}
