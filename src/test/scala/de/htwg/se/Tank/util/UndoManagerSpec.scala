package de.htwg.se.Tank.controller

import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.Game
import de.htwg.se.Tank.util.{Command, Observer, UndoManager}
import org.scalatest.{Matchers, WordSpec}

class UndoManagerSpec extends WordSpec with Matchers {
  "Undo Manger" when { "new " should {
    val undomanager = new UndoManager
    undomanager.undoStep
    "undo Step" in {
      undomanager.undoStep should be (undomanager.undoStep)
    }
    "redo Step" in {
      undomanager.redoStep should be (undomanager.redoStep)
    }
  }}
}
