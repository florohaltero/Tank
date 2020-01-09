package de.htwg.se.Tank.model.playerComponent.playerBase

import de.htwg.se.Tank.model.gameComponent.gameBase.Hitbox
import de.htwg.se.Tank.model.playerComponent.TankInterface

case class Tank(pos: Position,var lp: Int, canonAngle: Int) extends TankInterface{
  final val STEP = 5
  override def toString: String = {
    var s = "Pos(x,y): (" + pos.x + "," + pos.y + ") Life: " + lp + " CannonAngle: " + canonAngle
    s
  }
  var hitbox = Hitbox(1, 2, pos)
  var damage = 30
  override def canonUp(): Tank = {
    copy(pos, lp, canonAngle + STEP)
  }
  override def canonDown(): Tank = {
    copy(pos, lp, canonAngle - STEP)
  }
  def getDamage(damage : Int): Unit = {
    //copy(pos,lp -damage, canonAngle)
    lp = lp - damage
  }
}
