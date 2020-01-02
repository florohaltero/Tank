package de.htwg.se.Tank.model

import de.htwg.se.Tank.model.playerComponent.playerBase.Player

trait ItemTemplate {
  val name :  String
  def activateItem(player: Player): Unit = {
    if(!isItemAlreadyAvailable(player)) {
      collect(player)
      println(msgItem())
    } else {
      println(msgItemUsed())
      return
    }
    useItem(player)
  }
  def useItem(player: Player): Unit
  def msgItem() : String
  val value: Integer
  def msgItemUsed() : String = {
    "Item already used"
  }
  def collect(player: Player): Unit ={
    player.ItemList.append(this)
  }
  def isItemAlreadyAvailable(player: Player): Boolean = {
    player.ItemList.contains(this)
  }
}

case class ItemHealth() extends ItemTemplate {
  override val name: String = "Health"
  override def useItem(player: Player): Unit = {
    player.tank.lp + value
  }
  override val value: Integer = 25
  override def msgItem(): String = {
    "Health Item"
  }
}

case class ItemMoreMoves() extends ItemTemplate {
  override val name: String = "More Moves"
  override def useItem(player: Player): Unit = {
    //use more moves
  }
  override val value: Integer = 2
  override def msgItem(): String = {
    "More Moves Item"
  }
}
