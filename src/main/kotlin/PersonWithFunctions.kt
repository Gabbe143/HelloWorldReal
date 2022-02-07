package main.kotlin

class PersonWithFunctions(var firstName: String, var lastName: String, var number: ArrayList<String>, var email: ArrayList<String>){ //Created a class
    private var sizeOfpeopleList = 1
    fun writesOutTheWholeContactList(Person: PersonWithFunctions){
        var tempIndex = 1
        println("$tempIndex. FirstName: ${Person.firstName}, LastName: ${Person.lastName}, Number: ${Person.number.joinToString(separator = ", ")}, Email: ${Person.email.joinToString(separator = ", ")}") // joins all the numbers together with a comma and a space in between each other (same goes for the emails).
        tempIndex += 1 // Writes out every contact in the contact list when in a for loop.
    }

    fun removeAContact(Person: PersonWithFunctions, people: ArrayList<PersonWithFunctions>, tempFirstName: String, tempLastName: String): Boolean{
        if(Person.firstName.uppercase() == tempFirstName.uppercase() && Person.lastName.uppercase() == tempLastName.uppercase()){ //Checks each person if there is someone with an identical first and lastName.
            people.removeAt(sizeOfpeopleList-1) //If there is, the contact will be removed
            println("The individual has successfully been removed from the contact list")
            return true
        }
        else if(sizeOfpeopleList == people.size) { // if not the error message will be typed out (if the none of the people have those first and last names)
            println("The person was unfortunately not found, perhaps you misspelled their surname and/or lastName")
            return true
        }
        sizeOfpeopleList++
        return false
    }

    fun editAContact(Person: PersonWithFunctions, firstNameAndlastName: String){
        println("What is the first and last name of the contact that you would like to edit?")
            if(Person.firstName.uppercase() == firstNameAndlastName.substring(0, 1).uppercase() && Person.lastName.uppercase() == firstNameAndlastName.substring(2,3).uppercase()){ //On substring(1,2) is a jump/empty space
                do{
                    var continueInLoop = true
                    println("What would you like to change?")
                    println(" 1. Change first name.\n 2. Change last name.\n 3. Change phone number\n 4. Change email address.")
                    when(readln()) // Switch-case
                    {
                        "1" -> {
                            changeName(Person)
                            continueInLoop = false
                        }
                        "2" -> {
                            changeLastName(Person)
                            continueInLoop = false
                        }
                        "3" -> {
                            customizePhoneNumber(Person)
                            continueInLoop = false
                        }
                        "4" -> {
                            customizeEmail(Person)
                            continueInLoop = false
                        }
                        else ->{
                            println("You have typed an invalid number, try again.")
                        }
                    }
                }while(continueInLoop)
        }
    }

    private fun changeName(Person: PersonWithFunctions){
        println("What would you like the new first name to be?")
        Person.firstName = readln() //changes the firstname
    }
    private fun changeLastName(Person: PersonWithFunctions){
        println("What would you like the new last name to be?")
        Person.lastName = readln() //changes the last name
    }

    private fun customizePhoneNumber(Person: PersonWithFunctions) {
        println("The contact has the phone numbers: ${Person.number.joinToString(separator = ", ")}") //Writes out the numbers.
        println("Would you like to add, change or delete a phone number?")
        when (readln().lowercase()) { // Switch-cases
            "add" -> {
                addPhoneNumber(Person)
            }
            "change" -> {
                changePhoneNumber(Person)
            }
            "delete" -> {
                deletePhoneNumber(Person)
            }
            else -> {
                println("You have typed an invalid answer, try again.")
            }
        }
    }
    private fun addPhoneNumber(Person: PersonWithFunctions){
        println("What would you like the new number to be?")
        var addNumber: String = readln()
        if(phonenumberValidator(addNumber)){ // checks if the number is valid
            addNumber = newPhoneNumberWithoutHyphen(addNumber) // removes any possible hyphens
            Person.number.add(addNumber) // adds the number to the list
        }
    }
    private fun changePhoneNumber(Person: PersonWithFunctions){
        println("Please type in the international number you want to change.")
        val oldNumber: String = readln()
        for(phoneNumbers in Person.number){
            if(oldNumber == phoneNumbers){ // If the inputted number exists in the list
                println("What would you like to change the number to?")
                var newNumber: String = readln()
                if(phonenumberValidator(newNumber)){ // checks if the phone number is valid
                    newNumber = newPhoneNumberWithoutHyphen(newNumber) // removes any possible hyphens
                    val index: Int = Person.number.indexOf(newNumber) // gets' the index of the number
                    Person.number[index] = newNumber // replaces the number at that index with the new number
                }
                else{
                    println("You have typed an invalid phone number")
                }
            }
            else{
                println("The phone number does not exist.")
            }
        }
    }
    private fun deletePhoneNumber(Person: PersonWithFunctions){
        println("Please type in the international number you want to delete.")
        val deleteNumber: String = readln()
        for(phoneNumbers in Person.number){
            if(deleteNumber == phoneNumbers){ // if the number exists in the list
                Person.number.remove(deleteNumber) // the number will be removed
            }
        }
    }

    private fun customizeEmail(Person: PersonWithFunctions){
        do{
            var inLoop = true
            println("Would you like to add, change or delete an email?")
            when (readln().lowercase()) { // Switch-cases
                "add" -> {
                    addEmail(Person)
                    inLoop = false
                }
                "change" -> {
                    changeEmail(Person)
                    inLoop = false
                }
                "delete" -> {
                    removeEmail(Person)
                    inLoop = false
                }
                else ->{
                    println("You have typed an invalid answer, try again.")
                }
            }
        }while(inLoop)
    }
    private fun addEmail(Person: PersonWithFunctions){
        println("Please type in the email that you would like to add.")
        val newEmail: String = readln()
        Person.email.add(newEmail)
    }
    private fun changeEmail(Person: PersonWithFunctions){
        println("Please type in the email that you want to change.")
        val oldEmail: String = readln()
        for(emails in Person.email){
            if(oldEmail == emails) {
                println("What would you like the new email to be?")
                var emailIsFake = true
                do {
                    val newEmail: String = readln()
                    if(newEmail.contains('@')) {
                        val index: Int = Person.email.indexOf(oldEmail)
                        Person.email[index] = newEmail
                        emailIsFake = false
                    }
                    else{
                        println("You have entered an invalid email, try again.")
                    }
                }while(emailIsFake)
            }
        }
    }
    private fun removeEmail(Person: PersonWithFunctions){
        println("Please type in the email that you would like to remove.")
        val removeEmail: String = readln()
        for(emails in Person.email){
            if(removeEmail == emails){
                Person.email.remove(removeEmail)
            }
        }
    }
}
