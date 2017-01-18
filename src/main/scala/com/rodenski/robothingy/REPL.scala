package com.rodenski.robothingy

import com.rodenski.robothingy.dataTypes.{Board, Robot}
import com.rodenski.robothingy.enums.Command._
import com.rodenski.robothingy.enums.{Command, Direction}


/**
  * For lack of better name
  */
object REPL {

  def run(board: Board, robot: Option[Robot]): Unit = {

    val cmd = read(board, robot)
    val thingy = evaluate(cmd, board, robot)
    run(board, thingy)

  }


  def read(board: Board, robot: Option[Robot]): Array[String] = {

    robot match {
      case None => println(s"Please place Robothingy on the board, note that the boundaries are x=${board.x}, y=${board.y}")
      case _ => println("Robothingy awaits your command")
    }

    scala.io.StdIn.readLine().split(' ')
  }

  def evaluate(cmd: Array[String], board: Board, robot: Option[Robot]): Option[Robot] = {

    try {
      val cmdType = Command withName cmd(0).toUpperCase
      cmdType match {
        case PLACE =>
          val params = cmd(1).split(',')
          val x = Integer.parseInt(params(0))
          val y = Integer.parseInt(params(1))
          val dir = Direction withName params(2).toUpperCase
          Simulator.place(x, y, dir, board)
        case MOVE => Simulator.move(robot, board)
        case RIGHT => Simulator.right(robot)
        case LEFT => Simulator.left(robot)
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

  def report(robot: Option[Robot]) = {
    robot match {
      case Some(r) => println(s"${r.x}, ${r.y}, ${r.dir.toString}")
      case None => println("Robothingy in not placed on board, please place Robothingy")
    }

  }
}
