package de.htwg.se.Tank.controller.controllerComponent
import de.htwg.se.Tank.model.gameComponent.gameBase.Game
import de.htwg.se.Tank.model.gameComponent.gameInterface

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  def setGame(partyname: String, map: Int, name1: String, name2: String): Unit
  def setDefaultGame(): Unit
  def changePlayer() : Unit
  def moveLeft() : Unit
  def moveRight() : Unit
  def moveAngleUp() : Unit
  def moveAngleDown() : Unit
  def powerUp() : Unit
  def powerDown(): Unit
  def shoot(pow : Int) : Unit
  def gametoString: String
  def undo: Unit
  def redo: Unit
  def getGame : gameInterface
}

import scala.swing.event.Event

class NewGame extends Event
class UpdateMap extends Event
class Fire extends Event
class Hit extends Event
