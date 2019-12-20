package de.htwg.se.Tank.model.gameComponent

import de.htwg.se.Tank.model.gameComponent.gameBase.Map.StateContext.State
import de.htwg.se.Tank.model.playerComponent.playerBase.Player
import de.htwg.se.Tank.model.playerComponent.playerBase.Player

trait gameInterface {
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

}

