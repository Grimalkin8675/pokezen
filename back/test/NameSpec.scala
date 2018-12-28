import org.scalacheck._
import org.scalacheck.Prop._

import play.api.libs.json._

import pokezen.Name

object NameSpec extends Properties("Name") {
  property("name") = Name("foo").name == "foo"

  property(" stringifying") =
    Json.toJson(Name("foo")).toString == """"foo""""

  property(" stringifying list") =
    Json.toJson(List(Name("foo"), Name("bar"))).toString ==
      """["foo","bar"]"""
}
