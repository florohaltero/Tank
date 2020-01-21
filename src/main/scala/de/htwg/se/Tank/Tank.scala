package de.htwg.se.Tank

import com.google.inject.Guice
import de.htwg.se.Tank.aview.TUI
import de.htwg.se.Tank.aview.gui.MapGUI
import de.htwg.se.Tank.controller.controllerComponent.{ControllerInterface, NewGame}
import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase.Game
import javafx.application.Application
import scalafx.application.JFXApp
import scalafx.scene.media.AudioClip


object Tank {
  val injector = Guice.createInjector(new TankModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  def main(args: Array[String]): Unit = {
    val lines = scala.io.Source.fromFile("Tank_label.txt").mkString
    print(lines)

    var input : String = ""
    val tui = new TUI(controller)
    val gui = new MapGUI(controller)
    gui.main(Array())
    controller.publish(new NewGame)
    do {
      input = scala.io.StdIn.readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}
