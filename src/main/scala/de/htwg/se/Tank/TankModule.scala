package de.htwg.se.Tank

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.Tank.controller.controllerComponent.ControllerInterface
import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase.Game
import de.htwg.se.Tank.model.gameComponent.gameInterface
import net.codingwell.scalaguice.ScalaModule

class TankModule extends AbstractModule with ScalaModule {
  val defaultmap : Int  = 1


  override def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("DefaultMap")).to(defaultmap)
    bind[ControllerInterface].to[Controller]
    bind[gameInterface].to[Game]

  }
}
