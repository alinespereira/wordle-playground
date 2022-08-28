package com.wordle.game

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object Game {
  val initialStatus: GameStatus = GameInProgress
  val tries: Int = 6
  val wordSize: Int = 5
}

case class Game(tries: Int = Game.tries, size: Int = Game.wordSize) {
  val state: GameState = GameState(
    GameWord(size),
    tries,
    List(),
    Game.initialStatus
  )

  @tailrec
  private def perform(gameState: GameState): GameState = {
    println(gameState)
    gameState.status match {
      case GameOver =>
        println("You lost :(")
        gameState
      case GameWon =>
        println(s"You won! The secret word is ${gameState.gameWord.word}")
        gameState
      case GameInProgress =>
        val word = readLine(s"What is your next guess? ").trim.toUpperCase
        val newGameState = gameState.guess(word)
        println(s"Status: ${newGameState.status}")
        perform(newGameState)
    }
  }

  def play(): Unit = {
    perform(state)
  }

  def tryWord: String => GameState = state.guess
}
