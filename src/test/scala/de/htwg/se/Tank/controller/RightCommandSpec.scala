package de.htwg.se.Tank.controller

import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase
import de.htwg.se.Tank.model.gameComponent.gameBase.{Game, Map}
import de.htwg.se.Tank.model.gameComponent
import de.htwg.se.Tank.util.Observer
import org.scalatest.{Matchers, WordSpec}

class RightCommandSpec extends WordSpec with Matchers {
  "A Command right" when { "new" should {
    val game = Game("Standard", 0, "Flo", "Sasch")
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