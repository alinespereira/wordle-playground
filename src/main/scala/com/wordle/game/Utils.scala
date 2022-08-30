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
}
