# scala-nano-grad
A tiny autograd engine implemented in scala. This is inspired from Andrej Karpathy's [micrograd](https://github.com/karpathy/micrograd), from the YouTube video [The spelled-out intro to neural networks and backpropagation: building micrograd](https://www.youtube.com/watch?v=VMj-3S1tku0&list=PLAqhIrjkxbuWI23v9cThsA9GvCAUhRvKZ&index=4), that implements a pytorch-like API in Python. These are tiny with less than 200 lines of code. Implements backpropogation over a dynamically built DAG and a small neural networks library, both written in scala. While the intention is to learn the internals of neural networks by implementation, this scala version keeps things simple and attempts to replicate the core functionality in functional style. 

Some deviations:
* Created a function to print the DAG in text format, in place of a clear vizualization. Well, it is not as appelling as Andrej's, but it servers the purpose(my purpose) ;)
* Added a Usage.scala file in the project that demonstrates the usage of the classes created.
* As of now, demo, similar to micrograd, is not added. However, tests to validate the functionality are added. These were not validated using PyTorch.

### Example Usage

```scala
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

  println(a + b) // prints Value( data=-1.0 |  | 0.0 )
  println(a * b) // prints Value( data=-6.0 |  | 0.0 )
  println(d.previous) // prints Set(Value( data=-6.0 | e | 0.0 ), Value( data=10.0 | c | 0.0 ))
  println(d.operation) // prints +

  println(build(L, 1)) // prints below
  // |-Value( data=-8.0 | L | 0.0 ) ==> Operation(*)
  // |---Value( data=4.0 | d | 0.0 )
  // |----Value( data=-2.0 | f | 0.0 )
  // 
  // |---Value( data=4.0 | d | 0.0 ) ==> Operation(+)
  // |-----Value( data=-6.0 | e | 0.0 )
  // |------Value( data=10.0 | c | 0.0 )
  // 
  // |-----Value( data=-6.0 | e | 0.0 ) ==> Operation(*)
  // |-------Value( data=2.0 | a | 0.0 )
  // |--------Value( data=-3.0 | b | 0.0 )

}

```

### Running tests
To run the unit tests, you need to run the below command
```bash
gradlew test
```

### Further Improvements
* Implement the first problem of Neural nets, resolving the XOR gate in demo.
* Create an abstract class, Module, and extend for Neuron, Layer and MLP.
* Include the activation functions from micrograd.
* Create the dataset as in [micrograd's demo](https://github.com/karpathy/micrograd/blob/master/demo.ipynb) and the visualizations.(Challenging ones ;) )

### License
MIT