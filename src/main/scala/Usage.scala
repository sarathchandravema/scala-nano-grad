import com.nanograd.app._
import com.nanograd.app.utils.Viz.build

object Usage extends App {

  //inputs
  val x1 = new Value(2.0, label = Some("x1"))
  val x2 = new Value(0.0, label = Some("x2"))

  //weights
  val w1 = new Value(-3.0, label = Some("w1"))
  val w2 = new Value(1.0, label = Some("w2"))

  //bias
  val b = new Value(6.8813735870195432, label = Some("b"))

  //x1w1 + x2w2 + b
  val x1w1 = x1 * w1; x1w1.label = Some("x1w1")
  val x2w2 = x2 * w2; x2w2.label = Some("x2w2")
  val x1w1x2w2 = x1w1 + x2w2; x1w1x2w2.label = Some("x1w1 + x2w2")

  val n = x1w1x2w2 + b; n.label = Some("n")
  val o = n.tanh(); o.label = Some("o")

  println("Demonstrating the backprop and viz")
  println(build(o, 1))

  o.backward()

  println(build(o, 1))

  val nu = new Neuron(4)
  println(nu.weights)
  println(nu.bias)
  val inp1 = Seq(new Value(1.5), new Value(1.5), new Value(1.5), new Value(1.5))
  nu.weights = Seq(new Value(0.1), new Value(0.2), new Value(0.3), new Value(0.4) )

  println("\n\nDemonstrating the neuron calculations")
  println(nu(input = inp1))

  val inp2 = Seq(new Value(1.5), new Value(1.5))
  val l1 = new Layer(2, 3)
  println("\n\nDemonstrating the layer calculations")
  println(l1(inputs = inp2))

  val nn = new MLP(3, Seq(4, 4, 1))
  val xs = Seq(
    Seq(2.0, 3.0, -1.0),
    Seq(3.0, -1.0, 0.5),
    Seq(0.5, 1.0, 1.0),
    Seq(1.0, 1.0, -1.0)
  )
  val ys = Seq( 1.0, -1.0, -1.0, 1.0 )

  println("\n\nDemonstrating the MLP calculations")
  val iters = 0 until 20
  for (k <- iters) {
    val pred = xs.map(nn(_))
    val loss = ys.zip(pred).map(x => (x._1 - x._2.head)**2).fold(new Value(0.0))(_ + _)

    for (i <- nn.parameters()){
      i.grad = 0.0
    }

    loss.backward()

    for (i <- nn.parameters()){
      i.data += -0.05*i.grad
    }

    println(s"$k ==> ${loss.data}")
    pred.foreach(println)
  }

}
