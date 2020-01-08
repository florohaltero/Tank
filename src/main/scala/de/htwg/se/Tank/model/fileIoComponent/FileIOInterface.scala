package de.htwg.se.Tank.model.fileIoComponent

import de.htwg.se.Tank.model.gameComponent.gameInterface
import de.htwg.se.Tank.model.playerComponent.playerBase.Player

trait FileIOInterface {

  def load: gameInterface
  def save(game: gameInterface): Unit

}
