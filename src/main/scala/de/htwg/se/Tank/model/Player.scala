package de.htwg.se.Tank.model

case class Player(id: Int, name: String,var pos: Position) {
   override def toString:String = {
      val s = "Player: " + name + "\n" + tank.toString
      s
   }
   var tank = Tank(pos, 100, 10)
}

