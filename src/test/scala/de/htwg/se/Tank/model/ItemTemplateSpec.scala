package de.htwg.se.Tank.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ItemTemplateSpec extends WordSpec with Matchers {
  "An Item" when { "new" should {
    val player = PlayerFactory.createPlayer1("Sasch", Position(1, 1))
    val health = ItemHealth()
    val moves = ItemMoreMoves()
    "have name health" in {
      health.name should be ("Health")
    }
    "have value health" in {
      health.value should be (25)
    }
    "have name moves" in {
      moves.name should be ("More Moves")
    }
    "have value moves" in {
      moves.value should be (2)
    }
    health.useItem(player)
    "used item health" in {
      player.lp should be (100)
    }
    health.msgItem()
    "health item msg" in {
      "Health Item"
    }
    moves.msgItem()
    "moves item msg" in {
      "More Moves Item"
    }

  }}
}
