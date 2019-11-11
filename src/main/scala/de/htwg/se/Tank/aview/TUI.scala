package de.htwg.se.Tank.aview

import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.{Game, GameInitializer}
import de.htwg.se.Tank.util.Observer

class TUI(controller: Controller) extends Observer{

  def processInputLine(input: String): Unit ={
    input match {
      case "q" =>
      case "m0" => controller.setGame("Standard", 0, controller.game.player1, controller.game.player2)
      case "m1" => controller.setGame("Standard", 1, controller.game.player1, controller.game.player2)
      case "p" => controller.setGame("Standard", 1, "Player1","Player2")
      case "p1l" => controller.moveLeft(controller.game,controller.game.mapObject.p1)
      case "p1r" => controller.moveRight(controller.game,controller.game.mapObject.p1)
      case "p2l" => controller.moveLeft(controller.game,controller.game.mapObject.p2)
      case "p2r" => controller.moveRight(controller.game,controller.game.mapObject.p2)
    }
  }

  override def update: Unit = println(controller.gametoString)
}
