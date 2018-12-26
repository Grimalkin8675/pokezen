import org.scalacheck._
import org.scalacheck.Prop._

import pokezen._


object PokemonSpec extends Properties("Pokemon") {
  property("name exists") =
    Pokemon(
      Name("foo"),
      Image(""),
      Types(),
      Stats()
    ).name.name == "foo"

  property("image exists") =
    Pokemon(
      Name(""),
      Image("foo"),
      Types(),
      Stats()
    ).image.url == "foo"

  property("types exists") =
    Pokemon(
      Name(""),
      Image("foo"),
      Types(Type("type1"), Type("type2")),
      Stats()
    ).image.url == "foo"

  property("hasStat(stat)") = {
    val stat = Stat(Name("attack"), Base(45))
    Pokemon(
      Name(""),
      Image(""),
      Types(),
      Stats(stat)
    ).hasStat(stat)
  }
}
