# Overview

This program recreates the functionality on using an ATM. The user is prompted to log in and given access if found on the registry. Once logged in, the user has different options to interact with its bank account. The information is retrieved by using a .txt file. Information will be updated on the file if the user makes changes.

The purpouse of this project is for me to learn about Java and program using classes, I really like how Java works around the use of classes. Working with the Scanner librabry was interesting, I am sure there could be better ways to read user input, but it worked fine for this project.

{Provide a link to your YouTube demonstration.  It should be a 4-5 minute demo of the software running and a walkthrough of the code.  Focus should be on sharing what you learned about the language syntax.}

[Software Demo Video](http://youtube.link.goes.here)

# Development Environment

The tools that I used are Java and Microsoft Studio. The following is my main driver code.
``` Java
public static void main(String[] args) throws FileNotFoundException {
        File file = new File("bankAccounts.txt");
        Boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("Welcome to Javai Capital Bank - Please follow the steps below to log in");
            System.out.println("Insert Username: ");
            Scanner us1 = new Scanner(System.in);
            String username = us1.nextLine();
            System.out.println("Insert Password: ");
            Scanner pa1 = new Scanner(System.in);
            String password = pa1.nextLine();
            String balance = "";
    
            userAccount currentUser = new userAccount(username, password, balance);
            boolean UserExists = verifyLogin(username, password, file, balance, currentUser);
            if (UserExists) {
                /* OPEN THE ACCOUNT OF USER */
                boolean accountOpen = true;
                currentUser.menu(accountOpen);
                /* CLOSE THE ACCOUNT OF USER */
                accountOpen = false;
            }

            System.out.println("Would you like to log in again? Y/N");
            Scanner ex1 = new Scanner(System.in);
            String exit = ex1.nextLine();
            
            if (exit.equals("N") || exit.equals("n")) {
                exitProgram = true;
                us1.close();
                pa1.close();
                ex1.close();
                System.out.println("Have a nice day!");
                break;
            }
        }
    }
```

The libraries that I used helped to read output from the user and to read and write to a file.
``` Java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
```

**REMINDER:** To properly use this program, make sure you have a text file called bankAccounts.txt on the same directory of the project.

# Useful Websites

{Make a list of websites that you found helpful in this project}
* [Simple Verifying user login Java](https://www.youtube.com/watch?v=XrktMbcoeis)
* [How To Replace Specific String In Text File In Java?](https://javaconceptoftheday.com/modify-replace-specific-string-in-text-file-in-java/#:~:text=We%20are%20defining%20one%20method,back%20into%20the%20same%20file)

# Future Work

{Make a list of things that you need to fix, improve, and add in the future.}
* I may try to put this code into a UI
* Connect it to a real database on the internet
* Add the feature of sending money to other users.