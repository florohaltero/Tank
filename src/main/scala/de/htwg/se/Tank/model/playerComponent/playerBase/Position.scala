package de.htwg.se.Tank.model.playerComponent.playerBase

case class Position( x: Double, y: Double) {
  def move(x:Double, y:Double): Position = copy(x,y)
}
