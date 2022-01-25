import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.DoubleBinaryOperator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class userAccount {
    String username;
    String password;
    String balance;

    public userAccount(String username, String password, String balance) {
        setBalance(balance);
        setPassword(password);
        setUsername(username);
    }
    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    // MENU //
    public void menu(boolean accountOpen) {
        while (accountOpen) {
            System.out.println("\nGood Morning, what would you like to do?");
            System.out.println("1) Make a deposit");
            System.out.println("2) Withdraw money");
            System.out.println("3) Check balance");
            System.out.println("4) Update account");
            System.out.println("5) Estimate Expenses");
            System.out.println("6) Exit your account");
            Scanner re1 = new Scanner(System.in);
            String response = re1.nextLine();

            // If user exits account, we break loop
            if (response.equals("6")) {
                System.out.println("Your account has been closed.");
                accountOpen = false;
                //re1.close();
                break;
            }
            // Else user wants to stay
            else {
                if (response.equals("1")) {
                    makeDeposit(); // completed
                }
                else if (response.equals("2")) {
                    withdrawal(); //completed
                }
                else if (response.equals("3")) {
                    checkBalance(); //completed
                }
                else if (response.equals("4")) {
                    updateAccount(); // completed
                }
                else if (response.equals("5")) {
                    createEstimate(); // completed
                }
                // If option is out of scope, the user is notified of the error
                else {
                    System.out.print("\nError, action #" + response + " does not exist.\n");
                }
            }
        }
        
    }


    // TRANSACTIONS //
    public void makeDeposit() {
        System.out.println("-- MAKE A DEPOSIT -- How would you like to make a deposit?");
        System.out.println("1) Deposite a check");
        System.out.println("2) Deposite cash");
        Scanner re1 = new Scanner(System.in);
        String response = re1.nextLine();

        if (response.equals("1")) {
            System.out.println("-- MAKE DEPOSIT -- Deposit a check MAX: $2500.00");
            System.out.println("How much cash is printed on the check?");
            Scanner re2 = new Scanner(System.in);
            double response2 = re2.nextDouble();
            double currentBalance = Double.parseDouble(getBalance());
            double newBalance = (currentBalance + response2);
            if (response2 <= 2500.00) {
                System.out.printf("-- SUCCESS -- Your new balance is %s\n", newBalance);
                String newData =String.valueOf(newBalance);
                updateFile(newData, "3");
            }
            else {
                System.out.println("Check is TOO big! Please try cash or a bank window.");
            }
        }
        else {
            System.out.println("-- MAKE DEPOSIT -- Deposit cash");
            System.out.println("How much cash are you depositing?");
            Scanner re2 = new Scanner(System.in);
            double response2 = re2.nextDouble();
            double currentBalance = Double.parseDouble(getBalance());
            double newBalance = (currentBalance + response2);
            System.out.printf("-- SUCCESS -- Your new balance is %s\n", newBalance);
            String newData =String.valueOf(newBalance);
            updateFile(newData, "3");
        }

    }
    public void withdrawal() {
        System.out.println("-- WITHDRAW MONEY -- How much money would you like to withdraw?");
        Scanner re1 = new Scanner(System.in);
        double response = re1.nextDouble();
        double currentBalance = Double.parseDouble(getBalance());
        double newBalance = (currentBalance - response);
        if (newBalance >= 0) {
            System.out.printf("-- SUCCESS -- Your new balance is %s\n", newBalance);
            String newData =String.valueOf(newBalance);
            updateFile(newData, "3");
        }
        else {
            System.out.printf("-- ERROR -- You do not have sufficient funds, current balance is %s\n", currentBalance);
        }
        


    }
    public void checkBalance() {
        System.out.println("-- CURRENT BALANCE --");
        System.out.printf("Your current balance is %s\n",getBalance());
    }
    public void createEstimate() {
        System.out.println("-- CREATE AN ESTIMATE -- ");
        System.out.println("Insert the price of each item you want to add your expenses list and we will let you know if you have sufficient funds. When finished, type 0.");
        System.out.print("Item 1: ");
        ArrayList<Double> ListItems = new ArrayList<>();
        Scanner re1 = new Scanner(System.in);
        double item = re1.nextDouble();
        int i = 1;
        // List of Items
        while(item >= 1) {
            i++;
            ListItems.add(item);
            System.out.print("Item " + i + ": ");
            item = re1.nextDouble();
        }
        
        // Now we sum the items and calculate if there are enough funds
        double sumItems = 0;
        double currentBalance = Double.parseDouble(getBalance());
        double newBalance;
        Boolean pass1 = true;
        int x = 0;
        while(ListItems.size() > x) {
            sumItems += ListItems.get(x);
            newBalance = currentBalance - sumItems;
            // IF the sumItems is more than the currentBalance, we alert the user
            if (newBalance < 0 ) {
                pass1 = false;
                System.out.println("-- ERROR -- You will not be able to afford all the items on your list.");
                if(ListItems.size() == 1) {
                    System.out.println("Your funds are not enough to buy the only item of your list");
                } else {
                    System.out.println("Your funds can only help buy items until Item #" + x);
                }
                break;
            }
            x++;
        }
        // IF the currentBalance - sumItems will not overdraft the bank account
        if (pass1)
            System.out.println("You will be able to afford all the items and remain with " + (currentBalance - sumItems) + " funds on your account.");
        

        
    }
    public void updateAccount() {
        System.out.println("-- UPDATE ACCOUNT --");
        System.out.println("Would you like to change (1)Username or (2)Password?");
        Scanner re1 = new Scanner(System.in);
        String type = re1.nextLine();

        // User wants to change password
        if (type.equals("1")) {
            System.out.println("What is the new Username?");
            Scanner re2 = new Scanner(System.in);
            String newData = re2.nextLine();
            updateFile(newData, type);
        }
        // User wants to change username
        else {
            System.out.println("What is the new Password?");
            Scanner re2 = new Scanner(System.in);
            String newData = re2.nextLine();
            updateFile(newData, type);
        }
    }
    public void updateFile(String newData, String type) {
        File file = new File("bankAccounts.txt");
        boolean found = false;
        String tempUsername = "";
        String tempPassword = "";
        String tempBalance = "";

        try 
        {
            Scanner readFile = new Scanner(file);
            readFile.useDelimiter("[,\n]");

            while (readFile.hasNext() && !found) {
                tempUsername = readFile.next();
                tempPassword = readFile.next();
                tempBalance = readFile.next();

                // IF tempusernames matches username || if temppassword matches password || if tempbalance matches balance
                if (tempUsername.trim().equals(getUsername().trim()) && tempPassword.trim().equals(getPassword().trim()) && tempBalance.trim().equals(getBalance().trim())) {
                    found = true;
                    // DECIDE THE TYPE OF CHANGE. USERNAME = TYPE 1, PASSWORD = TYPE 2, BALANCE = TYPE 3
                    // is USERNAME IF 1
                    if (type.equals("1")) {
                        modifyFile("bankAccounts.txt", getUsername(), newData);
                        setUsername(newData);
                    }
                    // is PASSWORD IF 2
                    else if (type.equals("2")) {
                        modifyFile("bankAccounts.txt", getPassword(), newData);
                        setPassword(newData);
                    }
                    // is BALANCE IF 3
                    else if (type.equals("3")) {
                        modifyFile("bankAccounts.txt", getBalance(), newData);
                        setBalance(newData);
                    }
                }
            }
            readFile.close();
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }
    }

    // THIS CODE WAS TAKEN FROM:
    // https://javaconceptoftheday.com/modify-replace-specific-string-in-text-file-in-java/#:~:text=We%20are%20defining%20one%20method,back%20into%20the%20same%20file.
    static void modifyFile(String filePath, String oldString, String newString)
    {
        File fileToBeModified = new File(filePath);
         
        String oldContent = "";
         
        BufferedReader reader = null;
         
        FileWriter writer = null;
         
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
             
            //Reading all the lines of input text file into oldContent
             
            String line = reader.readLine();
             
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();
                 
                line = reader.readLine();
            }
             
            //Replacing oldString with newString in the oldContent
            
            String newContent = oldContent.replaceAll(oldString, newString);

            //Rewriting the input text file with newContent
             
            writer = new FileWriter(fileToBeModified);
             
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //Closing the resources
                 
                reader.close();
                 
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }

}

class MainBank {
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
    public static boolean verifyLogin(String username, String password, File file, String balance, userAccount user) {
        boolean found = false;
        String tempUsername = "";
        String tempPassword = "";
        String tempBalance = "";

        try 
        {
            Scanner readFile = new Scanner(file);
            readFile.useDelimiter("[,\n]");

            while (readFile.hasNext() && !found) {
                tempUsername = readFile.next();
                tempPassword = readFile.next();
                tempBalance = readFile.next();

                if (tempUsername.trim().equals(username.trim()) && tempPassword.trim().equals(password.trim())) {
                    found = true;
                }
            }
            readFile.close();
            balance = tempBalance;
            user.setBalance(balance);
            // returns true or false if the user exists
            return found;
        }
        catch (Exception e)
        {
            System.out.println("Error");
        }
        // last return exit
        return false;
    }
}