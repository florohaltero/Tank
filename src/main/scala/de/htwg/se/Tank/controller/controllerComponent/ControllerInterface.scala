package de.htwg.se.Tank.controller.controllerComponent

trait ControllerInterface {
  def setGame(partyname: String, map: Int, name1: String, name2: String): Unit = {}
  def setDefaultGame(): Unit
  def changePlayer() : Unit
  def moveLeft() : Unit
  def moveRight() : Unit
  def moveAngleUp() : Unit
  def moveAngleDown() : Unit
  def shoot(pow : Int) : Unit
  def gametoString: String
  def undo: Unit
  def redo: Unit
}

import scala.swing.event.Event

class NewGame extends Event
class UpdateMap extends Event
class Fire extends Event
