package de.htwg.se.Tank.model
trait Player{
   val id : Integer
   var tank : Tank
   val name : String
   var pos : Position

}

object PlayerFactory{
   val PLAYER1 : Int = 1
   val PLAYER2 : Int = 2
   val LIFEPOINTS : Int = 100
   val DEFAULT_A : Int = 30
   def createPlayer1(name:String, pos: Position) :Player ={
      Player1(name,pos)
   }
   def createPlayer2(name:String, pos: Position) : Player = {
      Player2(name,pos)
   }


   case class Player1(name: String, var pos: Position) extends Player {
      override val id: Integer = PLAYER1
      override def toString: String = {
         val s = "Player: " + name + "\n" + tank.toString
         s
      }
      var tank = Tank(pos, LIFEPOINTS, DEFAULT_A)

   }

   case class Player2(name: String, var pos: Position) extends Player {
      override val id: Integer = PLAYER2
      override def toString: String = {
         val s = "Player: " + name + "\n" + tank.toString
         s
      }
      var tank = Tank(pos, LIFEPOINTS, DEFAULT_A)
   }




}