import com.nanograd.app.Value
import com.nanograd.app.utils.Viz.build

object Usage extends App {

  val a = new Value(2.0)
  val b = new Value(-3.0)
  val c = new Value(10.0)

  val d = a*b + c

  println(a + b)
  println(a * b)
  println(a ** 3)
  println(d.previous)
  println(d.operation)
  println(build(d, 1))

}
