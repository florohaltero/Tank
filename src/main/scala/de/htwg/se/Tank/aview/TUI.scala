package de.htwg.se.Tank.aview

import de.htwg.se.Tank.controller.{Controller, GameStatus}
import de.htwg.se.Tank.model.Game
import de.htwg.se.Tank.util.Observer

import scala.util.{Failure, Success, Try}

class TUI(controller: Controller) extends Observer{
  controller.add(this)

  def processInputLine(input: String): Unit ={
    Try(input match {
      case "m0" => controller.setGame("Standard", 0, controller.game.name1, controller.game.name2)
      case "m1" => controller.setGame("Standard", 1, controller.game.name1, controller.game.name2)
      case "a" => controller.moveLeft()
      case "d" => controller.moveRight()
      case "w" => controller.moveAngleUp()
      case "s" => controller.moveAngleDown()
      case "p" => controller.changePlayer()
      case "f" => controller.shoot()
      case "z" => controller.undo
      case "y" => controller.redo
      }
    )  match {
      case Success(e) =>
      case Failure(e) => println("falsche Eingabe")
    }
    }


  override def update: Boolean = {
    println(controller.gametoString);
    println(GameStatus.message(controller.gameStatus))
    controller.gameStatus = GameStatus.IDLE
    true
  }
}
