package de.htwg.se.Tank.aview.gui

import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.Game

object test {
  val controller = new Controller(Game("Standard", 0, "Flo", "Sasch"))
  val gui = new MapGUI(controller)
}
