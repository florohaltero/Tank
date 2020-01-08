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

import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface {
  override def load: gameInterface = {
    val file = scala.xml.XML.loadFile("player.xml")
    val sizeAttr = (file \\ "name" \ "@name")
    val name = sizeAttr.text
    val injector = Guice.createInjector(new TankModule)
    val player1 : Player =  injector.instance[Player](Names.named("1"))
    val player2 : Player = injector.instance[Player](Names.named("2"))
    var game = new Game()
  }

  override def save(game: gameInterface): Unit = {
    saveString(game)
  }


  def saveString(game: gameInterface) = {
    import java.io._
    val pw = new PrintWriter(new File("grid.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gametoXML(game))
    pw.write(xml)
    pw.close
  }

  def gametoXML(game: gameInterface) ={
    <game>
      <party>{game.partyname}</party>
      <mapNo>{game.mapNum}</mapNo>
      {maptoXML}
      <size>{game.mapSize}</size>
      {playerToXML(Map.p1)}
      {playerToXML(Map.p2)}
    </game>
  }

  def playerToXML(player: Player) = {
    <player>
      <name>{player.name}</name>
      <id>{player.id}</id>
      <tank>{player.tank}</tank>
      <position>{player.pos}</position>
    </player>
  }

  def maptoXML = {
    <map>
      <activePlayer>{Map.activePlayer}</activePlayer>
      <moves>{Map.moves}</moves>
    </map>
  }


}
