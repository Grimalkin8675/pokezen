package tests

import scala.concurrent._
import scala.util._

import pokezen.controllers.Voteable
import pokezen.models._


import javax.inject._


case class MockVotesService() extends Voteable {
  implicit val ec = ExecutionContext.global

  type RemoteAddress = String

  var votes: Map[PokemonName, Map[RemoteAddress, Int]] = Map.empty

  def vote(
    address: RemoteAddress,
    name: PokemonName,
    voteValue: Int): Future[Boolean] = Future {
    votes.get(name) match {
      case Some(previousVotes) => {
        previousVotes.get(address) match {
          case Some(previousVoteValue) =>
            if (previousVoteValue != voteValue) {
              votes += name -> (previousVotes + (address -> voteValue))
              true
            } else false
          case None => {
            votes += name -> (previousVotes + (address -> voteValue))
            true
          }
        }
      }
      case None => {
        votes += name -> Map((address -> voteValue))
        true
      }
    }
  }
}
