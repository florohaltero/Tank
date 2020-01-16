package de.htwg.se.Tank.model.playerComponent.playerBase

import de.htwg.se.Tank.model.ItemTemplate
import de.htwg.se.Tank.model.gameComponent.{gameBase, gameInterface}
import de.htwg.se.Tank.model.playerComponent.PlayerFactoryInterface
import de.htwg.se.Tank.model.gameComponent.gameBase.Map
import scala.collection.mutable.ListBuffer

trait Player {
   val id : Integer
   var tank : Tank
   var name : String
   var pos : Position
   var power : Int
   var ItemList: ListBuffer[ItemTemplate] = ListBuffer.empty

   val WIDTH = 2
   val HEIGHT = 1

   def posInHitbox(p: Position): Boolean ={
      val xRange : ((Double),(Double)) = ((pos.x - 0.5 * WIDTH),(pos.x + 0.5 * WIDTH))
      val yRange: ((Double), (Double)) = ((pos.y - 0.5 * HEIGHT), (pos.y + 0.5 * HEIGHT))
      if(p.x >= xRange._1 && p.x <= xRange._2 && p.y >= yRange._1 && p.y <= yRange._2) {
         return true
      }
      false
   }

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


   case class Player1(var name: String, var pos: Position) extends Player {
      override val id: Integer = PLAYER1
      override def toString: String = {
         val s = "Player: " + name + "\n" + tank.toString
         s
      }
      var tank = Tank(pos, LP, P1DEFAULT_A)
      override var power: Int = DEFAULT_POWER
   }

   case class Player2(var name: String, var pos: Position) extends Player {
      override val id: Integer = PLAYER2
      override def toString: String = {
         val s = "Player: " + name + "\n" + tank.toString
         s
      }
      var tank = Tank(pos, LP, P2DEFAULT_A)
      override var power: Int = DEFAULT_POWER
   }

}
