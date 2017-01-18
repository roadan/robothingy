package com.rodenski.robothingy

import com.rodenski.robothingy.datatypes.{Table, RobotPosition}
import com.rodenski.robothingy.enums.Direction._

/**
  * This object contains the functions to control the robot
  */
object Robot {

  def place(x: Int, y: Int, dir: Direction, table: Table): Option[RobotPosition] = {

    if (x <= table.x && y <= table.y) {
      Some(RobotPosition(x, y, dir))
    } else {
      None
    }

  }

  def move(robot: Option[RobotPosition], table: Table): Option[RobotPosition] = {

    // Note: all out of bound movements are ignored as per the PROBLEM.md
    robot match {
      case Some(r) =>
        robot.get.dir match {
          case NORTH => if (r.y < table.x) Some(RobotPosition(r.x, r.y + 1, NORTH)) else robot
          case SOUTH => if (r.y > 0) Some(RobotPosition(r.x, r.y - 1, SOUTH)) else robot
          case EAST => if (r.x < table.x) Some(RobotPosition(r.x + 1, r.y, EAST)) else robot
          case WEST => if (r.x > 0) Some(RobotPosition(r.x - 1, r.y, WEST)) else robot
        }
      case None => None
    }
  }

  def left(robot: Option[RobotPosition]): Option[RobotPosition] = {

    robot match {
      case Some(r) =>
        Some(r.dir match {
          case NORTH => RobotPosition(r.x, r.y, WEST)
          case WEST => RobotPosition(r.x, r.y, SOUTH)
          case SOUTH => RobotPosition(r.x, r.y, EAST)
          case EAST => RobotPosition(r.x, r.y, NORTH)
        })
      case None => None
    }

  }

  def right(robot: Option[RobotPosition]): Option[RobotPosition] = {

    robot match {
      case None => None
      case Some(r) =>
        Some(r.dir match {
          case NORTH => RobotPosition(r.x, r.y, EAST)
          case EAST => RobotPosition(r.x, r.y, SOUTH)
          case SOUTH => RobotPosition(r.x, r.y, WEST)
          case WEST => RobotPosition(r.x, r.y, NORTH)
        })
    }

  }
}