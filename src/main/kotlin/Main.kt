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
                var TempIndex: Int = 1
                for(Person in People){
                    println("" + TempIndex + "." + " Firstname: "+ Person.FirstName + ", Lastname: " + Person.LastName + ", Number: " + Person.Number + ", Email: " + Person.Email)
                    TempIndex += 1
                }
                println("Please type in the individual's surname")
                var TempFirstName: String = readln()
                println("And please type in the individual's lastname")
                var TempLastName: String = readln()
                var SizeOfPeopleList = 1
                for(Person in People){
                    if(Person.FirstName.uppercase() == TempFirstName.uppercase() && Person.LastName.uppercase() == TempLastName.uppercase()){
                        People.removeAt(SizeOfPeopleList-1)
                        println("The individual has successfully been removed from the contact list")
                        break
                    }
                    if (SizeOfPeopleList == People.size) {
                        println("The person was unfortunately not found, perhaps you misspelled their surname and/or lastname")
                        break
                    }
                    SizeOfPeopleList += 1
                }
            }
            3 ->{
                var TempIndex: Int = 1
                for(Person in People) {
                    println("" + TempIndex + "." + " Firstname: " + Person.FirstName + ", Lastname: " + Person.LastName + ", Number: " + Person.Number + ", Email: " + Person.Email)
                    TempIndex += 1
                }
                println("What is the first and last name of the contact that you would like to edit?")
                var FirstnameAndLastname = readln()
                for(Person in People){
                    if(Person.FirstName.uppercase() == FirstnameAndLastname.substring(0, 1).uppercase() && Person.LastName.uppercase() == FirstnameAndLastname.substring(2,3).uppercase()){
                        do{
                            println("What would you like to change?")
                            println(" 1. Change first name.\n 2. Change last name.\n 3. Change phone number\n 4. Change email address.")
                            when(readln().toInt())
                            {
                                1 -> {
                                    println("What would you like the new first name to be?")
                                    Person.FirstName = readln()
                                    ContinueInLoop = false
                                }
                                2 -> {
                                    println("What would you like the new last name to be?")
                                    Person.LastName = readln()
                                    ContinueInLoop = false
                                }
                                3 -> {
                                    println("What would you like the new phone number to be?")
                                    Person.Number = readln()
                                    ContinueInLoop = false
                                }
                                4 -> {
                                    println("What would you like the new email address to be?")
                                    Person.Email = readln()
                                    ContinueInLoop = false
                                }
                                else -> {
                                    println("You have typed a invalid number, try again!")
                                }
                            }
                        }while(ContinueInLoop)
                    }
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
                var TempIndex: Int = 1
                val SortedList = People.sortedBy { it.FirstName }
                for(Person in SortedList){
                    println("" + TempIndex + "." + " Firstname: " + Person.FirstName + ", Lastname: " + Person.LastName + ", Number: " + Person.Number + ", Email: " + Person.Email)
                }

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
    }while(StayInLoop)
    println("You have successfully turned off the application, all of the contacts will be automatically saved.")





    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}
class Person(var FirstName: String, var LastName: String, var Number: String, var Email: String)