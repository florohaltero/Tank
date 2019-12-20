package de.htwg.se.Tank.model.playerComponent.playerBase

import de.htwg.se.Tank.model.gameComponent.gameBase.Hitbox
import de.htwg.se.Tank.model.playerComponent.TankInterface

case class Tank(pos: Position, lp: Int, canonAngle: Int) extends TankInterface{
  final val STEP = 5
  override def toString: String = {
    var s = "Pos(x,y): (" + pos.x + "," + pos.y + ") Life: " + lp + " CannonAngle: " + canonAngle
    s
  }
  val hitbox = Hitbox(5, 10, pos)
  var damage = 30
  override def canonUp(): Tank = {
    copy(pos, lp, canonAngle + STEP)
  }
  override def canonDown(): Tank = {
    copy(pos, lp, canonAngle - STEP)
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
