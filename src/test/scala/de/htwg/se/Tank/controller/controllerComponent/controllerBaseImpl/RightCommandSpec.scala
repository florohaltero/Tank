package de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase
import de.htwg.se.Tank.model.gameComponent.gameBase.Game
import org.scalatest.{Matchers, WordSpec}

class RightCommandSpec extends WordSpec with Matchers {
  "A Command right" when { "new" should {
    val game = Game("Standard", 0,"small", "Flo", "Sasch")
    val controller = new Controller(game)
    val rightCommand = new RightCommand(controller)
    rightCommand.doStep
    "do Step" in {
      gameBase.Map.p1.pos.x should be (gameBase.Map.p1.pos.x)
    }
    "undo Step" in {
      rightCommand.undoStep should be (rightCommand.redoStep)
    }
  }}

}

