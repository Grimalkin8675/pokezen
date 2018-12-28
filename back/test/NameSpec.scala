import org.scalacheck._
import org.scalacheck.Prop._

import pokezen.Name

object NameSpec extends Properties("Name") {
  property("name") = Name("foo").name == "foo"
}
