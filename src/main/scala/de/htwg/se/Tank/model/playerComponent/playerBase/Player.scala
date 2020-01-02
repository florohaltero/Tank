package de.htwg.se.Tank.model.playerComponent.playerBase

import de.htwg.se.Tank.model.ItemTemplate
import de.htwg.se.Tank.model.gameComponent.gameBase
import de.htwg.se.Tank.model.playerComponent.{PlayerFactoryInterface}

import scala.collection.mutable.ListBuffer

trait Player {
   val id : Integer
   var tank : Tank
   val name : String
   var pos : Position
   var power : Int
   var ItemList: ListBuffer[ItemTemplate] = ListBuffer.empty
}

object PlayerFactory extends PlayerFactoryInterface{
   val PLAYER1: Int = 1
   val PLAYER2: Int = 2
   val LP: Int = 100
   val P1DEFAULT_A: Int = 30
   val P2DEFAULT_A: Int = 150
   val DEFAULT_POWER : Int = 20

   override def createPlayer1(name: String): Player = {
      Player1(name, gameBase.Map.generatePos(1))
   }

   override def createPlayer2(name: String): Player = {
      Player2(name, gameBase.Map.generatePos(2))
   }


   case class Player1(name: String, var pos: Position) extends Player {
      override val id: Integer = PLAYER1
      override def toString: String = {
         val s = "Player: " + name + "\n" + tank.toString
         s
      }
      var tank = Tank(pos, LP, P1DEFAULT_A)
      override var power: Int = DEFAULT_POWER
   }

   case class Player2(name: String, var pos: Position) extends Player {
      override val id: Integer = PLAYER2
      override def toString: String = {
         val s = "Player: " + name + "\n" + tank.toString
         s
      }
      var tank = Tank(pos, LP, P2DEFAULT_A)
      override var power: Int = DEFAULT_POWER
   }

}