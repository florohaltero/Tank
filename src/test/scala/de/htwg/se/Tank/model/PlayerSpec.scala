package de.htwg.se.Tank.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlayerSpec extends WordSpec with Matchers {
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

}
