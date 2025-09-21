package com.nanograd.app

object BasicTest extends App {

  val a = new Value(2.0)
  val b = new Value(-3.0)

//  println(a.add(b))
  println(a + b)
  println(a == b)
}
