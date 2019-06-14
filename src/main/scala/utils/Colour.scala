package utils

case class Colour(red: Float, green: Float, blue: Float, alpha: Float)

object Colour {
  val WHITE: Colour = Colour(red = 1.0f, green = 1.0f, blue = 1.0f, alpha = 1.0f)
  val BLACK: Colour = Colour(red = 0.0f, green = 0.0f, blue = 0.0f, alpha = 1.0f)
  val RED: Colour = Colour(red = 1.0f, green = 0.0f, blue = 0.0f, alpha = 1.0f)
  val GREEN: Colour = Colour(red = 0.0f, green = 1.0f, blue = 0.0f, alpha = 1.0f)
  val BLUE: Colour = Colour(red = 0.0f, green = 0.0f, blue = 1.0f, alpha = 1.0f)
  val CYAN: Colour = Colour(red = 0.0f, green = 1.0f, blue = 1.0f, alpha = 1.0f)
  val MAGENTA: Colour = Colour(red = 1.0f, green = 0.0f, blue = 1.0f, alpha = 1.0f)
  val YELLOW: Colour = Colour(red = 1.0f, green = 1.0f, blue = 0.0f, alpha = 1.0f)
}
