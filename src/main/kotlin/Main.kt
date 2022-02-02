import main.kotlin.Person
import java.io.File
import java.io.InputStream
import java.io.PrintWriter

fun main(args: Array<String>) {
    var People = ArrayList<Person>()
    var Answer: Int
    var FirstName: String
    var LastName: String
    var Number = ArrayList<String>()
    var Email = ArrayList<String>()
    var StayInLoop = true
    var ContinueInLoop = true
//
    do{
        println("What would you like to do?")
        println(" 1. Add an individual to the contact list.\n 2. Remove a contact.\n 3. Edit a contact.\n 4. Show the whole contact list.\n 5. Show the contact list in alphabetical order.\n 6. Save the contact list to a file.\n 7. Read in contacts from file.\n 8. Turn off the application\n" )
        when(readln().toInt()){ //A switch-case (C#) where the Int input from the user will determine which case they will go into in the code.
            1 -> {
                println("What is the person's surname?")
                FirstName = readln() //User input
                println("What is the person's last name?")
                LastName = readln() //User input
                println("What is the individual's phone number? If they don't have a phone number, then leave it blank.")
                var TempListOfNumbers = ArrayList<String>()
                do {
                    ContinueInLoop = true
                    var TempNumber: String = readln() //User input
                    if(PhoneNumberValidator(TempNumber)){
                        val NewNumber = NewPhoneNumberWithoutHyphen(TempNumber)
                        TempListOfNumbers.add(NewNumber)
                        ContinueInLoop = false
                    }
                    else{
                        println("You have typed an invalid phone number. The number must be between 8 and 15 numbers, with a (+ international number). Please try again")
                    }
                }while(ContinueInLoop)

                println("And lastly, what is the individual's email?")
                Email.add(readln()) //User input
                if(Email.contains("[0-9]+")) //? Check if there are numbers here
                {
                    println()
                }
                People.add(Person(FirstName, LastName, TempListOfNumbers, Email)) //Adds a person into the contact list People.
            }
            2 ->{
                var TempIndex: Int = 1
                for(Person in People){
                    print("" + TempIndex + "." + " Firstname: "+ Person.FirstName + ", Lastname: " + Person.LastName + ", Number: ")
                    Person.Number.forEach{print(it)}
                    println(", Email: " + Person.Email)
                    TempIndex += 1
                } // Writes out every contact in the contact list.
                println("Please type in the individual's surname")
                var TempFirstName: String = readln()
                println("And please type in the individual's lastname")
                var TempLastName: String = readln()
                var SizeOfPeopleList = 1
                for(Person in People){
                    if(Person.FirstName.uppercase() == TempFirstName.uppercase() && Person.LastName.uppercase() == TempLastName.uppercase()){ //Checks each person if there is someone with an identical first and lastname.
                        People.removeAt(SizeOfPeopleList-1) //If there is, the
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
                    println("" + TempIndex + "." + " Firstname: " + Person.FirstName + ", Lastname: " + Person.LastName + ", Number: " + Person.Number + " Email: " + Person.Email)
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
                                    for(x in Number){

                                    }
                                    println("What would you like the new phone number to be?")
                                    var i = 1
                                    for(x in Number) {
                                        println(""+ i +x)
                                    }
                                    ContinueInLoop = false
                                }
                                4 -> {
                                    println("These are the emails in this contact: ")
                                    //for()
                                    println("What would you like the new email address to be?")
                                    //Person.Email = readln()
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
                    println("" + TempIndex + "." + " Firstname: "+ Person.FirstName + ", Lastname: " + Person.LastName + ", Number: " + Person.Number.forEach{println(it)} + ", Email: " + Person.Email)
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
                WriteToFile(People)
                println("The contacts have successfully been saved in Contacts.txt!")
            }
            7 ->{
                ReadFromFile(People)
                println("Your contacts have successfully been loaded in!")
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
//class Person(var FirstName: String, var LastName: String, var Number: ArrayList<String>, var Email: ArrayList<String>)

fun WriteToFile(People: ArrayList<Person>) {
    val writer = PrintWriter("src/main/kotlin/Contacts.txt")  // Sets the writer's path
    for (Person in People) {
        writer.write("Person: FirstName: ${Person.FirstName} LastName: ${Person.LastName} Number: ${Person.Number.joinToString()}  Email: ${Person.Email}\n") //!!!!!!!!!   //Writes out a contact on each line for each person in the people list
    }
    writer.close() // closes/stops the writer
}
fun ReadFromFile(People: ArrayList<Person>){
    val inputStream: InputStream = File("src/main/kotlin/Contacts.txt").inputStream()
    inputStream.bufferedReader().forEachLine{ //Reads through the txt file's content
        if(it.startsWith("Person: ")) //Checks where a person contact is in the txt file
        {
            var Array = it.split(' ')
            People.add(Person(Array[2], Array[4], Array[6], Array[8])) //People.add(FirstName, LastName, Number, Email) adds
        }
    }
}
fun PhoneNumberValidator (PhoneNumber: String): Boolean{
    var NewPhoneNumber = PhoneNumber
        if(PhoneNumber.contains('-'))
        {
            NewPhoneNumber = PhoneNumber.replace("-","")
        }
    if((NewPhoneNumber.length in 9..13 && NewPhoneNumber.substring(0,1) == "+" && NewPhoneNumber.substring(1, NewPhoneNumber.length-1).toIntOrNull() != null) || NewPhoneNumber.isEmpty()){
        return true
    }
    return false
}
fun NewPhoneNumberWithoutHyphen(OldPhoneNumber: String): String{
    var NewPhoneNumber = OldPhoneNumber
        if(OldPhoneNumber.contains('-'))
        {
            NewPhoneNumber = OldPhoneNumber.replace("-","")
        }
    return NewPhoneNumber
}