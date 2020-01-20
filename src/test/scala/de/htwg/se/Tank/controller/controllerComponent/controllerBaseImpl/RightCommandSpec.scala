package de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase
import de.htwg.se.Tank.model.gameComponent.gameBase.Game
import de.htwg.se.Tank.model.playerComponent.playerBase.{PlayerFactory, Position}
import org.scalatest.{Matchers, WordSpec}

class RightCommandSpec extends WordSpec with Matchers {
  "A Command right" when { "new" should {
    val game = Game("Standard", 2,"small", "Flo", "Sasch")
    val controller = new Controller(game)
    val rightCommand = new RightCommand(controller)
    gameBase.Map.p1 = PlayerFactory.createPlayer1("test",Position(5,0))
    val x = gameBase.Map.p1.pos.x

    rightCommand.doStep
    "do Step" in {
      rightCommand.doStep
      gameBase.Map.p1.pos.x should be (x)
    }

    rightCommand.undoStep
    "undo Step" in {
      rightCommand.undoStep
      gameBase.Map.p1.pos.x should be (x)
    }

    rightCommand.redoStep
    "redoStep" in {
      rightCommand.redoStep
      gameBase.Map.p1.pos.x should be (x)
    }
  }}

}

