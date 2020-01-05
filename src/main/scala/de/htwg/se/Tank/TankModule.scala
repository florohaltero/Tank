package de.htwg.se.Tank

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.Tank.controller.controllerComponent.ControllerInterface
import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase.{BigMap, Game, SmallMap}
import de.htwg.se.Tank.model.gameComponent.{MapSize, gameInterface}
import net.codingwell.scalaguice.ScalaModule

class TankModule extends AbstractModule with ScalaModule {
  val defaultmap : Int  = 1
  val defaultsize : String = "small"


  override def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("DefaultMap")).to(defaultmap)
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultsize)
    bind[ControllerInterface].to[Controller]
    bind[gameInterface].to[Game]
    bind[MapSize].annotatedWithName("small").toInstance(new SmallMap)
    bind[MapSize].annotatedWithName("big").toInstance(new BigMap)

  }
}
