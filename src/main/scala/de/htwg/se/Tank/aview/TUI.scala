package de.htwg.se.Tank.aview

import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.Game
import de.htwg.se.Tank.util.Observer

class TUI(controller: Controller) extends Observer{
  controller.add(this)

  def processInputLine(input: String): Unit ={
    input match {
      case "m0" => controller.setGame("Standard", 0, controller.game.player1, controller.game.player2)
      case "m1" => controller.setGame("Standard", 1, controller.game.player1, controller.game.player2)
      case x => if (x == "p") controller.setGame("Standard", 1, "Player1","Player2")
                  else println("Game konnte nicht erstellt werden")
      case x => if (x == "l") controller.moveLeft() else println("Fehler Eingabe")
      case x => if (x == "r") controller.moveRight() else println("Fehler Eingabe")
    }
  }

  override def update: Boolean = {println(controller.gametoString);true}
}
