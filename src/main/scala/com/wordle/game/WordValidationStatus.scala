package com.wordle.game

sealed trait WordValidationStatus

case object InvalidSize extends WordValidationStatus
case object InvalidWord extends WordValidationStatus
case object ValidWord extends WordValidationStatus