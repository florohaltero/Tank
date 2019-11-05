package de.htwg.se.Tank.model

case class Round() {
  private val moveTime = 10000
  private val roundTime = 60000
  private var move = true
  Timer(moveTime, false) {
    println("Move time abgelaufem")
    move = false
  }

  roundtime()
  def roundtime() {
    Timer(roundTime, false) {
      println("Round Time abgelaufen")
    }
  }
}
