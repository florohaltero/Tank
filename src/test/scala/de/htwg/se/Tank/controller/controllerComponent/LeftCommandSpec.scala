
package de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase
import de.htwg.se.Tank.model.gameComponent.gameBase.{Game, Map}
import de.htwg.se.Tank.model.gameComponent
import de.htwg.se.Tank.util.Observer
import org.scalatest.{Matchers, WordSpec}

class LeftCommandSpec extends WordSpec with Matchers {
  "A Command left" when { "new" should {
    val game = Game("Standard", 0, "Flo", "Sasch")
    val controller = new Controller(game)
    val leftCommand = new LeftCommand(controller)
    leftCommand.doStep
    "do Step" in {
      gameBase.Map.p1.pos.x should be (gameBase.Map.p1.pos.x)
    }
    "undo Step" in {
      leftCommand.undoStep should be (leftCommand.redoStep)
    }
  }}

}
