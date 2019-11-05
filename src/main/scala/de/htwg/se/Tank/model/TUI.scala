package de.htwg.se.Tank.model

import java.util.Scanner

object TUI {

  println("Welcome to Tank")
  println("Type name of Player1:")

  val name1 = scala.io.StdIn.readLine()
  println("Type name of Player2:")
  val name2 = scala.io.StdIn.readLine()

  println("Partyname eingeben:")
  val partyname = scala.io.StdIn.readLine()

  val game = GameInitializer.setGame(partyname,1,name1,name2)
  println("Spiel erstellt! Partyname: " + game.partyname + " Player1: " +game.player1.name + " Player2: " + game.player2.name)



}
