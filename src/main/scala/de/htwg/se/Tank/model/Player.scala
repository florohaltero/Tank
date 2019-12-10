package de.htwg.se.Tank.model

import scala.collection.mutable.ListBuffer

trait Player{
   val id : Integer
   var tank : Tank
   val name : String
   var pos : Position
   var lp: Int
   var power : Int
   var ItemList: ListBuffer[ItemTemplate] = ListBuffer.empty
}

object PlayerFactory {
   val PLAYER1: Int = 1
   val PLAYER2: Int = 2
   val LP: Int = 100
   val DEFAULT_A: Int = 30
   val DEFAULT_POWER : Int = 20

   def createPlayer1(name: String): Player = {
      Player1(name, Map.generatePos(1))
   }

   def createPlayer2(name: String): Player = {
      Player2(name, Map.generatePos(2))
   }


   case class Player1(name: String, var pos: Position) extends Player {
      override val id: Integer = PLAYER1
      override def toString: String = {
         val s = "Player: " + name + "\n" + tank.toString
         s
      }
      var tank = Tank(pos, LP, DEFAULT_A)
      override var lp: Int = LP
      override var power: Int = DEFAULT_POWER
   }

   case class Player2(name: String, var pos: Position) extends Player {
      override val id: Integer = PLAYER2
      override def toString: String = {
         val s = "Player: " + name + "\n" + tank.toString
         s
      }
      var tank = Tank(pos, LP, DEFAULT_A)
      override var lp: Int = LP
      override var power: Int = DEFAULT_POWER
   }

}