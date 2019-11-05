package de.htwg.se.Tank

import de.htwg.se.Tank.model.{GameInitializer, Player, Round}

object Tank {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
    GameInitializer

  }
}
