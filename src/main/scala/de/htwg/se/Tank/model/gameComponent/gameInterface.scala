package de.htwg.se.Tank.model.gameComponent

import de.htwg.se.Tank.model.gameComponent.gameBase.Map.StateContext.State
import de.htwg.se.Tank.model.playerComponent.playerBase.{Player, Position}

trait gameInterface {
  val partyname : String
  val mapNum : Int
  var mapSize : MapSize
  def moveLeft() : Player
  def undoMoveLeft() : Player
  def moveRight() : Player
  def undoMoveRight() : Player
  def moveAngleUp() : Player
  def moveAngleDown() : Player
  def shoot(power :Int) : Unit
  def changePlayer() : Unit
}

trait mapInterface {
  def getActivePlayer : Player
  def getFXList(GUImode : Boolean) : List[(Double,Double)]
  def getFxDouble(): List[Double]
  def posInMap(pos: Position): Boolean
  def getPlayer1 : Player
  def getPlayer2 : Player
}

trait MapSize {
  val MAP_X : Int
  val MAP_Y : Int
}

