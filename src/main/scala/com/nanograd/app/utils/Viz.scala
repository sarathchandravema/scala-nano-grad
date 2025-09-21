package com.nanograd.app.utils

import com.nanograd.app.Value

object Viz {

  /**
   * Function to print the graph in a text style. Well, this is not perfect, but it serves the purpose.
   * @param item
   * @param count
   */
  def build(item: Value, count: Int): Unit = {
    if (item.children.isEmpty) {
      print("")
    } else {
      println(s"\n|${"-"*count}$item ==> Operation(${item.operation})\n|${"-"*(count+2)}${item.children.mkString("\n|" + "-"*(count+3))}")
      item.children.foreach(x => build(x, count + 2))
    }
  }

}
