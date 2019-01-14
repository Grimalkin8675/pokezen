package pokezen


case class Types(types: Type*) {
  def map[T](f: Type => T): Seq[T] = this.types.map(f)
}
