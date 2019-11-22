package de.htwg.se.Tank.model

case class Tank(pos: Position, lp: Int, canonAngle: Int) {
  final val step = 5
  override def toString: String = {
    var s = "Pos(x,y): (" + pos.x + "," + pos.y + ") Life: " + lp + " CannonAngle: " + canonAngle
    s
  }
  // val hitbox = Hitbox(5, 10, pos)
  def canonUp(): Tank = {
    copy(pos, lp, canonAngle + step)
  }
  def canonDown(): Tank = {
    copy(pos, lp, canonAngle - step)
  }
  /*
  def shoot(power: Int): Boolean = {

    if (hitbox.posInHitbox(Calc.shootCalc(pos, canonAngle, power)).equals(true)) {
      return true
    }
    false
  }

     */
}

