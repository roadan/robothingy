package com.rodenski.robothingy

import com.rodenski.robothingy.datatypes.Table


/**
  * Created by roadan on 1/16/17.
  */
object Main extends App {

  case class Config(x: Int = 5, y: Int = 5)

  val parser = new scopt.OptionParser[Config]("Robothingy!") {

    opt[Int]('x', "x-axis") action { (x, conf) =>
      conf.copy(x = x)
    } text "The height of the robots tabletop"
    opt[Int]('y', "y-axis") action { (x, conf) =>
      conf.copy(y = x)
    } text "The width of the robots tabletop"

  }

  parser.parse(args, Config()) match {
    case Some(conf) =>
      val table = Table(conf.x, conf.y)
      val robot = None
      REPL.run(table, robot)
    case None => println("")

  }
}
