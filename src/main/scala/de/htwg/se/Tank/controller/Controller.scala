package de.htwg.se.Tank.controller

import de.htwg.se.Tank.model.gameComponent.gameBase.Game
import de.htwg.se.Tank.util.{Observable, UndoManager}

import scala.swing.Publisher

class Controller(var game: Game) extends Publisher {
  private val undoManager = new UndoManager
  def setGame(partyname: String, map: Int, name1: String, name2: String): Unit = {
    game = Game(partyname, map , name1, name2)
    publish(new NewGame)
    publish(new UpdateMap)
  }

  def setDefaultGame(): Unit  = {
    game = Game("Default", 0 , "Flo", "Sasch")
    publish(new NewGame)
  }

  def changePlayer() : Unit = {
    undoManager.doStep(new ChangePlayerCommand(this))
    publish(new UpdateMap)

  }
  def moveLeft() : Unit = {
    undoManager.doStep(new LeftCommand(this))
    publish(new UpdateMap)

  }
  def moveRight() : Unit = {
    undoManager.doStep(new RightCommand(this))
    publish(new UpdateMap)
  }
  def moveAngleUp() : Unit ={
    game.moveAngleUp()
    publish(new UpdateMap)
  }
  def moveAngleDown() : Unit ={
    game.moveAngleDown()
    publish(new UpdateMap)
  }
  def shoot(pow : Int) : Unit ={
    game.shoot(pow)
    publish(new Fire)
  }

  def gametoString: String = game.toString

  def undo: Unit = {
    undoManager.undoStep
    publish(new UpdateMap)
  }

  def redo: Unit = {
    undoManager.redoStep
    publish(new UpdateMap)
  }
}
