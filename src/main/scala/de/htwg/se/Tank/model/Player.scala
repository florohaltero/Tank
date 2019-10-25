package de.htwg.se.Tank.model

case class Player(name: String, var lp : Int) {
   override def toString:String = name

   def damagetaken(damage: Int) : Int = {
      lp = lp.- (damage)
      lp
   }
}

