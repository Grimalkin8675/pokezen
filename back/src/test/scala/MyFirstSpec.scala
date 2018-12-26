import org.scalacheck._
import org.scalacheck.Prop._

object MyFirstSpec extends Properties("MyFirst") {
  property(" 2 = 3 (this should fail)") = 2 == 3
}
