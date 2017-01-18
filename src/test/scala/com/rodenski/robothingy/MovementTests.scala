package com.rodenski.robothingy

import com.rodenski.robothingy.dataTypes.{Board, Robot}
import com.rodenski.robothingy.enums.Direction._
import org.scalatest.{FlatSpec, Matchers}

class MovementTests extends FlatSpec with Matchers {

  val board = Board(5, 5)

  "a robot" should "move North if not on the northern boundary" in {

    val robothingy = Some(Robot(0, 0, NORTH))
    val result = Simulator.move(robothingy, board).get
    result should be(Robot(0, 1, NORTH))

  }

  it should "not move if North if it is on the northern boundary" in {

    val robothingy = Some(Robot(0, 5, NORTH))
    val result = Simulator.move(robothingy, board).get
    result should be(Robot(0, 5, NORTH))

  }

  it should "move South if if it is not on the southern boundary" in {

    val robothingy = Some(Robot(0, 5, SOUTH))
    val result = Simulator.move(robothingy, board).get
    result should be(Robot(0, 4, SOUTH))

  }

  it should "not move South if  on the southern boundary" in {

    val robothingy = Some(Robot(0, 0, SOUTH))
    val result = Simulator.move(robothingy, board).get
    result should be(Robot(0, 0, SOUTH))

  }

  it should "move if South if it is not on the southern boundary" in {

    val robothingy = Some(Robot(0, 5, SOUTH))
    val result = Simulator.move(robothingy, board).get
    result should be(Robot(0, 4, SOUTH))

  }

  it should "move East if it is not on the eastern boundary" in {

    val robothingy = Some(Robot(0, 0, EAST))
    val result = Simulator.move(robothingy, board).get
    result should be(Robot(1, 0, EAST))

  }

  it should "not move East if it is on the eastern boundary" in {

    val robothingy = Some(Robot(5, 0, EAST))
    val result = Simulator.move(robothingy, board).get
    result should be(Robot(5, 0, EAST))

  }

  it should "move West if it is not on the eastern boundary" in {

    val robothingy = Some(Robot(5, 0, WEST))
    val result = Simulator.move(robothingy, board).get
    result should be(Robot(4, 0, WEST))

  }

  it should "not move West if it is on the eastern boundary" in {

    val robothingy = Some(Robot(0, 0, WEST))
    val result = Simulator.move(robothingy, board).get
    result should be(Robot(0, 0, WEST))

  }
}
