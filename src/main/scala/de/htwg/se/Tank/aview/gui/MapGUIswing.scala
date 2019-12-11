package de.htwg.se.Tank.aview.gui
import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.{Game, GameInit, Map}
import javax.swing.JFrame
import scalafx.scene.shape.Polyline

import scala.collection.mutable.ListBuffer
import scala.swing._
import scala.swing.event.Key

object MapGUIswing extends MainFrame {

  final val WIDTH : Double = 1000
  final val HEIGHT : Double = 600
  final val XScale: Double = WIDTH/GameInit.MAP_X2
  final val YScale: Double = HEIGHT/GameInit.MAP_Y2

  def getGUIScale(d : List[((Double),(Double))]) : List[Double] = {
    var l: ListBuffer[Double] = ListBuffer.empty
    d.foreach(d => l.append(d._1 * XScale, (GameInit.MAP_Y2 - d._2) * YScale))
    l.toList
  }

  GameInit.setMapSettings(0, "Sascha", "Flo")
  val controller = new Controller(Game("Standard", 0, "Flo", "Sasch"))

  title = "Map"

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") { })
      contents += new MenuItem(Action("Quit") { System.exit(0) })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") { controller.undo })
      contents += new MenuItem(Action("Redo") { controller.redo })
    }
  }

  contents = new BorderPanel{

  }



}


