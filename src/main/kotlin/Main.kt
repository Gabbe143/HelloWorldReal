package main.kotlin

import java.io.File
import java.io.InputStream
import java.io.PrintWriter

fun writeToFile(people: ArrayList<PersonWithFunctions>) {
    val writer = PrintWriter("src/main/kotlin/Contacts.txt")  // Sets the writer's path
    for (Person in people) {
        writer.write("Person: FirstName: ${Person.firstName} LastName: ${Person.lastName} Number: ${Person.number.joinToString(separator = " ")} Email: ${Person.email.joinToString(separator = " ")}\n")//Writes out a contact on each line for each person in the people list
    }
    writer.close() // closes/stops the writer
}
fun readFromFile(people: ArrayList<PersonWithFunctions>){
    val inputStream: InputStream = File("src/main/kotlin/Contacts.txt").inputStream() // Chooses the file to read from.
    inputStream.bufferedReader().forEachLine{ //Reads through the txt file's content
        if(it.startsWith("Person: ")) //Checks where a person contact is in the txt file
        {
            var index = 0
            var tempfirstName = ""
            var templastName = ""
            val tempNumber = ArrayList<String>()
            val tempEmail = ArrayList<String>()
            val array = it.split(' ')
            while (index < array.size){
                if(array[index] == "FirstName:"){ // If the string on index place contains "firstName"
                    tempfirstName = array[index+1]
                }
                if(array[index] == "LastName:"){ // If the string on index place contains "lastName"
                    templastName = array[index+1]
                }
                if(array[index] == "Number:"){ // If the string on the index's place contains "number"
                    while (array[index+1] != "Email:"){ // While the next string does not contain "email"
                        if(array[index+1] != ""){ // While the next string doesn't contain a space (multiple numbers create a space between each other)
                            tempNumber.add(newPhoneNumberWithoutHyphen(array[index+1])) // Add the number to a tempList of numbers without hyphen.
                        }
                        index++
                    }
                }
                if(array[index] == "Email:") { // If the string on the index's place contains "email"
                    while (index+1 != array.size){ // while the next string's index isn't the same as the array size
                        if(array[index+1] != ""){ // if the next string doesn't contain nothing
                            tempEmail.add(array[index + 1]) // Adds the email to the temporary list
                        }
                        index++
                    }
                }
                index++
            }
            people.add(PersonWithFunctions(tempfirstName, templastName, tempNumber, tempEmail)) // Add the contact
        }
    }
}
fun phonenumberValidator (phonenumber: String): Boolean{
    var newPhoneNumber = phonenumber // Declares a new phone number that is phone number
    if(phonenumber.contains('-')) // If it contains hyphen, we remove that
    {
        newPhoneNumber = phonenumber.replace("-","")
    }
    if((newPhoneNumber.length in 9..13 && newPhoneNumber.substring(0,1) == "+" && newPhoneNumber.substring(1, newPhoneNumber.length-1).toIntOrNull() != null) || newPhoneNumber.isEmpty()){ // If the phone number is larger or equal to 9 digits and is less or equal to 13 digits, the number contains a '+' at the start, and converting the number from string to int does NOT equal null, OR the number is empty.
        return true // Return true, it's a valid phone number
    }
    return false // Otherwise return false, it's not valid
}
fun newPhoneNumberWithoutHyphen(oldPhoneNumber: String): String{
    var newPhoneNumber = oldPhoneNumber
    if(oldPhoneNumber.contains('-'))
    {
        newPhoneNumber = oldPhoneNumber.replace("-","") // replaces "-" with nothing
    }
    return newPhoneNumber // Returns the new number
}
fun addContactToList(people: ArrayList<PersonWithFunctions>){
    val numbers = ArrayList<String>()
    val emails = ArrayList<String>()
    println("What is the person's surname?")
    val firstName: String = readln() //User input
    println("What is the person's last name?")
    val lastName: String = readln() //User input
    println("What is the individual's phone number? If they don't have a phone number, then leave it blank.")
    do {
        var continueInLoop = true
        val phonenumber: String = readln() //User input
        if(phonenumberValidator(phonenumber)){ //Checks if the phone number is valid
            numbers.add(phonenumber) //Adds the number to a list
            continueInLoop = false
        }
        else{ // If phone number is not valid
            println("You have typed an invalid phone number. The number must be between 8 and 15 numbers, with a (+ international number). Please try again")
        }
    }while(continueInLoop)

    do{
        var continueInLoop = true
        println("And lastly, what is the individual's email?")
        var newEmail = readln()
        if(newEmail.contains('@')){ // Checks if email contain a '@' symbol.
            emails.add(newEmail) //User input
            continueInLoop = false
        }
        else{
            println("You have typed an invalid email, try again.")
        }
    }while(continueInLoop)
    people.add(PersonWithFunctions(firstName, lastName, numbers, emails)) //Adds a person into the contact list people.
}

fun main(args: Array<String>) {
    val people = ArrayList<PersonWithFunctions>()
    var stayInLoop = true

    do{
        println("What would you like to do?")
        println(" 1. Add an individual to the contact list.\n 2. Remove a contact.\n 3. Edit a contact.\n 4. Show the whole contact list.\n 5. Show the contact list in alphabetical order.\n 6. Save the contact list to a file.\n 7. Read in contacts from file.\n 8. Turn off the application\n" )
        when(readln().toInt()){ //A switch-case where the Int input from the user will determine which case they will go into in the code.
            1 -> {
                addContactToList(people) // adds the contact to the list
            }
            2 ->{
                for(Person in people){
                    Person.writesOutTheWholeContactList(Person) //writes out the whole contact list
                }
                println("Please type in the individual's surname")
                val tempFirstName: String = readln() // User input
                println("And please type in the individual's lastName")
                val tempLastName: String = readln() //User input
                for(Person in people){
                    val returnBoolean = Person.removeAContact(Person, people, tempFirstName, tempLastName) // Removes the contact if found
                    if(returnBoolean){
                        break // Jumps out of the for loop to save time if found individual
                    }
                }
            }
            3 ->{
                for(Person in people) {
                    Person.writesOutTheWholeContactList(Person) // Writes out the whole contact list
                }
                println("What is the first and last name of the contact that you would like to edit?")
                val firstNameAndlastName = readln() // User input
                for(Person in people){
                    Person.editAContact(Person, firstNameAndlastName) // Gives the individual the option to edit a contact
                }
            }
            4 ->{
                for(Person in people) {
                    Person.writesOutTheWholeContactList(Person) // Writes out all the contacts in the list
                }
            }
            5 ->{
                val sortedList = people.sortedBy { it.firstName } // Creates a copy of the list "people" but sorted by firstnames
                for(Person in sortedList){
                    Person.writesOutTheWholeContactList(Person) // writes out all the contacts in the list
                }
            }
            6 ->{
                writeToFile(people)
                println("The contacts have successfully been saved in Contacts.txt!")
            }
            7 ->{
                readFromFile(people)
                println("Your contacts have successfully been loaded in!")
            }
            8 ->{
                stayInLoop = false
            }
            else ->{
                println("You have typed a invalid answer, try again.")
            }
        }
    }while(stayInLoop)
    println("You have successfully turned off the application, all of the contacts will be automatically saved.")
    println("Thank you for using our application! Have a lovely continue, goodbye!")
    writeToFile(people)
    println("Program arguments: ${args.joinToString()}")
}