package pokezen


case class Stats(stats: Stat*) {
  val contains: Stat => Boolean = this.stats.contains
}
