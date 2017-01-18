package com.rodenski.robothingy

import com.rodenski.robothingy.datatypes.{Table, RobotPosition}
import com.rodenski.robothingy.enums.Command._
import com.rodenski.robothingy.enums.{Command, Direction}
import com.rodenski.robothingy.Robot._
import com.rodenski.robothingy.enums.Direction.Direction

/**
  * This object manages the read evaluate print loop for Robothingy
  */
object REPL {

  /**
    * This is the REPL's loop, implemented as a tail recursion
    * @param table the tables dimensions
    * @param robot the robot's position
    */
  def run(table: Table, robot: Option[RobotPosition]): Unit = {

    prompt(table, robot)
    val cmd = scala.io.StdIn.readLine().split(' ')
    val newRobot = evaluate(cmd, table, robot)
    run(table, newRobot)

  }

  /**
    * Prompts the user to enter a valid input
    *
    * @param table the existing table
    * @param robot the robot
    */
  def prompt(table: Table, robot: Option[RobotPosition]) = {

    robot match {
      case None => println(s"Please place Robothingy on the table, note that the boundaries are x=${table.x}, y=${table.y}")
      case _ => println("Robothingy awaits your command")
    }

  }

  def evaluate(cmd: Array[String], table: Table, robot: Option[RobotPosition]): Option[RobotPosition] = {

    // maybe use the either monad
    try {
      val cmdType = Command withName cmd(0).trim.toUpperCase
      cmdType match {
        case PLACE =>
          val params = parsePlace(cmd(1))
          params match {
            case Some(p) => place(p._1, p._2, p._3, table)
            case None => robot
          }
        case MOVE => move(robot, table)
        case RIGHT => right(robot)
        case LEFT => left(robot)
        case REPORT =>
          report(robot)
          robot
      }
    } catch {
      case ne: NoSuchElementException =>
        println(s"Unknow commnad ${cmd(0)}")
        robot
      case e: Exception => robot
    }
  }

  /**
    * This functions parses the parameters passed to the place command
    *
    * @param params a comma delimited string containing the params to the place command
    * @return
    */
  def parsePlace(params: String): Option[(Int, Int, Direction)] = {

    val parsed = params.split(',')
    try {
      val x = Integer.parseInt(parsed(0).trim)
      val y = Integer.parseInt(parsed(1).trim)
      val dir = Direction withName parsed(2).trim.toUpperCase
      Some((x, y, dir))
    } catch {
      case ne: NoSuchElementException =>
        println(s"invalid direction ${parsed(2)}")
        None
      case nf: NumberFormatException =>
        println("invalid index entered")
        None
    }

  }

  def report(robot: Option[RobotPosition]) = {
    robot match {
      case Some(r) => println(s"${r.x}, ${r.y}, ${r.dir.toString}")
      case None => println("Robothingy in not placed on table, please place Robothingy")
    }

  }
}
