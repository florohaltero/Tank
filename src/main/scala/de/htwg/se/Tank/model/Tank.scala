package de.htwg.se.Tank.model

case class Tank(pos: Position, lp: Int, canonAngle: CanonAngle) {
  override def toString: String = {
    var s = "Pos(x,y): (" + pos.x + "," + pos.y + ") Life: " + lp + " CannonAngle: " + canonAngle.angle
    s
  }
  val hitbox = Hitbox(5, 10, pos)
  def moveLeft(): Tank = {
    copy(pos.move(pos.x - 5, pos.y), lp, canonAngle)
  }
  def moveRight(): Tank = {
    copy(pos.move(pos.x + 1, pos.y), lp, canonAngle)
  }
  def canonUp(): Tank = {
    copy(pos, lp, canonAngle.moveCanon(canonAngle.angle + 5))
  }
  def canonDown(): Tank = {
    copy(pos, lp, canonAngle.moveCanon(canonAngle.angle - 5))
  }
  def shoot(power: Int): Boolean = {

    if (hitbox.posInHitbox(Calc.shootCalc(pos, canonAngle, power)).equals(true)) {


      return true
    }
    false
  }
}
