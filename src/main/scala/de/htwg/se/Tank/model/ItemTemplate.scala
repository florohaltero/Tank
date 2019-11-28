package de.htwg.se.Tank.model

trait ItemTemplate {
  val name :  String
  def item() : Unit
  val value: Integer
}

case class ItemHealth() extends ItemTemplate {
  override val name: String = "Health"

  override def item(): Unit = {
    println("Health")
  }

  override val value: Integer = 25
}

case class ItemMoreMoves() extends ItemTemplate {
  override val name: String = "More Moves"

  override def item(): Unit = {
    println("MORE MOOOOOOVES")
  }

  override val value: Integer = 2
}
