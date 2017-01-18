package com.rodenski.robothingy

import com.rodenski.robothingy.datatypes.{RobotPosition, Table}
import com.rodenski.robothingy.enums.Direction._
import org.scalatest.{FlatSpec, Matchers}

/**
  * The tests here represent the examples in the PROBLEM.md
  */
class ExamplesTests extends FlatSpec with Matchers {

  val table = Table(5, 5)

  "the robot" should """succeed evaluating given the following sequence:
                        |PLACE 0,0,NORTH
                        |MOVE
                        |REPORT
                      """.stripMargin in {

    val position1 = REPL.evaluate( "PLACE 0,0,NORTH".split(' '), table, None)
    val position2 = REPL.evaluate( "MOVE".split(' '), table, position1)
    position2.get should be (RobotPosition(0, 1, NORTH))
  }

  "the robot" should """succeed evaluating given the following sequence:
                       |PLACE 0,0,NORTH
                       |LEFT
                       |REPORT
                     """.stripMargin in {

    val position1 = REPL.evaluate( "PLACE 0,0,NORTH".split(' '), table, None)
    val position2 = REPL.evaluate( "LEFT".split(' '), table, position1)
    position2.get should be (RobotPosition(0, 0, WEST))
  }

  "the robot" should """succeed evaluating given the following sequence:
                       |PLACE 1,2,EAST
                       |MOVE
                       |MOVE
                       |LEFT
                       |MOVE
                       |REPORT
                     """.stripMargin in {

    val position1 = REPL.evaluate( "PLACE 1,2,EAST".split(' '), table, None)
    val position2 = REPL.evaluate( "MOVE".split(' '), table, position1)
    val position3 = REPL.evaluate( "MOVE".split(' '), table, position2)
    val position4 = REPL.evaluate( "LEFT".split(' '), table, position3)
    val position5 = REPL.evaluate( "MOVE".split(' '), table, position4)
    position5.get should be (RobotPosition(3, 3, NORTH))
  }
}
