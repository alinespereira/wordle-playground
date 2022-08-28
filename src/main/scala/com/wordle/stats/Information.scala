package com.wordle.stats

import com.wordle.game.MatchPattern
import com.wordle.game.Utils

object Information {
  private def probability(pattern: MatchPattern, validWords: List[String]): Double = {
    val matchWords: Int = pattern.filterWords(validWords).length
    val totalWords: Int = validWords.length
    matchWords.toDouble / totalWords
  }

  private def shannonInformation(probability: Double): Double = -Math.log(probability)
  /**
   * For each word:
   *  1. find the pattern
   *  2. find all words matching the pattern
   *  3. calculate the probability
   *  4. calculate the information
   *  5. sum up the results
   * @return
   */
  def entropy: Double = {
    ???
  }
}
