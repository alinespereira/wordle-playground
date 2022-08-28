package com.wordle.game

sealed trait LetterGuessResult {
  val color: String
  val letter: Char

  override def toString: String = s"$color$letter${Console.RESET}"
}

case class LetterNotGuessed(letter: Char) extends LetterGuessResult {
  override val color: String = Console.BLACK
}

case class LetterNotFound(letter: Char) extends LetterGuessResult {
  override val color: String = Console.RED
}
case class LetterInWord(letter: Char) extends LetterGuessResult {
  override val color: String = Console.YELLOW
}
case class LetterInPosition(letter: Char, pos: Int) extends LetterGuessResult {
  override val color: String = Console.GREEN
}