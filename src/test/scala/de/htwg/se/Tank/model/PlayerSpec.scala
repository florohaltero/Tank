package de.htwg.se.Tank.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlayerSpec extends WordSpec with Matchers {
  /*
  private val lp = 100
  "A Player" when { "new" should {
    val player = PlayerFactory.createPlayer1("Flo", Position(0,0))
    player.tank = Tank(player.pos, 100, 10)
    "have a id" in {
      player.id should be (1)
    }
    "have a name"  in {
      player.name should be("Flo")
    }
    "have a Position" in {
      player.pos.x should be (0)
      player.pos.y should be (0)
    }
    "have a nice String representation" in {
      player.toString should be("Player: Flo\nPos(x,y): (0.0,0.0) Life: 100 CannonAngle: 10")
    }

  }}

   */

  "A Player" when { "new" should {
    val j = PlayerFactory
    val player1 = j.createPlayer1("Flo", Position(0,0))
    val player2 = j.createPlayer2("Sascha", Position(0,0))
    "have player1" in {
      j.PLAYER1 should be (1)
      j.PLAYER2 should be (2)
      j.LIFEPOINTS should be (100)
      j.DEFAULT_A should be (30)
    }
    "have player2" in {
      player2.id should be (2)
      player2.name should be ("Sascha")
      player2.pos.x should be (0)
      player2.pos.y should be (0)
    }
    j.Player1("Flo", Position(0,0))
    "have toString" in {
      j.Player1("Flo", Position(0,0)).toString should be (j.Player1("Flo", Position(0,0)).toString)
    }
    j.Player2("Sascha", Position(0,0))
    "have toString2" in {
      j.Player2("Sascha", Position(0,0)).toString should be (j.Player2("Sascha", Position(0,0)).toString)
    }

  }}
}
