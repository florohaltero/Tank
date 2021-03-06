package de.htwg.se.Tank.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import de.htwg.se.Tank.TankModule
import de.htwg.se.Tank.model.fileIoComponent.FileIOInterface
import de.htwg.se.Tank.model.gameComponent.gameBase.Map.StateContext
import de.htwg.se.Tank.model.gameComponent.gameBase.{Game, Map}
import de.htwg.se.Tank.model.gameComponent.gameInterface
import de.htwg.se.Tank.model.playerComponent.playerBase.{Player, Position}
import play.api.libs.json.{JsNumber, JsObject, JsValue, Json, Writes}
import de.htwg.se.Tank.model.playerComponent.playerBase

import scala.io.Source

class FileIO extends FileIOInterface {
  override def load: gameInterface = {
    var game: gameInterface = null
    val source: String = Source.fromFile("game.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val partyAttr = (json \ "game" \ "partyname").as[String]
    val mapNoAttr = (json \ "game" \ "mapNo").as[Int]
    val mapSizeAttr = (json \ "game" \ "size").as[String]

    val activePlayerAttr = (json \ "game" \ "map" \ "activePlayer_id").as[Int]
    val movesPlayerAttr = (json \ "game" \ "map" \ "moves").as[Int]

    val player1NameAttr = (json \ "game" \ "player1" \ "name").as[String]
    val player1IDAtrr = (json \ "game" \ "player1" \ "id").as[Int]
    val player1posxAttr = (json \ "game" \ "player1" \ "posx").as[Double]
    val player1posyAttr = (json \ "game" \ "player1" \ "posy").as[Double]

    val player2NameAttr = (json \ "game" \ "player2" \ "name").as[String]
    val player2IDAtrr = (json \ "game" \ "player2" \ "id").as[Int]
    val player2posxAttr = (json \ "game" \ "player2" \ "posx").as[Double]
    val player2posyAttr = (json \ "game" \ "player2" \ "posy").as[Double]

    val tank1lifeAttr = (json \ "game" \ "tank" \ "life1").as[Int]
    val tank2lifeAttr = (json \ "game" \ "tank" \ "life2").as[Int]
    val tank1angleAttr = (json \ "game" \ "tank" \ "angle1").as[Int]
    val tank2angleAttr = (json \ "game" \ "tank" \ "angle2").as[Int]

    game = Game(partyAttr, mapNoAttr, mapSizeAttr, "", "")

    Map.moves = movesPlayerAttr

    Map.StateContext.setState(StateContext.P1State())
    Map.activePlayer = Map.getPlayer(activePlayerAttr)

    Map.getPlayer(player1IDAtrr).name = player1NameAttr
    Map.getPlayer(player2IDAtrr).name = player2NameAttr

    Map.getPlayer(player1IDAtrr).pos = Position(player1posxAttr, player1posyAttr)
    Map.getPlayer(player2IDAtrr).pos = Position(player2posxAttr, player2posyAttr)

    Map.getPlayer(player1IDAtrr).tank = playerBase.Tank(Position(player1posxAttr, player1posyAttr), tank1lifeAttr, tank1angleAttr)
    Map.getPlayer(player2IDAtrr).tank = playerBase.Tank(Position(player2posxAttr, player2posyAttr), tank2lifeAttr, tank2angleAttr)

    game
  }

  override def save(game: gameInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("game.json"))
    pw.write(Json.prettyPrint(gameToJson(game)))
    pw.close
  }

//  implicit def gameWrites = new Writes[gameInterface] {
//    def writes(game: gameInterface) = Json.obj(
//      "game" -> gameToJson(game)
//    )
//  }

  def gameToJson(game: gameInterface) :JsObject = {
    Json.obj(
      "game" -> Json.obj(
        "partyname" -> game.partyname,
                "mapNo" -> game.mapNum,
                "size" -> game.mapSize.getName,
        "map" -> Json.obj(
          "activePlayer_id" -> Map.activePlayer.id.toInt,
                  "moves" -> Map.moves
        ),
        "player1" -> Json.obj(
          "name" -> Map.p1.name,
                  "id" -> Map.p1.id.toInt,
                  "posx" -> Map.p1.pos.x,
                  "posy" -> Map.p1.pos.y
        ),
        "player2" -> Json.obj(
          "name" -> Map.p2.name,
                  "id" -> Map.p2.id.toInt,
                  "posx" -> Map.p2.pos.x,
                  "posy" -> Map.p2.pos.y
        ),
        "tank" -> Json.obj(
          "life1" -> Map.p1.tank.lp,
                  "angle1" -> Map.p1.tank.canonAngle,
                  "life2" -> Map.p2.tank.lp,
                  "angle2" -> Map.p2.tank.canonAngle
        )
      )
    )
  }
}
