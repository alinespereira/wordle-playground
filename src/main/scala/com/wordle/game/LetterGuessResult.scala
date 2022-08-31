package com.wordle.game

sealed trait LetterGuessResult {
  val color: String
  val letter: Char
  val priority: Int

  override def toString: String = s"$color$letter${Console.RESET}"
}

object LetterGuessResult {
  implicit def ordering(implicit ord: Ordering[Char]): Ordering[LetterGuessResult] =
    Ordering.by(g => (g.letter, g.priority))
}


object LetterNotGuessed

case class LetterNotGuessed(letter: Char) extends LetterGuessResult {
  override val color: String = Console.BLACK
  override val priority: Int = 40
}


object LetterNotFound

case class LetterNotFound(letter: Char) extends LetterGuessResult {
  override val color: String = Console.RED
  override val priority: Int = 30
}


object LetterInWord

case class LetterInWord(letter: Char) extends LetterGuessResult {
  override val color: String = Console.YELLOW
  override val priority: Int = 20
}


object LetterInPosition

case class LetterInPosition(letter: Char) extends LetterGuessResult {
  override val color: String = Console.GREEN
  override val priority: Int = 10
}