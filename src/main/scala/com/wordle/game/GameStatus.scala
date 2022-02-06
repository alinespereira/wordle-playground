package com.wordle.game

sealed trait GameStatus
case object GameInProgress extends GameStatus
case object GameWon extends GameStatus
case object GameOver extends GameStatus
