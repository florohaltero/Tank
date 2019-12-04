package de.htwg.se.Tank.controller

object GameStatus extends Enumeration {
  type GameStatus = Value
  val IDLE, HIT, NOHIT = Value

  val map = Map[GameStatus, String] (
    IDLE -> "",
    HIT -> "Treffer!!",
    NOHIT -> "kein Treffer"
  )

  def message(gameStatus: GameStatus): Unit = {
    map(gameStatus)
  }
}
