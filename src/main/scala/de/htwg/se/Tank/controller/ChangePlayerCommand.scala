package de.htwg.se.Tank.controller

import de.htwg.se.Tank.model.Map
import de.htwg.se.Tank.util.Command

class ChangePlayerCommand(controller: Controller) extends Command{
var memento : Map = controller.game.mapObject
  override def doStep: Unit = {
    memento = controller.game.mapObject
    controller.game.mapObject.StateContext.state.changePlayer()
  }

  override def undoStep: Unit = {
    val new_memento = controller.game.mapObject
    controller.game.mapObject = memento
    memento = new_memento
  }

  override def redoStep: Unit = {
    val new_memento = controller.game.mapObject
    controller.game.mapObject = memento
    memento = new_memento
  }
}
