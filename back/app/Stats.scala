package pokezen


case class Stats(stats: Stat*) {
  def map[T](f: Stat => T): Seq[T] = this.stats.map(f)

  def statByName(name: String): Option[Stat] = this.stats.find(_.name == name)
}
