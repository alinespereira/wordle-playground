package com.wordle.stats

import com.wordle.Utils
import com.wordle.game.{LetterGuessResult, LetterInPosition, LetterInWord, LetterNotFound, MatchPattern}

object Information {
  private def probability(pattern: MatchPattern, validWords: List[String]): Double = {
    val matchWords: Int = pattern.filterWords(validWords).length
    val totalWords: Int = validWords.length
    matchWords.toDouble / totalWords
  }

  private def shannon(probability: Double): Double = -Math.log(probability) / Math.log(2)

  private def patterns(size: Int): List[List[Char => LetterGuessResult]] = {
    val letterNotFound: Char => LetterGuessResult = LetterNotFound.apply
    val letterInWord: Char => LetterGuessResult = LetterInWord.apply
    val letterInPosition: Char => LetterGuessResult = LetterInPosition.apply

    val options = List.fill(size) {
      List(letterNotFound, letterInWord, letterInPosition)
    }

    Utils.permutations(options)
  }
  /**
   * For each word:
   *  1. find the pattern
   *  2. find all words matching the pattern
   *  3. calculate the probability
   *  4. calculate the information
   *  5. sum up the results
   * @return
   */
  def entropy(word: String, validWords: List[String]): Double = {
    patterns(word.length).map { pattern =>
      val matchPattern = MatchPattern {
        pattern
          .zip(word)
          .map { case (fn, ch) => fn(ch) }
      }
      val p = probability(matchPattern, validWords)
      p * shannon(p)
    }
      .sum
  }
}
