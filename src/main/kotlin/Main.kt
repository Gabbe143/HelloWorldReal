fun main(args: Array<String>) {
    println("Hello World!")
    val People = ArrayList<Person>()
    val Answer = true
    while (Answer)
    People.add(Person(readln(), readln(), readln(), readln()))


    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

class Person(var Name: String, var LastName: String, var Number: String, var Mail: String)