package de.htwg.se.Tank.model.fileIoComponent

import de.htwg.se.Tank.model.playerComponent.playerBase.Player

trait FileIOInterface {

  def load: Player
  def save(player: Player): Unit

}
