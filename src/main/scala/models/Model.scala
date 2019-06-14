package models

import com.jogamp.opengl.GL2
import utils.{Colour, Coord}

trait Model {

  protected var position: Coord = Coord(0.0, 0.0)
  protected var scale: Float = 1.0f
  protected var colours: Seq[Colour] = Seq(Colour.WHITE)
  protected var vertices: Seq[Coord] = Seq.empty
  protected var rotation: Float = 0.0f

  def reset(): Unit

  def move(amountX: Double, amountY: Double): Unit = {
    this.position = Coord(this.position.x + amountX, this.position.y + amountY)
  }

  def updateScale(amount: Float) : Unit = {
    this.scale += amount
  }

  def rotate(amount: Float): Unit = {
    this.rotation += amount
  }

  def render(gl: GL2): Unit
}
