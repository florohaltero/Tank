package de.htwg.se.Tank.model

case class CanonAngle(angle: Int) {
  def moveCanon(x:Int): CanonAngle = copy(x)
}
