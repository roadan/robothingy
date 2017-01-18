package com.rodenski.robothingy

import com.rodenski.robothingy.datatypes.{Table, RobotPosition}
import com.rodenski.robothingy.enums.Direction._
import org.scalatest.{FlatSpec, Matchers}

class MovementTests extends FlatSpec with Matchers {

  val table = Table(5, 5)

  "a robot" should "move North if not on the northern boundary" in {

    val position = Some(RobotPosition(0, 0, NORTH))
    val result = Robot.move(position, table).get
    result should be(RobotPosition(0, 1, NORTH))

  }

  it should "not move if North if it is on the northern boundary" in {

    val position = Some(RobotPosition(0, 5, NORTH))
    val result = Robot.move(position, table).get
    result should be(RobotPosition(0, 5, NORTH))

  }

  it should "move South if if it is not on the southern boundary" in {

    val position = Some(RobotPosition(0, 5, SOUTH))
    val result = Robot.move(position, table).get
    result should be(RobotPosition(0, 4, SOUTH))

  }

  it should "not move South if  on the southern boundary" in {

    val position = Some(RobotPosition(0, 0, SOUTH))
    val result = Robot.move(position, table).get
    result should be(RobotPosition(0, 0, SOUTH))

  }

  it should "move if South if it is not on the southern boundary" in {

    val position = Some(RobotPosition(0, 5, SOUTH))
    val result = Robot.move(position, table).get
    result should be(RobotPosition(0, 4, SOUTH))

  }

  it should "move East if it is not on the eastern boundary" in {

    val position = Some(RobotPosition(0, 0, EAST))
    val result = Robot.move(position, table).get
    result should be(RobotPosition(1, 0, EAST))

  }

  it should "not move East if it is on the eastern boundary" in {

    val position = Some(RobotPosition(5, 0, EAST))
    val result = Robot.move(position, table).get
    result should be(RobotPosition(5, 0, EAST))

  }

  it should "move West if it is not on the eastern boundary" in {

    val position = Some(RobotPosition(5, 0, WEST))
    val result = Robot.move(position, table).get
    result should be(RobotPosition(4, 0, WEST))

  }

  it should "not move West if it is on the eastern boundary" in {

    val position = Some(RobotPosition(0, 0, WEST))
    val result = Robot.move(position, table).get
    result should be(RobotPosition(0, 0, WEST))

  }
}
