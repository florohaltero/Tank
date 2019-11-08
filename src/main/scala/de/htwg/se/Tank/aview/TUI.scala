package de.htwg.se.Tank.aview

import de.htwg.se.Tank.model.{Game, GameInitializer}

class TUI {

  def processInputLine(input: String, game: Game): Game ={
    input match {
      case "q" => game
      case "m0" => GameInitializer.setGame("Standard", 0, game.player1, game.player2)
      case "m1" => GameInitializer.setGame("Standard", 1, game.player1, game.player2)
      case "p" => GameInitializer.setGame("Standard", 1, "Player1","Player2")
      case "p1l" => GameInitializer.moveLeft(game,game.mapObject.p1)
      case "p1r" => GameInitializer.moveRight(game,game.mapObject.p1)
      case "p2l" => GameInitializer.moveLeft(game,game.mapObject.p2)
      case "p2r" => GameInitializer.moveRight(game,game.mapObject.p2)
    }
  }


}
