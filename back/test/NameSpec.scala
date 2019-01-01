import org.scalacheck._
import org.scalacheck.Prop._

import play.api.libs.json._

import pokezen.Name


object NameSpec extends Properties("Name") {
  property("name") = Name("foo").name == "foo"

  property("compare(a, b)") =
    Name.compare(Name("foo"), Name("bar")) > 0

  property("compare(a, b)") =
    Name.compare(Name("foo"), Name("foo")) == 0

  property("compare(a, b)") =
    Name.compare(Name("bar"), Name("foo")) < 0

  property(" stringifying") =
    Json.toJson(Name("foo")).toString == """"foo""""

  property(" stringifying list") =
    Json.toJson(List(Name("foo"), Name("bar"))).toString ==
      """["foo","bar"]"""
}
