package com.wordle.game

import GameMode.GameMode

object Game {
  val initialStatus: GameStatus = GameInProgress
  val tries: Int = 6
  val wordSize: Int = 5
}

case class Game(mode: GameMode = GameMode.FromList, tries: Int = Game.tries, size: Int = Game.wordSize) {
  val state: GameState = GameState(
    GameWord(mode, size),
    tries,
    0,
    List(),
    Game.initialStatus
  )

  def tryWord: String => GameState = state.guess
}
