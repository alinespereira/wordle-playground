package com.wordle.game

sealed trait CharGuessResult {
  val color: String
  val c: Char

  override def toString: String = s"$color$c${Console.RESET}"
}

case class CharNotFound(c: Char) extends CharGuessResult {
  override val color: String = Console.RED
}
case class CharInWord(c: Char) extends CharGuessResult {
  override val color: String = Console.YELLOW
}
case class CharInPosition(c: Char) extends CharGuessResult {
  override val color: String = Console.GREEN
}