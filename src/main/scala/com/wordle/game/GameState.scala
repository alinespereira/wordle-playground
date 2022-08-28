package com.wordle.game

case class GameState(gameWord: GameWord,
                     totalTries: Int,
                     guesses: List[MatchPattern],
                     status: GameStatus) {

  private def performedTries: Int = guesses.length
  private def remainingTries: Int = totalTries - performedTries

  def guess(word: String): GameState = {
    gameWord.validateWord(word) match {
      case ValidWord =>
        val (guessResult, checkResult) = gameWord.guessAndCheck(word)
        val currentStatus =
          if (performedTries >= totalTries - 1) GameOver
          else if (checkResult) GameWon
          else GameInProgress
        val allGuesses = guessResult :: guesses

        copy(guesses = allGuesses, status = currentStatus)
      case _ => this
    }
  }

  override def toString: String = {
    val guessesString = guesses.reverse
      .zip(LazyList.from(1))
      .map { case (guess, idx) =>
        s"[$idx / $totalTries]: $guess"
      }
      .mkString("\n", "\n", "\n")

    s"""
       |Total tries: $totalTries
       |Remaining tries: $remainingTries
       |Current tries: $performedTries
       |
       |$guessesString
       |""".stripMargin
  }
}
