import com.nanograd.app.{Foo, Value}
import com.nanograd.app.utils.Viz.build

object Usage extends App {

  //inputs
  val x1 = new Value(2.0, label = Some("x1"))
  val x2 = new Value(0.0, label = Some("x2"))

  //weights
//  val w1 = new Value(-3.0, label = Some("w1"))
//  val w2 = new Value(1.0, label = Some("w2"))
//
//  //bias
//  val b = new Value(6.8813735870195432, label = Some("b"))
//
//  //x1w1 + x2w2 + b
//  val x1w1 = x1 * w1; x1w1.label = Some("x1w1")
//  val x2w2 = x2 * w2; x2w2.label = Some("x2w2")
//  val x1w1x2w2 = x1w1 + x2w2; x1w1x2w2.label = Some("x1w1 + x2w2")
//
//  val n = x1w1x2w2 + b; n.label = Some("n")
//  val o = n.tanh(); o.label = Some("o")
//
//  println(build(o, 1))
//
//  o.backward()
//
//  println(build(o, 1))

//  println(x1 + 1.0)
//  println(1.0 + x1)

  val f1 = new Foo(1.0)
  println(f1 + 1)
  println(f1 + 1.0)
  println(1 + f1)

  println(1 + x1)
  println(1.0 + x1)
  println(x1 + 1)

}
