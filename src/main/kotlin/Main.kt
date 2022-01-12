import javax.naming.Name

fun main(args: Array<String>) {
    var People = ArrayList<Person>()
    var Answer: Int
    var Surname: String
    var LastName: String
    var Number: String
    var Email: String
    var StayInLoop = true

    do{
        println("What would you like to do?")
        println(" 1. Add an individual to the contact list.\n 2. Remove a contact.\n 3. Edit a contact.\n 4. Show the whole contact list.\n 5. Show the contact list in alphabetical order.\n 6. Save the contact list to a file.\n 7. Read in contacts from file.\n 8. Turn off the application\n" )
        when(readln().toInt()){
            1 -> {
                println("What is the person's surname?")
                Surname = readln()
                println("What is the person's last name?")
                LastName = readln()
                println("What is the individual's phone number?")
                Number = readln()
                println("And lastly, what is the individual's email?")
                Email = readln()
                People.add(Person(Surname, LastName, Number, Email))
            }
            2 ->{
                println("Please type in the individual's surname")
                var TempSurname: String = readln()
                println("And please type in the individual's lastname")
                var TempLastName: String = readln()
                var TempIndex: Int = 0
                for(x in People){
                    if(People[TempIndex].Surname.uppercase()==TempSurname.uppercase() && People[TempIndex].LastName.uppercase()==TempLastName.uppercase()){
                        People.removeAt(TempIndex)
                        println("The individual has successfully been removed from the contact list")
                        break
                    }
                    TempIndex += 1
                }
                println("The person was unfortunately not found, perhaps you misspelled their surname and/or lastname")
            }
            3 ->{

            }
            4 ->{
                var TempIndex: Int = 0
                for(x in People){
                    println(People[TempIndex])
                }
            }
            5 ->{

            }
            6 ->{

            }
            7 ->{

            }
            8 ->{
                StayInLoop = false
            }
            else ->{
                println("You have typed a invalid answer, try again.")
            }
        }
    }while(StayInLoop == true)
    println("You have successfully turned off the application, all of the contacts will be automatically saved.")





    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

class Person(var Surname: String, var LastName: String, var Number: String, var Email: String)