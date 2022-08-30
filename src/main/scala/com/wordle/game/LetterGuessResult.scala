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

case class LetterNotGuessed(letter: Char) extends LetterGuessResult {
  override val color: String = Console.BLACK
  override val priority: Int = 40
}

case class LetterNotFound(letter: Char) extends LetterGuessResult {
  override val color: String = Console.RED
  override val priority: Int = 30
}
case class LetterInWord(letter: Char) extends LetterGuessResult {
  override val color: String = Console.YELLOW
  override val priority: Int = 20
}
case class LetterInPosition(letter: Char, pos: Int) extends LetterGuessResult {
  override val color: String = Console.GREEN
  override val priority: Int = 10
}