package de.htwg.se.Tank.aview.gui

import java.io.{File, FileInputStream}

import javax.print.DocFlavor.URL
import scalafx.animation.{Animation, PathTransition}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{BorderPane, HBox}
import scalafx.scene.paint.Color.Black
import scalafx.scene.shape.{Circle, Rectangle}
import scalafx.util.Duration

import scala.swing.{MainFrame, Panel}

object animationtest extends JFXApp {

  def show() : Unit = {
    stage = new PrimaryStage {
      scene = new Scene(1000,1000){
        val url = new File("file:Game-Tank-Background-Free-Picture.jpg").toURI().toString
        val image = new Image(url)
        val explosion = new ImageView(image)

        explosion.style= "-fx-background-color: black"
        content = explosion
      }
    }
  }
}

object run {
  def main(args: Array[String]): Unit = {
    print(234)
    animationtest.show
  }
}
