package de.htwg.se.Tank.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GameSpec extends WordSpec with Matchers{
  "A Game" when { "new" should {
    val game_0 = Game("Standard", 0, "Flo", "Sascha")
    "have partyname" in {
      game_0.partyname should be("Standard")
    }
    "have Map_x" in {
      Map.endOfMap._1 should be(30)
    }
    "have Map_y" in {
      Map.endOfMap._2 should be(15)
    }
    "have map" in {
      game_0.mapNum should be (0)
    }
    "have Player 1" in {
      game_0.name1 should be ("Flo")
    }
    "have Player 2" in {
      game_0.name2 should be ("Sascha")
    }
    "have Player1 map" in {
      Map.p1.name should be ("Flo")
    }
    "have a nice String Presentatiopn" in {
      game_0.toString should be(game_0.toString)
    }
    "have moveLeft" in {
      game_0.moveLeft()
      Map.moves should be (1)
    }
    "have moveLeft in Map" in {
      game_0.moveLeft()
      Map.p1.pos.x should be (1)
    }
    "have moveRight" in {
      game_0.moveRight()
      Map.moves should be (0)
    }
    game_0.moveAngleUp()
    "Angled up" in {
      Map.activePlayer.tank.canonAngle should be (30)
    }
    game_0.moveAngleDown()
    "Angled down" in {
      Map.activePlayer.tank.canonAngle should be (30)
    }
  }
  }
}
