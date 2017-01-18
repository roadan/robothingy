package com.rodenski.robothingy.enums

/**
  * I do know scala enums are not the best design choice, especially for pattern matching,
  * but I'm using them to avid string matching (since I am parsing strings as inputs)
  */
object Direction extends Enumeration {
  type Direction = Value
  val NORTH, SOUTH, EAST, WEST = Value
}
