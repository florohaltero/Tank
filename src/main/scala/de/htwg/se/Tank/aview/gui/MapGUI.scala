package de.htwg.se.Tank.aview.gui


import java.io.File

import de.htwg.se.Tank.aview.TUI
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{BorderPane, FlowPane, HBox, StackPane, TilePane}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.{Alert, Button, CheckBox, Label, Menu, MenuBar, MenuItem, SplitPane, Tab, TabPane}
import scalafx.scene.shape.{Circle, Polyline, Rectangle}
import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.{Game, GameInit, Map, Position}
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.media.{AudioClip, Media, MediaPlayer}

import scala.collection.mutable.ListBuffer
import scala.swing.BorderPanel
//noinspection ScalaStyle
object MapGUI extends JFXApp {

  final val WIDTH : Double = 1000
  final val HEIGHT : Double = 600
  final val XScale: Double = WIDTH/GameInit.MAP_X2
  final val YScale: Double = HEIGHT/GameInit.MAP_Y2

  def getGUIScale(d : List[((Double),(Double))]) : List[Double] = {
    var l: ListBuffer[Double] = ListBuffer.empty
    d.foreach(d => l.append(d._1 * XScale, (GameInit.MAP_Y2 - d._2) * YScale))
    l.toList
  }

  def getPosinGUIScale(value : Position) : ((Double),(Double)) = {
    (value.x*XScale,(GameInit.MAP_Y2 - value.y)*YScale)
  }

  val MainPane = new BorderPane
  val controller = new Controller(Game("Standard", 0, "Flo", "Sasch"))
  createMap

    private def createMenuBar : MenuBar = new MenuBar {
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
      menuBar
    }

  private def createTitle : StackPane = new StackPane(){
    layoutY = 50
    style = "-fx-background-color: green"
    children = Seq(
      new Text() {
        text = "Tank"
        style = "-fx-font-size: 60pt; "
        alignmentInParent = Pos.TopCenter
        fill = Black
      })
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

  def createMap() {
    stage = new PrimaryStage{
      title = "Map"
      scene = new Scene(WIDTH,HEIGHT) {
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

        MainPane.top = menuBar
        val TabPane = new TabPane
        TabPane += create()

        MainPane.center = TabPane
        //border.bottom = createButton
        root = MainPane

      }

    }
  }

  def create() : Tab ={


      GameInit.setMapSettings(0, "Sascha", "Flo")
      val map = Map
      val guiscale = getGUIScale(Map.getFXList(true))
      val line: Polyline = Polyline(guiscale: _*)
      line.layoutX = 0
      line.layoutY = 0
      val t1 = new Rectangle {
        val p1 = getPosinGUIScale(Map.p1.pos)
        layoutX = p1._1
        layoutY = p1._2
        height = 10
        width = 20
      }
      val t2 = new Rectangle {
        val p2 = getPosinGUIScale(Map.p2.pos)
        layoutX = p2._1
        layoutY = p2._2
        height = 10
        width = 20
      }
      //content = List(line, t1, t2)

    val split = new SplitPane
    val MapPane = new BorderPane
    val canvtest = new Canvas
    MapPane.center = List(line,t1,t2)

    split.items ++=  List(MapPane)
    val tab = new Tab
    tab.text = "hallo"
    tab.content = split
    tab
  }


      /*
      val border = new BorderPane{
        top = menuBar
        bottom = showText()
      }*/

      //content = List(rootPane,h)
      /*content = new HBox {
        padding = Insets(20)
        children = new Text(){
          text = "Tank"
          style = "-fx-font-size: 80pt; -fx-alignment: CENTER;"
          fill = Black
          TextAlignment.CENTER


        }
        children = Seq(
          new Text {
            text = "Hello "
            style = "-fx-font-size: 48pt"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(PaleGreen, SeaGreen))
          },
          new Text {
            text = "World!!!"
            style = "-fx-font-size: 48pt"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(Cyan, DodgerBlue)
            )
            effect = new DropShadow {
              color = DodgerBlue
              radius = 25
              spread = 0.25
            }
          }
        )
      }*/


}