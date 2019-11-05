package de.htwg.se.Tank.model

case class Tank(pos: Position, lp: Int, canonAngle: CanonAngle) {
  def moveLeft(): Tank = {
    copy(pos.move(pos.x - 1, pos.y), lp, canonAngle)
  }
  def moveRight(): Tank = {
    copy(pos.move(pos.x + 1, pos.y), lp, canonAngle)
  }
  def canonUp(): Tank = {
    copy(pos, lp, canonAngle.moveCanon(canonAngle.x, canonAngle.y + 1))
  }
  def canonDown(): Tank = {
    copy(pos, lp, canonAngle.moveCanon(canonAngle.x, canonAngle.y - 1))
  }
  def shoot(power: Int): Boolean = {
    val hitbox = Hitbox(5, 10, pos)
    if (hitbox.posInHitbox(Calc.shootCalc(pos, canonAngle, power)).equals(true)) {


      return true
    }
    false
  }
}
