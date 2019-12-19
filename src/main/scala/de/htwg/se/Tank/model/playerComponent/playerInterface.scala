package de.htwg.se.Tank.model.playerComponent

import de.htwg.se.Tank.model.ItemTemplate
import de.htwg.se.Tank.model.playerComponent.playerBase.{Position, Tank}

import scala.collection.mutable.ListBuffer

trait playerInterface {
  val id : Integer
  var tank : Tank
  val name : String
  var pos : Position
  var lp: Int
  var power : Int
  var ItemList: ListBuffer[ItemTemplate] = ListBuffer.empty
}
