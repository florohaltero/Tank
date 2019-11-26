package de.htwg.se.Tank

import de.htwg.se.Tank.aview.TUI
import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.Game


object Tank {
  def main(args: Array[String]): Unit = {
    val lines = scala.io.Source.fromFile("Tank_label.txt").mkString
    print(lines)
    /*println("Spieler 1: ")
    val name1 = scala.io.StdIn.readLine()
    println("Spieler 2: ")
    val name2 = scala.io.StdIn.readLine()
    println("Map(0, 1): ")
    val map = scala.io.StdIn.readLine()
    */
    var input : String = ""
    val controller = new Controller(Game("Standard", 0, "Flo", "Sasch"))
    val tui = new TUI(controller)
    controller.notifyObservers
    do {
      input = scala.io.StdIn.readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}
