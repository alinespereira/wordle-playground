package com.wordle

import com.wordle.game._

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object Main {

  @tailrec
  def perform(state: GameState): GameState = {
    println(state)
    state.status match {
      case GameOver =>
        println("You lost :(")
        state
      case GameWon =>
        println(s"You won! The secret word is ${state.gameWord.word}")
        state
      case GameInProgress =>
        println(s"You still have ${state.remainingTries} tries")
        val word = readLine(s"What is your next guess? ").trim.toUpperCase
        if (word.length != state.gameWord.size) {
          println(s"""
            |Word should be ${state.gameWord.size} chars long.
            |Provided word is ${word.length} chars long.
            |""".stripMargin)
          perform(state)
        } else {
          val newState = state.guess(word)
          perform(newState)
        }
    }
  }

  def play(): Unit = {
    val game = Game()
    println(s"Word is ${game.state.gameWord.word}")
    perform(game.state)
  }

  def main(args: Array[String]): Unit = {
    play()
  }
}
