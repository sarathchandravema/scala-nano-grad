import com.nanograd.app.Value
import com.nanograd.app.utils.Viz.build

object Usage extends App {

  val a = new Value(2.0, label = Some("a"))
  val b = new Value(-3.0, label = Some("b"))
  val c = new Value(10.0, label = Some("c"))
  val f = new Value(-2.0, label = Some("f"))

  val e = a * b; e.label = Some("e")
  val d = e + c; d.label = Some("d")
  val L = d * f; L.label = Some("L")

  println(a + b)
  println(a * b)
  println(d.previous)
  println(d.operation)
  println(build(L, 1))

}
