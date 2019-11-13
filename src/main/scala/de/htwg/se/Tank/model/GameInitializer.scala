package de.htwg.se.Tank.model

object GameInitializer {
  val version = 0.1
  val name = "Tank"

  def setGame(partyname :String, map: Int, name1: String, name2: String): Game ={
    Game(partyname, map , name1, name2)
  }


}
