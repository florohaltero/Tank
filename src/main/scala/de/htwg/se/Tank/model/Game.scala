package de.htwg.se.Tank.model

case class Game(partyname :String, map: Map, player1: Player,player2: Player){
  override def toString: String = {
    var name = "\n" + "partyname: " + partyname + "\n"
    name = name + player1.toString + "\n" + player2.toString + "\n"
    name = name + map.toString
    name
  }

}
