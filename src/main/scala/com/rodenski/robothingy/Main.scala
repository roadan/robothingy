package com.rodenski.robothingy

import com.rodenski.robothingy.dataTypes.Board


/**
  * Created by roadan on 1/16/17.
  */
object Main extends App {

  case class Config(height: Int = 5, width: Int = 5)

  val parser = new scopt.OptionParser[Config]("Robothingy!") {

    head("Robothingy!!", "1.0-SNAPSHOT")

    opt[Int]('x', "x-axis") action { (x, c) =>
      c.copy(height = x)
    } text "The height of the robots tabletop"
    opt[Int]('y', "y-axis") action { (x, c) =>
      c.copy(width = x)
    } text "The width of the robots tabletop"

  }

  parser.parse(args, Config()) match {
    case Some(conf) =>
      val board = Board(conf.height, conf.width)
      REPL.run(board, None)
    case None => println("")

  }
}
