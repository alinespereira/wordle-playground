package com.wordle.game

object MatchPattern {
  def matchWords(word: String, secret: String): MatchPattern = {
    val notFoundInSecret = lookupNotFound(word, secret)
    val foundInPosition = matchInPosition(word, secret)

    val inPositionOrNotFound = notFoundInSecret.merge(foundInPosition)

    val lettersToGuess =
      inPositionOrNotFound
        .zipWithIndex
        .map {
          case (LetterNotGuessed(ch), i) =>
            val currentLetterInPosition = inPositionOrNotFound.countLetterInPosition(ch, from = i)
            val currentLetterTotal = secret.count(_ == ch)
            val currentLetterInWord = word.take(i + 1).count(_ == ch)
            if (currentLetterTotal > 0 && currentLetterInWord + currentLetterInPosition <= currentLetterTotal)
              LetterInWord(ch)
            else
              LetterNotFound(ch)
          case (result, _) => result
        }

    MatchPattern(lettersToGuess)
  }

  private def matchInPosition(word: String, secret: String): MatchPattern = {
    val pattern = word
      .zip(secret)
      .zipWithIndex
      .map {
        case ((ch, s), i) if ch == s => LetterInPosition(ch, i)
        case ((ch, _), _) => LetterNotGuessed(ch)
      }
      .toList
    apply(pattern)
  }

  private def lookupNotFound(word: String, secret: String): MatchPattern = {
    val pattern = word.map { ch =>
      if (!secret.contains(ch))  LetterNotFound(ch)
      else LetterNotGuessed(ch)
    }
      .toList
    apply(pattern)
  }
}

case class MatchPattern(pattern: Iterable[LetterGuessResult]) extends Iterable[LetterGuessResult]() {
  override def iterator: Iterator[LetterGuessResult] = pattern.iterator

  override def toString(): String =
    pattern.mkString("")

  def filterWords(words: List[String]): List[String] =
    words.filter {
      word => findAnyInWord(word) || findAnyInPosition(word)
    }

  private def findAnyInPosition(other: String): Boolean =
    pattern.flatMap {
      case LetterInPosition(c, pos) => Some(c, pos)
      case _ => None
    }
      .forall { case (c, pos) => other(pos) == c }

  private def findAnyInWord(other: String): Boolean = {
    pattern.flatMap {
      case LetterInWord(c) => Some(c)
      case _ => None
    }
      .exists(c => other.contains(c))
  }

  private def countLetterInPosition(ch: Char, from: Int = 0, until: Int = pattern.size): Int = {
    pattern.slice(from, until).count {
      case LetterInPosition(letter, _) => letter == ch
      case _ => false
    }
  }

  private def merge(other: MatchPattern): MatchPattern = {
    val newPattern =
      pattern
        .zip(other.pattern)
        .map {
          case (result: LetterInPosition, _) => result
          case (result: LetterNotFound, _) => result
          case (_, result: LetterInPosition) => result
          case (_, result: LetterNotFound) => result
          case (result: LetterInWord, _) => result
          case (_, result: LetterInWord) => result
          case (result: LetterNotGuessed, _) => result
          case (_, result: LetterNotGuessed) => result
        }

    MatchPattern(newPattern)
  }
}
