package de.htwg.se.Tank.model

trait ItemTemplate {
  val name :  String
  def item() : Unit
  val value: String
}

case class ItemHealth() extends ItemTemplate {
  override val name: String = "Health"

  override def item(): Unit = {
    println("Health")
  }

  override val value: String = ""
}

case class ItemMoreDmg() extends ItemTemplate {
  override val name: String = "More Damage"

  override def item(): Unit = {
    println("Damage")
  }

  override val value: String = ""
}
