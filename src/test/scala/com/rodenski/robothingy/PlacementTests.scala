package com.rodenski.robothingy

import com.rodenski.robothingy.dataTypes.{Board, Robot}
import com.rodenski.robothingy.enums.Direction._
import org.scalatest.{FlatSpec, Matchers}

class PlacementTests extends FlatSpec with Matchers {

  val board = Board(5, 5)

  "place" should "return Some(robot) if x and y are on the board" in {

    val robothingy = Simulator.place(4, 4, NORTH, board)
    robothingy should be(Some(Robot(4, 4, NORTH)))

  }

  it should "return None if x is out of bounds" in {

    val robothingy = Simulator.place(6, 4, NORTH, board)
    robothingy should be(None)

  }

  it should "return None if y is out of bounds" in {

    val robothingy = Simulator.place(4, 6, NORTH, board)
    robothingy should be(None)

  }

  it should "return None if x and y are both out of bounds" in {

    val robothingy = Simulator.place(6, 6, NORTH, board)
    robothingy should be(None)

  }
}