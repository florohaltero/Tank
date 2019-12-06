package de.htwg.se.Tank.controller

import de.htwg.se.Tank.model.{Game, Player}
import de.htwg.se.Tank.util.{Observable, UndoManager}

class Controller(var game: Game) extends Observable {
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
    game.moveAngleUp()
    notifyObservers
  }
  def moveAngleDown() : Unit ={
    game.moveAngleDown()
    notifyObservers
  }
  def shoot() : Unit ={
    game.shoot(20)
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
