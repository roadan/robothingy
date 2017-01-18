package com.rodenski.robothingy

import com.rodenski.robothingy.dataTypes.{Board, Robot}
import com.rodenski.robothingy.enums.Direction._

/**
  * This object contains the functions to control the robot
  */
object Simulator {

  /**
    *
    * @param x
    * @param y
    * @param dir
    * @param board
    * @return
    */
  def place(x: Int, y: Int, dir: Direction, board: Board): Option[Robot] = {

    if (x <= board.x && y <= board.y) {
      Some(Robot(x, y, dir))
    } else {
      None
    }

  }

  def move(robot: Option[Robot], board: Board): Option[Robot] = {

    // Note: all out of bound movements are ignored as per the PROBLEM.md
    robot match {
      case Some(r) =>
        robot.get.dir match {
          case NORTH => if (r.y < board.x) Some(Robot(r.x, r.y + 1, NORTH)) else robot
          case SOUTH => if (r.y > 0) Some(Robot(r.x, r.y - 1, SOUTH)) else robot
          case EAST => if (r.x < board.x) Some(Robot(r.x + 1, r.y, EAST)) else robot
          case WEST => if (r.x > 0) Some(Robot(r.x - 1, r.y, WEST)) else robot
        }
      case None => None
    }
  }

  def left(robot: Option[Robot]): Option[Robot] = {

    robot match {
      case Some(r) =>
        Some(r.dir match {
          case NORTH => Robot(r.x, r.y, WEST)
          case WEST => Robot(r.x, r.y, SOUTH)
          case SOUTH => Robot(r.x, r.y, EAST)
          case EAST => Robot(r.x, r.y, NORTH)
        })
      case None => None
    }

  }

  def right(robot: Option[Robot]): Option[Robot] = {

    robot match {
      case None => None
      case Some(r) =>
        Some(r.dir match {
          case NORTH => Robot(r.x, r.y, EAST)
          case EAST => Robot(r.x, r.y, SOUTH)
          case SOUTH => Robot(r.x, r.y, WEST)
          case WEST => Robot(r.x, r.y, NORTH)
        })
    }

  }
}