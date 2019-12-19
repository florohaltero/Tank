package de.htwg.se.Tank.aview

import de.htwg.se.Tank.controller.{Controller, Fire, NewGame, UpdateMap}
import de.htwg.se.Tank.model.gameComponent.gameBase.Game
import de.htwg.se.Tank.util.Observer

import scala.swing.Reactor
import scala.util.{Failure, Success, Try}

class TUI(controller: Controller) extends Reactor{
  listenTo(controller)
  def processInputLine(input: String): Unit ={
    Try(input match {
      case "m0" => controller.setGame("Standard", 0, controller.game.name1, controller.game.name2)
      case "m1" => controller.setGame("Standard", 1, controller.game.name1, controller.game.name2)
      case "n" => controller.setGame("Standard", 0, "Flo", "Sasch")
      case "a" => controller.moveLeft()
      case "d" => controller.moveRight()
      case "w" => controller.moveAngleUp()
      case "s" => controller.moveAngleDown()
      case "p" => controller.changePlayer()
      case "f" => controller.shoot(20)
      case "z" => controller.undo
      case "y" => controller.redo
      }
    ) match {
      case Success(e) =>
      case Failure(e) => println("falsche Eingabe")
    }
    }


  reactions += {
    case event: NewGame => printTui
    case event: UpdateMap => printTui
    case event: Fire => printTui
  }

  def printTui: Unit = {
    println(controller.gametoString)
  }

}
