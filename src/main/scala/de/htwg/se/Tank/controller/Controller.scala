package de.htwg.se.Tank.controller

import de.htwg.se.Tank.model.{Game, Player}
import de.htwg.se.Tank.util.Observable

class Controller(var game: Game) extends Observable{
  def setGame(partyname :String, map: Int, name1: String, name2: String): Unit ={
    game = Game(partyname, map , name1, name2)
    notifyObservers
  }
  def moveLeft() : Unit = {
    game.mapObject.moveLeft()
    notifyObservers
  }
  def moveRight() : Unit = {
    game.mapObject.moveRight()
    notifyObservers
  }

  def gametoString: String = game.toString
}
