package de.htwg.se.Tank.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.Tank.TankModule
import de.htwg.se.Tank.model.fileIoComponent.FileIOInterface
import de.htwg.se.Tank.model.gameComponent.{MapSize, gameInterface, mapInterface}
import de.htwg.se.Tank.model.playerComponent.playerBase.Player
import net.codingwell.scalaguice.InjectorExtensions._

import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface {
  override def load: Player = {
    var player = null
    val file = scala.xml.XML.loadFile("player.xml")
    val sizeAttr = (file \\ "name" \ "@name")
    val name = sizeAttr.text
    val injector = Guice.createInjector(new TankModule)
    player = injector.instance[Player]
    player
  }

  override def save(player: Player): Unit = saveString(player)


  def saveString(player: Player) = {
    import java.io._
    val pw = new PrintWriter(new File("grid.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(playerToXML(player))
    pw.write(xml)
    pw.close
  }

  def playerToXML(player: Player) = {
    <player>
      <name>{player.name}</name>
      <id>{player.id}</id>
      <tank>{player.tank}</tank>
      <position>{player.pos}</position>
    </player>
  }
}
