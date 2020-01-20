package de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase
import de.htwg.se.Tank.model.gameComponent.gameBase.Game
import de.htwg.se.Tank.model.playerComponent.playerBase.{PlayerFactory, Position}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class ChangePlayerCommandSpec extends WordSpec with Matchers {
  "A Command right" when { "new" should {
    val game = Game("Standard", 2,"small", "Flo", "Sasch")
    val controller = new Controller(game)
    controller.changePlayer()

    "change Player" in {
      gameBase.Map.activePlayer = gameBase.Map.p2
    }

  }}
}
