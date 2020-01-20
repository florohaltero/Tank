
package de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase
import de.htwg.se.Tank.model.gameComponent.gameBase.Game
import de.htwg.se.Tank.model.playerComponent.playerBase.{PlayerFactory, Position}
import org.scalatest.{Matchers, WordSpec}

class LeftCommandSpec extends WordSpec with Matchers {
  "A Command left" when { "new" should {
    val game = Game("Standard", 2,"small", "Flo", "Sasch")
    val controller = new Controller(game)
    val leftCommand = new LeftCommand(controller)
    gameBase.Map.p1 = PlayerFactory.createPlayer1("test",Position(10,0))
    val x : Double = gameBase.Map.p1.pos.x
    leftCommand.doStep
    "do Step" in {
      leftCommand.doStep
      gameBase.Map.p1.pos.x should be (x)
    }

    leftCommand.undoStep
    "undo Step" in {
      leftCommand.undoStep
      gameBase.Map.p1.pos.x should be (10)
    }

    leftCommand.redoStep
    "redo Step" in {
      leftCommand.redoStep
      gameBase.Map.p1.pos.x should be (10)
    }

  }}

}
