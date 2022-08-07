package collections

object HandsOnScalaSite extends App {

  def stdDev(a: Array[Double]): Double = {
    val mean = a.sum / a.length
    val squareErrors = a.map(x => x - mean).map(y => y * y)
    math.sqrt(squareErrors.sum / a.length)
  }

  println(stdDev(Array(11.2, 21.1, 32.1, 21.1, 3232.21)).toInt)

  // Builders - immutable collections

  val builder = Array.newBuilder[Int]
  builder += 1
  builder += 2
  builder.result().foreach(println)

  // Factory methods

  Array.fill[String](2)("hello") // array with "hello" repeated 5 times
  Array.tabulate[String](6)(x => s"hello ${x}").foreach(println) // array with 6 times, each computed from its index
  println((Array(1, 2, 3, 4, 5) ++ Array(6, 7, 8, 9, 10) ++ Array(21, 22)).length) // concatenate n number of arrays

  // Transforms

  val transformArray = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
  transformArray.map(mul => mul * mul).foreach(print) // find square of each element in array
  print("\n")
  transformArray.filter(x => x % 2 == 1).foreach(print) // only element which is not divisible by 2
  print("\n")
  transformArray.take(2).foreach(print) //take only 2 elements
  print("\n")
  transformArray.drop(2).foreach(print) // drop start two elements
  print("\n")
  transformArray.slice(1, 3).foreach(print) // keep elements from index 1 to 3
  print("\n")

  // Queries

  val queriesArray = Array(1, 2, 3, 4, 6, 7, 7, 8, 9, 10, 11, 12, 13, 14)
  println(queriesArray.find(x => x % 2 == 0 && x > 4))
  println(queriesArray.find(x => x % 2 == 0 && x > 15))
  println(queriesArray.exists(x => x <= 1))
  println(queriesArray.exists(_ < 0))

  // Aggregations
  val aggArray = Array(1, 2, 3, 4, 6, 7, 7, 8, 9, 10, 11, 12, 13, 14)
  println(aggArray.mkString(" "))
  println(aggArray.mkString("[", "|", "]"))
  println(aggArray.foldLeft(0)((x, y) => x + y)) //println(aggArray.sum)
  println(aggArray.foldLeft(1)((x, y) => x * y)) //println(aggArray.product)
  println(aggArray.foldLeft(1)(_ * _)) //println(aggArray.product)

  // GroupBy
  val groupArray: Array[Int] = Array(1, 2, 3, 4, 6, 7, 7, 8, 9, 10, 11, 12, 13, 14)
  val grouped = groupArray.groupBy(x => x % 2)
  println(grouped.getClass)
  println(grouped)
  grouped(0).foreach(print)
  print("\n")
  grouped(1).foreach(print)
  print("\n")
}
