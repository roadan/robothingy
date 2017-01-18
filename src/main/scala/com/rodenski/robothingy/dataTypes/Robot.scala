package com.rodenski.robothingy.dataTypes

import com.rodenski.robothingy.enums.Direction._

/**
  * The Robot case class holds the location and direction of the robot
  *
  * @param x the index of the robot on the x axis
  * @param y the index of the robot on the y axis
  * @param dir the direction the robot is facing
  */
case class Robot(x: Int, y: Int, dir: Direction)