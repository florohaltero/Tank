package de.htwg.se.Tank.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.Tank.TankModule
import de.htwg.se.Tank.model.fileIoComponent.FileIOInterface
import de.htwg.se.Tank.model.gameComponent.gameBase.Game
import de.htwg.se.Tank.model.gameComponent.{MapSize, gameInterface, mapInterface}
import de.htwg.se.Tank.model.playerComponent.PlayerFactoryInterface
import de.htwg.se.Tank.model.playerComponent.playerBase.Player
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.Tank.model.gameComponent.gameBase.Map

import scala.xml.{Elem, PrettyPrinter}

class FileIO extends FileIOInterface {
  override def load: gameInterface = {
    val file = scala.xml.XML.loadFile("game.xml")
    val partyAttr = (file \ "game" \ "party")
    val partyT = partyAttr.text
    val mapNoAttr = (file \ "game" \ "mapNo")
    val mapNoT = mapNoAttr.text.toInt
    val mapSizeAttr = (file \ "game" \ "size")
    val mapSizeT = mapNoAttr.text
    val activePlayerT = (file \ "game" \ "map" \ "activePlayer").text.toInt
    val movesT = (file \ "game" \ "map" \ "moves").text.toInt
    val players = (file \\ "player")
    for(player<- players){
      val name = (player \ "name").text
      val id = (player \ "id").text.toInt
      val posx = (player \ "posx").text.toDouble
      val posy = (player \ "posy").text.toDouble
      val lp = (player \ "tank" \ "life")
      val angle = (player \ "tank" \ "angle")
    }

    val injector = Guice.createInjector(new TankModule)
    val player1 : Player =  injector.instance[Player](Names.named("1"))
    val player2 : Player = injector.instance[Player](Names.named("2"))
    var game : gameInterface = new Game(partyT,mapNoT,mapSizeT,"","")

    //var game = new Gam
  }

  override def save(game: gameInterface): Unit = {
    saveString(game)
  }


  def saveString(game: gameInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("game.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gametoXML(game))
    pw.write(xml)
    pw.close
  }

  def gametoXML(game: gameInterface): Elem ={
    <game>
      <party>{game.partyname}</party>
      <mapNo>{game.mapNum}</mapNo>
      {maptoXML}
      <size>{game.mapSize.getName}</size>
      {playerToXML(Map.p1)}
      {playerToXML(Map.p2)}
    </game>
  }

  def playerToXML(player: Player): Elem = {
    <player>
      <name>{player.name}</name>
      <id>{player.id}</id>
      {tanktoXML(player)}
      <posx>{player.pos.x}</posx>
      <posy>{player.pos.y}</posy>
    </player>
  }

  def maptoXML: Elem = {
    <map>
      <activePlayer>{Map.activePlayer.id}</activePlayer>
      <moves>{Map.moves}</moves>
    </map>
  }

  def tanktoXML(player: Player): Elem = {
    <tank>
      <life>{player.tank.lp}</life>
      <angle>{player.tank.canonAngle}</angle>
    </tank>
  }


}
