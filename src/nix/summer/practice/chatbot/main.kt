package nix.summer.practice.chatbot

fun main (args: Array<String>) {
    //Stage 1
    val botName = "Harry";
    val yearOfCreation = 2022;

    println("Hello! My name is $botName.");
    println("I was created in $yearOfCreation.");

    //Stage 2
    println("Please, remind me your name.");
    val userName = readln();
    println("What a great name you have, $userName.");

    //Stage 3
    println("Let me your age. \nEnter reminders of dividing your age by 3, 5 and 7");
    while (true) {
        try {
            val reminder3 = readln().toInt();
            val reminder5 = readln().toInt();
            val reminder7 = readln().toInt();
            val age = (reminder3 * 70 + reminder5 * 21 + reminder7 * 15) % 105;
            println("Your age is $age: that's a good time to start programming!");
            break;
        } catch (e: Exception) {
            println("try again, enter only numbers");
        }
    }

    //Stage 4
    println("I will prove to you that i can count to any number you want:");
    while (true) {
        try {
            val countNumber = readln().toInt();
            var i = 0;
            while (i < countNumber) {
                i++;
                println("$i !");
            }
            break;
        } catch (e: Exception) {
            println("try again, enter only numbers");
        }
    }

    //Stage 5
    println("Please, answer the question.");
    while (true) {
        println("What programming language do I use?");
        println("1. JavaScript \n2. C/C++ \n3. Kotlin \n4. Python");
        try {
            val answer = readln().toInt();
            if (answer != 3) {
                println("Please, try again.");
                continue;
            } else {
                println("Great, you right.");
                break;
            }
        } catch (e: Exception) {
            println("try again, enter only numbers");
        }
    }

    println("Goodbye, have a nice day!");
}