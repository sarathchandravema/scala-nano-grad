package com.nanograd.app

class Person (var name: String, var age: Int) {

  override def toString: String = s"Person( $name , $age)"

  def canEqual(a: Any): Boolean = a.isInstanceOf[Person]

  // Step 3 - proper signature for `equals`
  // Steps 4 thru 7 - implement a `match` expression
  override def equals(that: Any): Boolean =
    that match {
      case that: Person => {
        that.canEqual(this) &&
          this.name == that.name &&
          this.age == that.age
      }
      case _ => false
    }
}
