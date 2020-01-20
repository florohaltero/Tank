package de.htwg.se.Tank.model.gameComponentSpec.gameBaseSpec

import de.htwg.se.Tank.model.gameComponent.gameBase
import de.htwg.se.Tank.model.gameComponent.gameBase.{Map,Game, GameInit, SmallMap}
import de.htwg.se.Tank.model.playerComponent.playerBase.{PlayerFactory, Position}
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GameSpec extends WordSpec with Matchers{
  "A Game" when { "new" should {
    val game_0 = Game("Standard", 2,"small", "Flo", "Sascha")
    Map.p1 = PlayerFactory.createPlayer1("Flo",Position(5,0))
    val str = game_0.toString
    "have a nice String Presentatiopn" in {
      str should be (game_0.toString)
    }
    "have partyname" in {
      game_0.partyname should be("Standard")
    }
    "have Map_x" in {
      gameBase.Map.endOfMap._1 should be(new(SmallMap).MAP_X)
    }
    "have Map_y" in {
      gameBase.Map.endOfMap._2 should be(new(SmallMap).MAP_Y)
    }
    "have map" in {
      game_0.mapNum should be (2)
    }
    "have Player 1" in {
      game_0.name1 should be ("Flo")
    }
    "have Player 2" in {
      game_0.name2 should be ("Sascha")
    }
    "have Player1 map" in {
      gameBase.Map.p1.name should be ("Flo")
    }

    "have moveLeft" in {
      game_0.moveLeft()
      gameBase.Map.p1.pos.x should be (gameBase.Map.p1.pos.x)
      gameBase.Map.moves should be (gameBase.Map.moves)
    }
    "have moveRight" in {
      game_0.moveRight()
      gameBase.Map.moves should be (gameBase.Map.moves)
    }
    game_0.moveAngleUp()
    "Angled up" in {
      gameBase.Map.activePlayer.tank.canonAngle should be (gameBase.Map.activePlayer.tank.canonAngle)
    }
    game_0.moveAngleDown()
    "Angled down" in {
      gameBase.Map.activePlayer.tank.canonAngle should be (gameBase.Map.activePlayer.tank.canonAngle)
    }
  }
  }
}
