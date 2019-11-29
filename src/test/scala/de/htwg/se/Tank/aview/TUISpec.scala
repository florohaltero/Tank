package de.htwg.se.Tank.aview
import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.PlayerFactory.Player1
import de.htwg.se.Tank.model.{Game, PlayerFactory, Position}
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TUISpec extends WordSpec with Matchers{

  "A Tank Tui" should {
    val controller = new Controller(Game("Standard", 0, "Flo", "Sascha"))
    val tui = new TUI(controller)
    val player1 = PlayerFactory.createPlayer1("FLo", Position(0,0))
    "move Tank left on input 'a'" in {
      tui.processInputLine("a")
      //controller.game.mapObject.activePlayer.pos.x should be (controller.game.mapObject.activePlayer.pos.x)
      player1.pos.x should be (player1.pos.x)
    }
    "shoot on in input 'f'" in {
      tui.processInputLine("f")
      controller.shoot() should be (controller.shoot())
    }
    "move Tank right on input 'd'" in {
      tui.processInputLine("d")
      //controller.game.mapObject.activePlayer.pos.x should be (controller.game.mapObject.activePlayer.pos.x)
      player1.pos.x should be (player1.pos.x)
    }
    "move Tank Canon Angle on input 'w'" in {
      tui.processInputLine("d")
      player1.tank.canonAngle should be (player1.tank.canonAngle)
    }
    "moce Tank Canon Angle on input 's'" in {
      tui.processInputLine("s")
      player1.tank.canonAngle should be (player1.tank.canonAngle)
    }
  }




}
