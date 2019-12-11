package de.htwg.se.Tank.aview.gui


import java.io.File

import de.htwg.se.Tank.aview.TUI
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Orientation, Pos}
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{Background, BorderPane, FlowPane, HBox, StackPane, TilePane, VBox}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.text.Text
import scalafx.Includes._
import scalafx.event._
import scalafx.scene.control.{Alert, Button, CheckBox, Label, Menu, MenuBar, MenuItem, SplitPane, Tab, TabPane, TextArea, TextField}
import scalafx.scene.shape.{Box, Circle, Polygon, Polyline, Rectangle}
import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.{Game, GameInit, Map, Position}
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.media.{AudioClip, Media, MediaPlayer}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.swing.BoxPanel
//noinspection ScalaStyle
object MapGUI extends JFXApp {

  final val WIDTH : Double = 1000
  final val HEIGHT : Double = 600
  final val XScale: Double = WIDTH/GameInit.MAP_X2
  final val YScale: Double = HEIGHT/GameInit.MAP_Y2

  val rootPane = new BorderPane
  val controller = new Controller(Game("Standard", 0, "Flo", "Sasch"))
  GameInit.setMapSettings(0, "Sascha", "Flo")
  val map = Map
  val mainPane = new BorderPane
  val bottomBox = new HBox

  val path = "masterofpuppets.mp3"
  val res = getClass.getResource(path)
  val clip = new AudioClip(res.toString)
  clip.play()
  createMap


  def getGUIScale(d : List[((Double),(Double))]) : List[Double] = {
    var l: ListBuffer[Double] = ListBuffer.empty
    d.foreach(d => l.append(d._1 * XScale, (GameInit.MAP_Y2 - d._2) * YScale))
    l.toList
  }

  def getPosinGUIScale(value : Position) : ((Double),(Double)) = {
    (value.x*XScale,(GameInit.MAP_Y2 - value.y)*YScale)
  }

  def createMap() {
    stage = new PrimaryStage{
      title = "Map"
      scene = new Scene {
        createMenuBar
        createMapShapes
        createMapButtons
        createTitle
        rootPane.center =  mainPane
        rootPane.bottom = bottomBox
        //rootPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #dc143c, #661a33)")
        rootPane.setStyle("-fx-background-color: lightblue")
        root = rootPane


      }
    }
  }

  private def createMenuBar = {
    val menuBar = new MenuBar
    val fileMenu = new Menu("New")
    val newGame = new MenuItem("New")
    val options = new Menu("Options")
    val exitGame = new MenuItem("Exit")
    val undo = new MenuItem("Undo")
    val redo = new MenuItem("Redo")
    options.items = List(undo, redo)
    fileMenu.items = List(newGame, exitGame)
    menuBar.menus = List(fileMenu, options)
    rootPane.top= menuBar
  }



  def createMapShapes = {
    var lb : mutable.Buffer[Double] = getGUIScale(Map.getFXList(true)).toBuffer
    lb.append(WIDTH)
    lb.append(HEIGHT)
    lb.append(0)
    lb.append(HEIGHT)
    val guiscale = lb.toList
    val line = Polygon(guiscale: _*)
    line.fill = Green
    line.layoutX = 0
    line.layoutY = 0
    val t1 = new Rectangle {
      val p1 = getPosinGUIScale(Map.p1.pos)
      height = 10
      width = 20
      x = p1._1 - 0.5*width()
      y = p1._2 - 0.5*height()
      fill = Blue


    }
    val t2 = new Rectangle {
      val p2 = getPosinGUIScale(Map.p2.pos)
      height = 10
      width = 20
      x = p2._1 - 0.5*width()
      y = p2._2 - 0.5*height()
      fill = Red
    }
    mainPane.prefWidth = WIDTH
    mainPane.prefHeight = HEIGHT
    mainPane.children.addAll(line,t1,t2)

  }

  def createMapButtons ={

    val moveLeft = new Button("Move Left"){
      onAction = (e:ActionEvent) => {
        controller.moveLeft()
      }
      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.A => controller.moveLeft()
          case _ =>
        }
      }
    }

    val moveRight = new Button("Move Right"){
      onAction = (e:ActionEvent) => {
        controller.moveRight()
      }
    }
    val angleUp = new Button("Cannon +"){
      onAction = (e:ActionEvent) => {
        controller.moveAngleUp()
      }
    }
    val angelDown = new Button("Cannon -"){
      onAction = (e:ActionEvent) => {
        controller.moveAngleDown()
      }
    }
    val power = new TextField

    val fire = new Button("Fire!"){
      onAction = (e:ActionEvent) => {
        controller.shoot()
      }
      onKeyTyped = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Space => controller.shoot()
          case _ =>
        }
      }
    }
    bottomBox.children.addAll(moveLeft,moveRight,angleUp,angelDown,power,fire)
  }

  private def createTitle : VBox = new VBox{
    val name = new Text() {
      text = "Tank"
      style = "-fx-font-size: 60pt; "
      alignmentInParent = Pos.TopCenter
      fill = Black
    }
    val infoPlayer1 = new Text(){
      text = Map.p1.toString
    }
    val infoPlayer2 = new Text(){
      text = Map.p2.toString
    }
    val vbox = new VBox{
      children.addAll(name,infoPlayer1,infoPlayer2)
    }
    mainPane.children.add(vbox)
  }

  def showFire ={

  }

  private def createButton : TilePane = new TilePane(){
    //prefTileWidth = 300

    style = "-fx-background-color: green"
    prefHeight = 100
    children = List(
      new Button("New Game") {
        scaleX = 2
        scaleY = 2
        alignmentInParent = Pos.CenterRight
        onAction = (e: ActionEvent) => createMap()
      },
      new Button("Exit Game") {
        scaleX = 2
        scaleY = 2
        alignmentInParent = Pos.CenterLeft
        onAction = (e: ActionEvent) => System.exit(0)
      })

  }

}