package pokezen


case class Pokemon(name: Name, image: Image, types: Types, stats: Stats) {
  def hasStat(stat: Stat): Boolean = this.stats.contains(stat)
}
