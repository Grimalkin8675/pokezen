import org.scalatestplus.play._

import pokezen._
import pokezen.services.pokeapi.PokeAPITypes


class PokeAPITypesSpec extends PlaySpec {
  "PokeAPITypes" must {
    "be instance of Types" in {
      PokeAPITypes(Type("foo"), Type("bar")).isInstanceOf[Types] mustBe true
    }
  }
}
