import javax.naming.Name

fun main(args: Array<String>) {
    var People = ArrayList<Person>()
    var Answer: Int
    var FirstName: String
    var LastName: String
    var Number: String
    var Email: String
    var StayInLoop = true
    var ContinueInLoop = true

    do{
        println("What would you like to do?")
        println(" 1. Add an individual to the contact list.\n 2. Remove a contact.\n 3. Edit a contact.\n 4. Show the whole contact list.\n 5. Show the contact list in alphabetical order.\n 6. Save the contact list to a file.\n 7. Read in contacts from file.\n 8. Turn off the application\n" )
        when(readln().toInt()){
            1 -> {
                println("What is the person's surname?")
                FirstName = readln()
                println("What is the person's last name?")
                LastName = readln()
                println("What is the individual's phone number?")
                Number = readln()
                println("And lastly, what is the individual's email?")
                Email = readln()
                People.add(Person(FirstName, LastName, Number, Email))
            }
            2 ->{
                println("Please type in the individual's surname")
                var TempFirstName: String = readln()
                println("And please type in the individual's lastname")
                var TempLastName: String = readln()
                var TempIndex: Int = 0
                for(Person in People){
                    if(Person.FirstName.uppercase() == TempFirstName.uppercase() && Person.LastName.uppercase() == TempLastName.uppercase()){
                        People.removeAt(TempIndex)
                        println("The individual has successfully been removed from the contact list")
                        break
                    }
                    TempIndex += 1
                }
                println("The person was unfortunately not found, perhaps you misspelled their surname and/or lastname")
            }
            3 ->{
                var TempIndex: Int = 1
                for(Person in People){
                    println("" + TempIndex + "." + " Firstname: "+ Person.FirstName + ", Lastname: " + Person.LastName + ", Number: " + Person.Number + ", Email: " + Person.Email)
                    TempIndex += 1
                }
                TempIndex = 0
                println("What is the first and last name of the contact that you would like to edit?")
                var Answer = readln()
                for(Person in People){
                    if(Person.FirstName.uppercase() == Answer.substring(0).uppercase() && Person.LastName.uppercase() == Answer.substring(1).uppercase()){
                        do{
                            println("What would you like to change?")
                            println(" 1. Change first name.\n 2. Change last name.\n 3. Change phone number\n 4. Change email address.")
                            when(readln().toInt())
                            {
                                1 ->{
                                    }
                                }
                            }
                        }while(ContinueInLoop == true)
                    }
                    TempIndex += 1
                }
            }
            4 ->{
                var TempIndex: Int = 1
                for(Person in People){
                    println("" + TempIndex + "." + " Firstname: "+ Person.FirstName + ", Lastname: " + Person.LastName + ", Number: " + Person.Number + ", Email: " + Person.Email)
                    TempIndex += 1
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

class Person(var FirstName: String, var LastName: String, var Number: String, var Email: String)