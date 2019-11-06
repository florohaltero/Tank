package de.htwg.se.Tank.aview

import de.htwg.se.Tank.model.{Game, GameInitializer}

class TUI {

  def processInputLine(input: String, game: Game): Game ={
    input match {
      case "q" => game
      case "m0" => GameInitializer.setGame("Standard", 0, game.player1.name,game.player2.name)
      case "m1" => GameInitializer.setGame("Standard", 1, game.player1.name,game.player2.name)
      case "p" => GameInitializer.setGame("Standard", 1, "Player1","Player2")
    }
  }


}
