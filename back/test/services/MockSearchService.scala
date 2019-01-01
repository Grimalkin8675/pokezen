package tests

import scala.concurrent._

import pokezen.Name
import pokezen.controllers.SearcheableService


case class MockSearchService() extends SearcheableService {
  def pokemons(): Future[List[Name]] =
    Future { List(Name("foo"), Name("bar")) }(ExecutionContext.global)
}
