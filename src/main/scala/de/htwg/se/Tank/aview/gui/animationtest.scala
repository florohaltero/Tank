package de.htwg.se.Tank.aview.gui

import scalafx.animation.PathTransition
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.{Circle, Rectangle}
import scalafx.util.Duration

import scala.swing.{MainFrame, Panel}

object animationtest {

  def main(args: Array[String]): Unit = {
    val frame = new MainFrame(){

    }

    val panel = new Panel {
      val rect = new Rectangle()
      rect.width = 200
      rect.height = 200
      rect.x = 30
      rect.y = 30
      rect.fill = Black
      val mun = Circle(200,Black)
      val fire = new PathTransition(new Duration(100000),rect)
      //fire.node = mun
      fire.setNode(mun)
      fire.setRate(200)
      fire.setPath(rect)
      fire.play()
    }
    frame.contents = panel
    frame.open()
  }

}
