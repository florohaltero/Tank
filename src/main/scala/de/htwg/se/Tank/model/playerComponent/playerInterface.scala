package de.htwg.se.Tank.model.playerComponent

import de.htwg.se.Tank.model.ItemTemplate
import de.htwg.se.Tank.model.playerComponent.playerBase.{Player, Position, Tank}

import scala.collection.mutable.ListBuffer

trait PlayerFactoryInterface {
  def createPlayer1(name: String): Player
  def createPlayer2(name: String): Player
}

trait TankInterface {
  def canonUp(): Tank
  def canonDown(): Tank
}
