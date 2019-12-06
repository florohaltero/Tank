package de.htwg.se.Tank.controller

import de.htwg.se.Tank.util.Command

class LeftCommand(controller: Controller) extends Command {
  override def doStep: Unit = {
    controller.game.moveLeft()
  }

  override def undoStep: Unit = {
    controller.game.undoMoveLeft()
  }

  override def redoStep: Unit = {
    controller.game.moveLeft()
  }

}
