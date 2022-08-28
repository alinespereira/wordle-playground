package com.wordle.game

import scala.util.Random

case class GameWord(size: Int = 5) {
  lazy val validWords: List[String] = Utils.getWords(size)
  lazy val word: String = validWords(Random.nextInt(validWords.length))

  def validateWord(word: String): WordValidationStatus = {
    if (word.length != size) InvalidSize
    else if (!validWords.contains(word)) InvalidWord
    else ValidWord
  }

  def guess(guessWord: String): MatchPattern = {
    MatchPattern.matchWords(guessWord, word)
  }

  def isSecretWord(guessWord: MatchPattern): Boolean =
    guessWord.forall(_.isInstanceOf[LetterInPosition])

  def guessAndCheck(guessWord: String): (MatchPattern, Boolean) = {
    val guessResult: MatchPattern = guess(guessWord)
    val checkResult: Boolean = isSecretWord(guessResult)
    (guessResult, checkResult)
  }
}