package de.htwg.se.Tank.controller

import de.htwg.se.Tank.model.Map
import de.htwg.se.Tank.util.Command

class ChangePlayerCommand(controller: Controller) extends Command{
  var memento: (Integer) = _
  override def doStep: Unit = {
    memento = controller.game.mapObject.moves
    controller.game.mapObject.StateContext.state.changePlayer()
  }

  override def undoStep: Unit = {
    val new_memento = controller.game.mapObject.moves
    controller.game.mapObject.StateContext.state.changePlayer()
    controller.game.mapObject.moves = memento
    memento = new_memento
  }

  override def redoStep: Unit = {
    val new_memento = controller.game.mapObject.moves
    controller.game.mapObject.StateContext.state.changePlayer()
    controller.game.mapObject.moves = memento
    memento = new_memento
  }
}
