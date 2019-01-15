package pokezen

import play.api.libs.json._


case class Stats(stats: Stat*) {
  def map[T](f: Stat => T): Seq[T] = this.stats.map(f)

  def contains(statName: String): Boolean =
    this.stats.exists(_.name == statName)

  def statByName(name: String): Option[Stat] = this.stats.find(_.name == name)
}

object Stats {
  implicit val statsWrites: Writes[Stats] =
    Writes(stats => Json.toJson(stats.stats))
}
