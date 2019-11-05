package de.htwg.se.Tank.model

case class CanonAngle(x: Double, y: Double) {
  def moveCanon(x:Double, y:Double): CanonAngle = copy(x,y)
}
