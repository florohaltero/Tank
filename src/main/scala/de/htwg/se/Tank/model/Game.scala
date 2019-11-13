package de.htwg.se.Tank.model

case class Game(partyname :String, map: Int, player1: String,player2: String){
  override def toString: String = {
    var name = "\n" + "partyname: " + partyname + "\n"
    name += player1.toString + "\n" + player2.toString + "\n"
    name += mapObject.toString
    name
  }
  final val MAP_X = 20
  final val MAP_Y = 10

  var mapObject = Map((0,0),(MAP_X,MAP_Y),map, player1, player2)

//  def setMap(map :Map)  : Game = {
//    null
//  }


}
