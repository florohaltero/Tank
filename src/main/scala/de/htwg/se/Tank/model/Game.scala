package de.htwg.se.Tank.model

case class Game(partyname :String, map: Int, player1: String,player2: String){
  override def toString: String = {
    var name = "\n" + "partyname: " + partyname + "\n"
    name += player1.toString + "\n" + player2.toString + "\n"
    name += mapObject.toString
    name
  }
  val mapObject = Map((0,0),(200,50),map, player1, player2)


}
