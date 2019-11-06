package de.htwg.se.Tank.model

case class Player(name: String) {
   override def toString:String = {
      var s = "Player: " + name + "\n" +  tank.toString
      s
   }
   val tank = Tank(Position(0,0),100,CanonAngle(1))
}

