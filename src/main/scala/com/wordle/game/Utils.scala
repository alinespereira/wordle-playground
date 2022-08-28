package com.wordle.game

import scala.io.Source
import scala.util.Using

object Utils {
  def getWords(size: Int): List[String] =
    Using(Source.fromURL(getClass.getResource(s"$size-grams.txt"))) { source =>
      source
        .getLines()
        .map(_.toUpperCase)
        .toList
    }.get

//  def matchWords(word: String, secret: String): MatchPattern =
//    word.zipWithIndex.map {
//      case (ch, _) if !secret.contains(ch) => LetterNotFound(ch)
//      case (ch, i) if secret(i) == ch => LetterInPosition(ch, i)
//      case (ch, _) => LetterInWord(ch)
//    }
//      .zipWithIndex
//      .map {
//        case (letter@LetterInWord(ch), i) =>
//          if (countLetterToPosition(word, ch, i) <= countLetterToPosition(secret, ch, i))
//            letter
//          else
//            LetterNotFound(ch)
//        case (result, _) => result
//      }
//      .toList
}
