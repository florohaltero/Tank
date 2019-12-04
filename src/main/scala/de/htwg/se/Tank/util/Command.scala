package de.htwg.se.Tank.util

trait Command {
  def doStep: Unit
  def undoStep: Unit
  def redoStep: Unit
}
