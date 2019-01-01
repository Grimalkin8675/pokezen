import org.scalacheck._
import org.scalacheck.Prop._

import play.api.libs.json._

import pokezen.{Name, NameOrdering}


object NameSpec extends Properties("Name") {
  property("name") = Name("foo").name == "foo"

  property(" stringifying") =
    Json.toJson(Name("foo")).toString == """"foo""""

  property(" stringifying list") =
    Json.toJson(List(Name("foo"), Name("bar"))).toString ==
      """["foo","bar"]"""
}

object NameOrderingSpec extends Properties("NameOrdering") {
  property("compare(a, b)") =
    NameOrdering.compare(Name("foo"), Name("bar")) > 0

  property("compare(a, b)") =
    NameOrdering.compare(Name("foo"), Name("foo")) == 0

  property("compare(a, b)") =
    NameOrdering.compare(Name("bar"), Name("foo")) < 0
}
