package de.htwg.se.Tank.model

case class Player(id: Int, name: String, pos: Position) {
   override def toString:String = {
      val s = "Player: " + name + "\n" + tank.toString
      s
   }
   val tank = Tank(pos, 100, CanonAngle(1))
}

