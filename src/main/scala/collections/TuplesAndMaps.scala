package collections

import scala.annotation.tailrec

object TuplesAndMaps {

  def main(args: Array[String]): Unit = {

    def swap[A, B](p: (A, B)) = (p._2, p._1)

    val aTuple = new Tuple4[Int, String, String, String](2, "Hi", "hello", "Bonjour")
    val bTuple = Tuple2("hi", 2)
    val cTuple = (1, 2, 3, 4, 5, "StringData")

    println(aTuple._1)
    println(bTuple.copy(_2 = "UpdatedWithJava"))
    println(swap(bTuple)) // created swap
    println(bTuple.swap) // scala' swap


    //Map - key -> value

    val aMap: Map[String, Int] = Map()
    val phoneBook: Map[String, Int] = Map(("Jim", 120), "Daniel" -> 892).withDefaultValue(-1)
    // this withDefaultValues method ensures that when try to get key that does not exist in that case -1 will be returned.
    println(phoneBook)

    //map operations
    println(phoneBook.contains("Jim"))
    println(phoneBook("Jim")) //When key does not exist it will throw an error {java.util.NoSuchElementException} : key not found: key

    //add a pairing
    val newPairing = "Marry" -> 218
    val newPhoneBook = phoneBook + newPairing
    println(newPhoneBook)

    //functions on maps
    //map, flatMap, filter
    println(phoneBook.map(pair => pair._1.toLowerCase() -> pair._2))

    //filterKeys //Deprecated
    println(newPhoneBook.filter(x => x._1.startsWith("Jim") || x._2.equals(218)))

    //mapValues //Deprecated
    println(phoneBook.map(x => x._1 -> x._2 * 21))
    println(phoneBook.map {
      case (x, y) => (x, "2021-" + y * 2)
    })

    // conversions to other collections
    println(phoneBook.toList)
    println(phoneBook.toList.toMap)

    val names = List("bobs", "james", "jim", "angela")
    println(names.groupBy(x => x.charAt(0)))

    /*
        1.  What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900

            !!! careful with mapping keys.

        2.  Overly simplified social network based on maps
            Person = String
            - add a person to the network
            - remove
            - friend (mutual)
            - unfriend

            - number of friends of a person
            - person with most friends
            - how many people have NO friends
            - if there is a social connection between two people (direct or not)
       */

    // exercise-1
    val notebook = Map("Pam" -> 200, "Ram" -> 100, "PAM" -> 100)
    println(notebook.map(pair => pair._1.toLowerCase() -> pair._2))

    // exercise-2
    def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
      network + (person -> Set())

    def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
      val friendsA = network(a)
      val friendsB = network(b)

      network + (a -> (friendsA + b)) + (b -> (friendsB + a))
    }

    def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
      val friendsA = network(a)
      val friendsB = network(b)

      network + (a -> (friendsA - b)) + (b -> (friendsB - a))
    }

    def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
      def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
        if (friends.isEmpty) networkAcc
        else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

      val unfriended = removeAux(network(person), network)
      unfriended - person
    }

    val empty: Map[String, Set[String]] = Map()
    val network = add(add(empty, "Bob"), "Mary")
    println(network)
    println(friend(network, "Bob", "Mary"))
    println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
    println(remove(friend(network, "Bob", "Mary"), "Bob"))

    // Jim,Bob,Mary
    val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
    val jimBob = friend(people, "Bob", "Jim")
    val testNet = friend(jimBob, "Bob", "Mary")

    println(testNet)

    def nFriends(network: Map[String, Set[String]], person: String): Int =
      if (!network.contains(person)) 0
      else network(person).size

    println(nFriends(testNet, "Bob"))

    def mostFriends(network: Map[String, Set[String]]): String =
      network.maxBy(pair => pair._2.size)._1

    println(mostFriends(testNet))

    def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
      network.count(_._2.isEmpty)

    println(nPeopleWithNoFriends(testNet))

    def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
      @tailrec
      def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
        if (discoveredPeople.isEmpty) false
        else {
          val person = discoveredPeople.head
          if (person == target) true
          else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
          else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
        }
      }

      bfs(b, Set(), network(a) + a)
    }

    println(socialConnection(testNet, "Mary", "Jim"))
    println(socialConnection(network, "Mary", "Bob"))

  }
}
