package de.htwg.se.Tank.model

trait ItemTemplate {
  val name :  String
  def collectItem(player: Player): Unit = {
    player.ItemList.append(this)
  }
  def useItem(player: Player): Unit

  val value: Integer
}

case class ItemHealth() extends ItemTemplate {
  override val name: String = "Health"

  override def useItem(player: Player): Unit = {
    player.lp + value
    println("Name: " + player.name + "Life: " + player.lp)
  }
  override val value: Integer = 25
}

case class ItemMoreMoves() extends ItemTemplate {
  override val name: String = "More Moves"

  override def useItem(player: Player): Unit = {
    println("Moves + 1")
  }

  override val value: Integer = 2
}
