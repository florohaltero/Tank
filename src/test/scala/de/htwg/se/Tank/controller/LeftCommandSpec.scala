
package de.htwg.se.Tank.controller

import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.Game
import de.htwg.se.Tank.util.Observer
import org.scalatest.{Matchers, WordSpec}

class LeftCommandSpec extends WordSpec with Matchers {
  "A Command left" when { "new" should {
    val game = Game("Standard", 0, "Flo", "Sasch")
    val controller = new Controller(game)
    val leftCommand = new LeftCommand(controller)
    leftCommand.doStep
    "do Step" in {
      controller.game.mapObject.p1.pos.x should be (controller.game.mapObject.p1.pos.x)
    }
    "undo Step" in {
      leftCommand.undoStep should be (leftCommand.redoStep)
    }
  }}

}
