package de.htwg.se.Tank.controller

import de.htwg.se.Tank.controller.GameStatus._
import de.htwg.se.Tank.model.{Game, Player}
import de.htwg.se.Tank.util.{Observable, UndoManager}

class Controller(var game: GameInit) extends Observable {
  var gameStatus: GameStatus = IDLE
  private val undoManager = new UndoManager

  def setGame(partyname: String, map: Int, name1: String, name2: String): Unit = {
    game = Game(partyname, map , name1, name2)
    notifyObservers
  }

  def changePlayer() : Unit = {
    undoManager.doStep(new ChangePlayerCommand(this))
    //undoManager.deleteCommands
    notifyObservers
  }
  def moveLeft() : Unit = {
    undoManager.doStep(new LeftCommand(this))
    notifyObservers
  }
  def moveRight() : Unit = {
    undoManager.doStep(new RightCommand(this))
    notifyObservers
  }
  def moveAngleUp() : Unit ={
    game.mapObject.moveAngleUp()
    notifyObservers
  }
  def moveAngleDown() : Unit ={
    game.mapObject.moveAngleDown()
    notifyObservers
  }
  def shoot() : Unit ={
    game.mapObject.shoot(20)
    notifyObservers
  }

  def gametoString: String = game.toString


  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers
  }
}
