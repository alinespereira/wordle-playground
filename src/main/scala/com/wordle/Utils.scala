package com.wordle

import scala.io.Source
import scala.util.Using

object Utils {
  def getWords(size: Int, language: String = "ptbr"): List[String] =
    Using(Source.fromURL(getClass.getResource(s"$language-$size-grams.txt"))) { source =>
      source
        .getLines()
        .map(_.toUpperCase)
        .toList
    }.get

  def permutations[T](input: Iterable[Iterable[T]]): List[List[T]] = {
    input match {
      case single :: Nil => single.map(List(_)).toList
      case head :: tail =>
        head.flatMap { h =>
          permutations(tail).map { t =>
            h :: t
          }
        }
        .toList
      case Nil => Nil
    }
  }
}
