package com.nanograd.app

class Value (var data: Double
            ) {

  override def toString: String = s"Value( data=${this.data} )"

  def ==(that: Value): Boolean = this.data == that.data

  override def equals(that: Any): Boolean = that match {
    case that: Value => that.isInstanceOf[Value] && this == that
    case _ => false
  }

  def +(that: Value): Value = {
    val out = new Value(this.data + that.data)

    out
  }

  def *(that: Value): Value = {
    val out = new Value(this.data * that.data)

    out
  }
}
