package pokezen.models

import play.api.libs.json._


case class Stats(stats: Stat*) {
  def map[T](f: Stat => T): Seq[T] = stats.map(f)

  def contains(statName: String): Boolean = stats.exists(_.name == statName)

  def statByName(name: String): Option[Stat] = stats.find(_.name == name)
}

object Stats {
  implicit val statsWrites: Writes[Stats] =
    Writes(stats => Json.toJson(stats.stats))
}
