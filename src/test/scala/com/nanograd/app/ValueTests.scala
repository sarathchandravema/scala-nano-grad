package com.nanograd.app

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ValueTests extends AnyFlatSpec with Matchers {

  "Values" should "add correctly return the expected value" in {

    val a = new Value(2.0)
    val b = new Value(-3.0)
    val c = new Value(-1.0)

    a + b shouldBe c
  }

  it should "multiply correctly and return the expected value" in {
    val a = new Value(2.0)
    val b = new Value(-3.0)
    val c = new Value(-6.0)

    a * b shouldBe c
  }

  it should "return the list of operations and the values resulting it" in {
    val a = new Value(2.0)
    val b = new Value(-3.0)
    val c = new Value(10.0)

    val d = a*b
    val e = d + c
    val g = new Value(-6.0)
    val f = new Value(4.0)

    d shouldBe g
    d.previous shouldBe Set(a, b)
    d.operation shouldBe "*"

    e shouldBe f
    e.previous shouldBe Set(c, d)
    e.operation shouldBe "+"
  }

  it should "return the power operations and the values resulting it" in {
    val a = new Value(-3.0)

    val b = a**2
    val c = new Value(9.0)

    b shouldBe c
    b.previous shouldBe Set(a)
    b.operation shouldBe "power"
  }

  it should "return the list of operations and the values resulting it after including label in Value class" in {
    val a = new Value(2.0, label = Some("a"))
    val b = new Value(-3.0, label = Some("b"))
    val c = new Value(10.0, label = Some("c"))
    val f = new Value(-2.0, label = Some("f"))

    val e = a * b; e.label = Some("e")
    val d = e + c; d.label = Some("d")
    val L = d * f; L.label = Some("L")

    d.previous shouldBe Set(e, c)
    d.operation shouldBe "+"
    e.previous shouldBe Set(a, b)
    e.operation shouldBe "*"
    L.previous shouldBe Set(d, f)
    L.operation shouldBe "*"
  }

  it should "return the tanh value for the input" in {
    val a = new Value(0.8813735870195432)
    val b = new Value(0.7071067811865476)
    val c = a.tanh()

    c shouldBe b
    c.previous shouldBe Set(a)
    c.operation shouldBe "tanh"
  }

  it should "return the grads populated using backward functionality" in {
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

    o shouldBe new Value(0.7071067811865476, label = Some("o"))
    o.previous shouldBe Set(n)
    o.operation shouldBe "tanh"

    n shouldBe new Value(0.8813735870195432, label = Some("n"))
    n.previous shouldBe Set(x1w1x2w2, b)
    n.operation shouldBe "+"

    x1w1x2w2 shouldBe new Value(-6.0, label = Some("x1w1 + x2w2"))
    x1w1x2w2.previous shouldBe Set(x1w1, x2w2)
    x1w1x2w2.operation shouldBe "+"

    x1w1 shouldBe new Value(-6.0, label = Some("x1w1"))
    x1w1.previous shouldBe Set(x1, w1)
    x1w1.operation shouldBe "*"

    x2w2 shouldBe new Value(0.0, label = Some("x2w2"))
    x2w2.previous shouldBe Set(x2, w2)
    x2w2.operation shouldBe "*"

    o.grad shouldBe 0.0
    n.grad shouldBe 0.0
    x1w1x2w2.grad shouldBe 0.0
    x1w1.grad shouldBe 0.0
    x2w2.grad shouldBe 0.0

    o.backward()

    o.grad shouldBe 1.0
    n.grad shouldBe 0.4999999999999999
    x1w1x2w2.grad shouldBe 0.4999999999999999
    x1w1.grad shouldBe 0.4999999999999999
    x2w2.grad shouldBe 0.4999999999999999
    x1.grad shouldBe -1.4999999999999996
    x1.grad shouldBe -1.4999999999999996
    x2.grad shouldBe 0.4999999999999999
    w1.grad shouldBe 0.9999999999999998
    w2.grad shouldBe 0.0
  }

  it should "work commutatively for both addition and multiplication" in {
    val x1 = new Value(2.0, label = Some("x1"))
    val r0 = new Value(2.0)
    val r1 = new Value(3.0)
    val r2 = new Value(4.0)

    1 + x1 shouldBe r1
    1 * x1 shouldBe r0
    x1 * BigDecimal(2) shouldBe r2
  }
}
