package de.htwg.se.Tank.aview
import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.Game
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TUISpec extends WordSpec with Matchers{
  "A Tank Tui" should {
    val controller = new Controller(Game("Standard", 0, "Flo", "Sascha"))
    val tui = new TUI(controller)
    "move Tank left on input 'l'" in {
      tui.processInputLine("l")
      controller.game.mapObject.activePlayer.pos.x should be (controller.game.mapObject.activePlayer.pos.x)
    }
    "move Tank right on input 'r'" in {
      tui.processInputLine("r")
      controller.game.mapObject.activePlayer.pos.x should be (controller.game.mapObject.activePlayer.pos.x)
    }
  }


}
