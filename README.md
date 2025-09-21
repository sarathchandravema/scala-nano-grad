# scala-nano-grad
A tiny autograd engine implemented in scala. This is inspired from Andrej Karpathy's [micrograd](https://github.com/karpathy/micrograd), from the YouTube video [Spelled-out-intro](https://www.youtube.com/watch?v=VMj-3S1tku0&list=PLAqhIrjkxbuWI23v9cThsA9GvCAUhRvKZ&index=4), that implements a pytorch-like API in Python. Implements backpropogation over a dynamically built DAG and a small neural networks library, both written in scala. While the intention is to learn the internals of neural networks by implementation, this scala version keeps things simple and attempts to replicate the core functionality in functional style.

### Example Usage

```scala
import com.nanograd.app.Value

object Usage extends App {

  val a = new Value(2.0)
  val b = new Value(-3.0)

  val c = a + b
  println(c) // prints Value( data=-1.0 )
}
```

### Running tests
To run the unit tests, you need to run the below command
```bash
gradlew test
```
