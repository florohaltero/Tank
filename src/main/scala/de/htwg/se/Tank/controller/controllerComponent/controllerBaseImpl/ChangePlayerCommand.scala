package de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.Tank.controller.controllerComponent.ControllerInterface
import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase.Map
import de.htwg.se.Tank.util.Command

class ChangePlayerCommand(controller: ControllerInterface) extends Command{
  var memento: (Integer) = _
  override def doStep: Unit = {
    memento = Map.moves
    controller.getGame.changePlayer()
  }

  override def undoStep: Unit = {
    val new_memento = Map.moves
    controller.getGame.changePlayer()
    Map.moves = memento
    memento = new_memento
  }

  override def redoStep: Unit = {
    val new_memento = Map.moves
    controller.getGame.changePlayer()
    Map.moves = memento
    memento = new_memento
  }
}
