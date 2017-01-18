package com.rodenski.robothingy

import com.rodenski.robothingy.datatypes.{Table, RobotPosition}
import com.rodenski.robothingy.enums.Direction._
import org.scalatest.{FlatSpec, Matchers}

class PlacementTests extends FlatSpec with Matchers {

  val table = Table(5, 5)

  "place" should "return Some(robot) if x and y are on the table" in {

    val position = Robot.place(4, 4, NORTH, table)
    position should be(Some(RobotPosition(4, 4, NORTH)))

  }

  it should "return None if x is out of bounds" in {

    val position = Robot.place(6, 4, NORTH, table)
    position should be(None)

  }

  it should "return None if y is out of bounds" in {

    val position = Robot.place(4, 6, NORTH, table)
    position should be(None)

  }

  it should "return None if x and y are both out of bounds" in {

    val position = Robot.place(6, 6, NORTH, table)
    position should be(None)

  }
}