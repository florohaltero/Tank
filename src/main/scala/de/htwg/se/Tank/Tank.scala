package de.htwg.se.Tank

import de.htwg.se.Tank.aview.TUI
import de.htwg.se.Tank.aview.gui.MapGUI
import de.htwg.se.Tank.controller.{Controller, NewGame, UpdateMap}
import de.htwg.se.Tank.model.Game
import javafx.application.Application
import scalafx.application.JFXApp
import scalafx.scene.media.AudioClip


object Tank {
  def main(args: Array[String]): Unit = {
    val lines = scala.io.Source.fromFile("Tank_label.txt").mkString
    print(lines)
    var input : String = ""
    val controller = new Controller(Game("Standard", 0, "Flo", "Sasch"))
    val tui = new TUI(controller)
    val gui = new MapGUI(controller)
    gui.main(Array())
    controller.publish(new NewGame)
    //val path = "C:\\Users\\heims.DESKTOP-C6OFMGU\\OneDrive\\Studium\\3.Semester\\SE\\Tank\\masterofpuppets.mp3"
    //val res = getClass.getResource(path)
    //val clip = new AudioClip(res.toString)
    //clip.play()
    //val gui = new GUI(controller)
    //controller.notifyObservers
    do {
      input = scala.io.StdIn.readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}
