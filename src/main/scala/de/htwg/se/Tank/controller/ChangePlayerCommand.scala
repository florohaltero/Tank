package de.htwg.se.Tank.controller

import de.htwg.se.Tank.model.Map
import de.htwg.se.Tank.util.Command

class ChangePlayerCommand(controller: Controller) extends Command{
  var memento: (Integer) = _
  override def doStep: Unit = {
    memento = Map.moves
    controller.game.changePlayer()
  }

  override def undoStep: Unit = {
    val new_memento = Map.moves
    controller.game.changePlayer()
    Map.moves = memento
    memento = new_memento
  }

  override def redoStep: Unit = {
    val new_memento = Map.moves
    controller.game.changePlayer()
    Map.moves = memento
    memento = new_memento
  }
}
