package de.htwg.se.Tank.model

object GameInitializer {
  val version = 0.1
  val name = "Tank"

  def setGame(partyname :String, map: Int, name1: String, name2: String): Game ={
    //val mapObject = Map((0,0),(200,50),map)
    /*var p1 = Player(1, name1, Position(0,0))
    var p2 = Player(2, name2, Position(0,0))
    p1 = Player(1, name1, mapObject.generatePos(p1))
    p2 = Player(2, name2, mapObject.generatePos(p2))
    */
    Game(partyname,map , name1, name2)

  }


}
