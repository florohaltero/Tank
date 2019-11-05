package de.htwg.se.Tank.model

case class Tank(pos: Position, lp: Int, canonAngle: CanonAngle) {
  def moveLeft(): Position = {
    pos.move(pos.x - 1, pos.y)
  }
  def moveRight(): Position = {
    pos.move(pos.x + 1, pos.y)
  }
  def canonUp(): CanonAngle = {
    canonAngle.moveCanon(canonAngle.x, canonAngle.y + 1)
  }
  def canonDown(): CanonAngle = {
    canonAngle.moveCanon(canonAngle.x, canonAngle.y - 1)
  }
}
