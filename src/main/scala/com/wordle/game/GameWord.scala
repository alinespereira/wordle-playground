package com.wordle.game

import GameMode.GameMode

import scala.util.Random

object GameWord {
  type Guess = List[CharGuessResult]
}
import GameWord._

case class GameWord(mode: GameMode, size: Int = 5) {
  lazy val word: String = {
    mode match {
      case GameMode.RandomWord =>
        Random.alphanumeric.filter(_.isUpper).take(size).mkString("")
      case GameMode.FromList =>
        val words = Utils.getWords(size)
        words(Random.nextInt(words.length))
    }
  }

  def guess(guessWord: String): Guess =
    guessWord.zipWithIndex.map {
      case (ch, _) if !word.contains(ch) => CharNotFound(ch)
      case (ch, i) if word(i) == ch => CharInPosition(ch)
      case (ch, _) => CharInWord(ch)
    }
      .toList

  def isSecretWord(guessWord: Guess): Boolean =
    guessWord.forall(_.isInstanceOf[CharInPosition])

  def guessAndCheck(guessWord: String): (Guess, Boolean) = {
    val guessResult = guess(guessWord)
    val checkResult = isSecretWord(guessResult)
    (guessResult, checkResult)
  }
}