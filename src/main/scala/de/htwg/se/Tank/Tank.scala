package de.htwg.se.Tank

import de.htwg.se.Tank.aview.TUI
import de.htwg.se.Tank.model.{Game, GameInitializer, Player, Round}

object Tank {
  def main(args: Array[String]): Unit = {
    //val student = Player("Your Name")
    //println("Hello, " + student.name)

    var input : String = ""
    val tui = new TUI
    var g1: Game = GameInitializer.setGame("Standard", 1, "Flo", "Sascha")
    //println("Type name of Player1 and Player2:")
    do {
      println("Game : " + g1.toString)
      input = scala.io.StdIn.readLine()
      g1 = tui.processInputLine(input, g1)
    } while (input != "q")

  }
}
