import java.awt.event.{KeyEvent, KeyListener}

import com.jogamp.opengl.awt.GLCanvas
import com.jogamp.opengl.util.FPSAnimator
import com.jogamp.opengl.{GL, GL2, GLAutoDrawable, GLCapabilities, GLEventListener, GLProfile}
import javax.swing.{JFrame, WindowConstants}
import models.{Box, Model}
import utils.{Colour, Coord}

import scala.collection.mutable.{Set => MSet}

class TransformationExample extends JFrame with GLEventListener with KeyListener {

  //Class Data
  protected val windowWidth: Int = 600
  protected val windowHeight: Int = 600
  protected val windowName: String = "OpenGL Scala Transformation Demo"
  protected val clearColor: Colour = Colour.BLACK

  private val activeKeys: MSet[Int] = MSet.empty

  private val box: Model = new Box(Coord(0.0, 0.0))

  //OpenGl Setup
  private val profiles: Array[String] = Array(GLProfile.GL2)
  private val profile = GLProfile.get(profiles, true)
  private val capabilities = new GLCapabilities(profile)
  private val animator: FPSAnimator = new FPSAnimator(30)
  private val canvas: GLCanvas = new GLCanvas(capabilities)
  canvas.addGLEventListener(this)
  canvas.addKeyListener(this)
  animator.add(canvas)

  //JFrame Setup
  this.setTitle(windowName)
  this.setName(windowName)
  this.getContentPane.add(canvas)
  this.setSize(windowWidth, windowHeight)
  this.setLocationRelativeTo(null)
  this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  this.setVisible(true)
  this.setResizable(false)
  canvas.requestFocusInWindow()

  def play(): Unit = {
    animator.start()
  }

  override def init(drawable: GLAutoDrawable): Unit = {
    val gl: GL2 = drawable.getGL.getGL2
    gl.glClearColor(clearColor.red, clearColor.green, clearColor.blue, clearColor.alpha)
  }

  override def dispose(drawable: GLAutoDrawable): Unit = {
    animator.stop()
  }

  override def display(drawable: GLAutoDrawable): Unit = {
    processInputs()
    render(drawable)
  }

  override def reshape(drawable: GLAutoDrawable, x: Int, y: Int, width: Int, height: Int): Unit = {

  }

  override def keyTyped(keyEvent: KeyEvent): Unit = {

  }

  override def keyPressed(keyEvent: KeyEvent): Unit = {
    activeKeys.addOne(keyEvent.getKeyCode)
  }

  override def keyReleased(keyEvent: KeyEvent): Unit = {
    activeKeys.subtractOne(keyEvent.getKeyCode)
  }

  private def render(drawable: GLAutoDrawable): Unit = {
    val gl: GL2 = drawable.getGL.getGL2
    gl.glMatrixMode(GL2.GL_MODELVIEW0_ARB)
    gl.glLoadIdentity()
    gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT)
    box.render(gl)
    gl.glFlush()
  }

  private def processInputs(): Unit = {
    for(key <- activeKeys) {
      key match {
        case KeyEvent.VK_W => box.move(amountX = 0.0f, amountY = 0.02f)
        case KeyEvent.VK_S => box.move(amountX = 0.0f, amountY = -0.02f)
        case KeyEvent.VK_A => box.move(amountX = -0.02f, amountY = 0.0f)
        case KeyEvent.VK_D => box.move(amountX = 0.02f, amountY = 0.0f)
        case KeyEvent.VK_Q => box.rotate(amount = 1.0f)
        case KeyEvent.VK_E => box.rotate(amount = -1.0f)
        case KeyEvent.VK_1 => box.updateScale(amount = 0.02f)
        case KeyEvent.VK_2 => box.updateScale(amount = -0.02f)
        case KeyEvent.VK_R => box.reset()
        case _ => ()
      }
    }
  }
}

object TransformationExample {
  def main(args: Array[String]): Unit = {
    val example = new TransformationExample
    example.play()
  }
}
