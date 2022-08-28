package com.wordle

import com.wordle.game._

object Main {
  def main(args: Array[String]): Unit = {
    val game = Game()
    game.play()
  }
}
