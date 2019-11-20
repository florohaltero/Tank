package de.htwg.se.Tank

import de.htwg.se.Tank.aview.TUI
import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.GameInitializer
import de.htwg.se.Tank.model.Game


object Tank {
  def main(args: Array[String]): Unit = {
    //val student = Player("Your Name")
    //println("Hello, " + student.name)
    val lines = scala.io.Source.fromFile("Tank_label.txt").mkString
    print(lines)

    var input : String = ""
    val controller = new Controller(Game("Standard", 0, "Flo", "Sasch"))
    val tui = new TUI(controller)
    controller.notifyObservers
//    var g1: Unit = GameInitializer.setGame("Standard", 0, "Flo", "Sascha")
    //println("Type name of Player1 and Player2:")
    do {
      input = scala.io.StdIn.readLine()
      tui.processInputLine(input)
    } while (input != "q")

  }
}
