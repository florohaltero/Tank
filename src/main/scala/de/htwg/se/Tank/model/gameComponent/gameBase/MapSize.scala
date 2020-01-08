package de.htwg.se.Tank.model.gameComponent.gameBase

import de.htwg.se.Tank.model.gameComponent.MapSize

class SmallMap extends MapSize{
  override val MAP_X = 100
  override val MAP_Y = 30

  override def getName: String = "small"
}

class BigMap extends MapSize{
  override val MAP_X = 200
  override val MAP_Y = 50

  override def getName: String = "big"
}

