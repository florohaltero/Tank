package de.htwg.se.Tank.controller

import de.htwg.se.Tank.util.Command

class RightCommand(controller: Controller) extends Command {
  override def doStep: Unit = {
    controller.game.moveRight()
  }

  override def undoStep: Unit = {
    controller.game.undoMoveRight()
  }

  override def redoStep: Unit = {
    controller.game.moveRight()
  }
}
