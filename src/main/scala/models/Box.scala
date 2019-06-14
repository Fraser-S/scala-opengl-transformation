package models


import com.jogamp.opengl.GL2
import utils.{Colour, Coord}

class Box(initialPosition: Coord) extends Model {

  this.position = initialPosition
  this.vertices = Seq(Coord(-1.0, 1.0), Coord(-1.0, -1.0), Coord(1.0, -1.0), Coord(1.0, 1.0))
  this.colours = Seq(Colour.RED, Colour.BLUE, Colour.GREEN, Colour.WHITE)
  this.scale = 0.5f

  override def reset(): Unit = {
    this.position = initialPosition
    this.scale = 0.5f
    this.rotation = 0.0f
  }

  override def render(gl: GL2): Unit = {
    gl.glTranslatef(position.x.toFloat, position.y.toFloat, 1.0f)
    gl.glRotatef(rotation, 0.0f, 0.0f, 1.0f)
    gl.glScalef(scale, scale, scale)
    gl.glBegin(GL2.GL_POLYGON)
    colours.length match {
      case size if size == vertices.size => renderMultipleColours(gl)
      case _ => renderSingleColour(gl)
    }
    gl.glEnd()
  }

  private def renderSingleColour(gl: GL2): Unit = {
    gl.glColor4f(colours.head.red, colours.head.green, colours.head.blue, colours.head.alpha)
    for(vertex <- vertices) {
      gl.glVertex2d(vertex.x, vertex.y)
    }
  }


  private def renderMultipleColours(gl: GL2): Unit = {
    for(index <- vertices.indices) {
      gl.glColor4f(colours(index).red, colours(index).green, colours(index).blue, colours(index).alpha)
      gl.glVertex2d(vertices(index).x, vertices(index).y)
    }
  }
}
