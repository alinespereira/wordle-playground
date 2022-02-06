package com.wordle.game

case class GameState(gameWord: GameWord,
                     totalTries: Int,
                     currentTries: Int,
                     guesses: List[GameWord.Guess],
                     status: GameStatus) {

  lazy val remainingTries: Int = totalTries - currentTries

  def guess(word: String): GameState = {
    val (guessResult, checkResult) = gameWord.guessAndCheck(word)
    val currentStatus = if (remainingTries <= 1) GameOver
    else if (checkResult) GameWon
    else GameInProgress
    val allGuesses = guessResult :: guesses

    GameState(
      gameWord,
      totalTries,
      currentTries + 1,
      allGuesses,
      currentStatus
    )
  }

  override def toString: String = {
    val guessesString = guesses.reverse.map(_.mkString(""))
      .zip(LazyList.from(1))
      .map { case (guess, idx) =>
        s"[$idx / $totalTries]: $guess"
      }
      .mkString("\n", "\n", "\n")

    s"""
       |Total tries: $totalTries
       |Remaining tries: $remainingTries
       |
       |$guessesString
       |""".stripMargin
  }
}
