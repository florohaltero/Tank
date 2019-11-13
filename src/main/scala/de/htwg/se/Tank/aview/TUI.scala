package de.htwg.se.Tank.aview

import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.{Game, GameInitializer}
import de.htwg.se.Tank.util.Observer

class TUI(controller: Controller) extends Observer{
  controller.add(this)

  def processInputLine(input: String): Unit ={
    input match {
      case "m0" => controller.setGame("Standard", 0, controller.game.player1, controller.game.player2)
      case "m1" => controller.setGame("Standard", 1, controller.game.player1, controller.game.player2)
      case "p" => controller.setGame("Standard", 1, "Player1","Player2")
      case "l" => controller.moveLeft()
      case "r" => controller.moveRight()
    }
  }

  override def update: Unit = println(controller.gametoString)
}
