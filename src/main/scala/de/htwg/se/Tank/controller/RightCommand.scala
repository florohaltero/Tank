package de.htwg.se.Tank.controller

import de.htwg.se.Tank.util.Command

class RightCommand(controller: Controller) extends Command {
  override def doStep: Unit = controller.game.mapObject.activePlayer = controller.game.mapObject.moveRight()

  override def undoStep: Unit = controller.game.mapObject.activePlayer = controller.game.mapObject.moveLeft()

  override def redoStep: Unit = controller.game.mapObject.activePlayer = controller.game.mapObject.moveRight()
}