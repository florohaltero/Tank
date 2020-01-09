package de.htwg.se.Tank.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.Tank.model.fileIoComponent.FileIOInterface
import de.htwg.se.Tank.model.gameComponent.gameBase.Map
import de.htwg.se.Tank.model.gameComponent.gameInterface
import de.htwg.se.Tank.model.playerComponent.playerBase.Player
import play.api.libs.json.{JsNumber, JsValue, Json, Writes}

class FileIO extends FileIOInterface {
  override def load: gameInterface = ???

  override def save(game: gameInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("game.json"))
    pw.write(Json.prettyPrint(gameToJson(game)))
    pw.close
  }

  implicit val gameWrites = new Writes[gameInterface] {
    def writes(game: gameInterface) = Json.obj(
      "game" -> gameToJson(game)
    )
  }

  def gameToJson(game: gameInterface) = {
    Json.obj(
      "game" -> Json.obj(
        "partyname" -> game.partyname,
                "mapNum" -> game.mapNum,
        "map" -> Json.obj(
          "active_player" -> Map.activePlayer),
                  "moves" -> Map.moves,
        "player" -> Json.obj(
          "name" -> Map.activePlayer.name,
        )
        )
    )
  }
}
