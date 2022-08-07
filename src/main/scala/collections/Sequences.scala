package collections

import scala.util.Random

object Sequences extends App {
  // Seq
  val aSequence = Seq(1, 2, 3, 4, 5)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(4))
  println(aSequence ++ Seq(6, 7, 8, 9, 10))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  val bRange: Seq[Int] = 1 until 10

  aRange.foreach {
    println
  }

  bRange.foreach {
    println
  }

  (1 to 10).foreach { x => print("hello") }

  // List

  println("\n")
  val aList = List(1, 2, 3)
  val appended = 21 +: aList :+ 12

  println(appended)
  println(appended.mkString("-"))

  // Array

  val number = Array(1, 2, 3)
  val fiveElements = Array.ofDim[String](5)
  fiveElements.foreach(println)

  // Array and Seq
  val numberSeq: Seq[Int] = number
  println(numberSeq)

  // Vectors
  val vector: Vector[Int] = Vector(1, 2, 10)
  println(vector.getClass)
  vector.foreach(println)

  // Vectors vs List

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numberLists = (1 to maxCapacity).toList
  val numberVectors = (1 to maxCapacity).toVector
  println(getWriteTime(numberLists))
  println(getWriteTime(numberVectors))
}
