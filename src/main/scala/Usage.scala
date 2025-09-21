import com.nanograd.app.Value

object Usage extends App {

  val a = new Value(2.0)
  val b = new Value(-3.0)

  val c = a + b
  println(c)
}
