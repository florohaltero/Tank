package de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller

import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.{ChangePlayerCommand, LeftCommand, RightCommand}
import de.htwg.se.Tank.controller.controllerComponent.{ControllerInterface, Fire, NewGame, UpdateMap}
import de.htwg.se.Tank.model.gameComponent.gameBase.Game
import de.htwg.se.Tank.util.{Observable, UndoManager}
import de.htwg.se.Tank.model.gameComponent.gameInterface


class Controller(var game: gameInterface) extends ControllerInterface {
  private val undoManager = new UndoManager
  override def setGame(partyname: String, map: Int, name1: String, name2: String): Unit = {
    game = Game(partyname, map , name1, name2)
    publish(new NewGame)
    publish(new UpdateMap)
  }

  def getGame() : gameInterface = {
    game
  }

  override def setDefaultGame(): Unit  = {
    game = Game("Default", 0 , "Flo", "Sasch")
    publish(new NewGame)
  }

  override def changePlayer() : Unit = {
    undoManager.doStep(new ChangePlayerCommand(this))
    publish(new UpdateMap)

  }
  override def moveLeft() : Unit = {
    undoManager.doStep(new LeftCommand(this))
    publish(new UpdateMap)

  }
  override def moveRight() : Unit = {
    undoManager.doStep(new RightCommand(this))
    publish(new UpdateMap)
  }
  override def moveAngleUp() : Unit ={
    game.moveAngleUp()
    publish(new UpdateMap)
  }
  override def moveAngleDown() : Unit ={
    game.moveAngleDown()
    publish(new UpdateMap)
  }
  override def shoot(pow : Int) : Unit ={
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
