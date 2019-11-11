package de.htwg.se.Tank.controller

import de.htwg.se.Tank.model.{Game, Player}
import de.htwg.se.Tank.util.Observable

class Controller(var game: Game)  extends Observable{
  def setGame(partyname :String, map: Int, name1: String, name2: String): Unit ={
    game = Game(partyname, map , name1, name2)
    notifyObservers
  }
  def  moveLeft(game: Game, p: Player) : Unit = {
    game.mapObject.moveLeft(p)
    notifyObservers
  }
  def  moveRight(game: Game, p: Player) : Unit = {
    game.mapObject.moveRight(p)
    notifyObservers
  }

  def gametoString: String = game.toString
}
