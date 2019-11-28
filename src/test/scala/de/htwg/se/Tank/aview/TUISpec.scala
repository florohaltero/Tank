package de.htwg.se.Tank.aview
import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.PlayerFactory.Player1
import de.htwg.se.Tank.model.{Game, PlayerFactory, Position}
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TUISpec extends WordSpec with Matchers{
  /*
  "A Tank Tui" should {
    val controller = new Controller(Game("Standard", 0, "Flo", "Sascha"))
    val tui = new TUI(controller)
    val player1 = PlayerFactory.createPlayer1("FLo", Position(0,0))
    "move Tank left on input 'l'" in {
      tui.processInputLine("l")
      //controller.game.mapObject.activePlayer.pos.x should be (controller.game.mapObject.activePlayer.pos.x)
      player1.pos.x should be (player1.pos.x)
    }
    "shoot on in input 'f'" in {
      tui.processInputLine("f")
      controller.shoot() should be (Unit)
    }
    "move Tank right on input 'r'" in {
      tui.processInputLine("r")
      //controller.game.mapObject.activePlayer.pos.x should be (controller.game.mapObject.activePlayer.pos.x)
      player1.pos.x should be (player1.pos.x)
    }
  }

   */


}
