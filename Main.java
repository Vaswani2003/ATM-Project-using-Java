/* ATM PROJECT - PROGRAMMING IN JAVA
 VINAMRA VASWANI - HU21CSEN0101331

 I've made an ATM class that simulates an ATMs environment and provides services based on request.
 I've also made an account class that works in sync the ATM's authorization mechanism.

  The ATM reads account number, checks for authorization and provides services based on request.
  The account number and pin-number are based on the Account class object

  I've made a parametrised constructor for account class where the information for the account can be
  taken as per the user.

  But for the ease of running and testing I'll be using a default constructor where :

  Account Number = 1331
  Pin-number = 2077

  Use these credentials while you run the code for smooth sailing.

  */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;

// ======================================================================================================== //
class ATMInterface {
    Scanner sc = new Scanner(System.in);
    ArrayList<Account> UserAccounts = new ArrayList<>();
    Account CurrentAccount;
    String mainMenu =
                    """
                    ==================================================
                    ==                                              ==
                    ==          +++ WELCOME TO OUR ATM +++          ==
                    ==                                              ==
                    ==              LOADING . . .                   ==
                    ==                                              ==
                    ==================================================
                    """;


    // ======================================================================================================== //

    ATMInterface(ArrayList<Account> AccountsList) {   // constructor
        this.UserAccounts.addAll(AccountsList);
    }

    // ======================================================================================================== //

    void printMenu() throws InterruptedException {   // this function prints menu and takes initial steps
        System.out.println(mainMenu);

        Thread.sleep(3000);

        for (int i = 0; i < 10; i++)
            System.out.println();

        System.out.print("Please enter your account number : ");
        int accNo = sc.nextInt();

        this.CurrentAccount = getAccount(accNo);

        if (this.CurrentAccount != null)
            proceed();

        else {
            System.out.println("Account does not exist : ");
            System.exit(0);
        }
    }

    // ======================================================================================================== //

    private void proceed() throws InterruptedException {   // this function lets us proceed to ATM operations

        while (true){

        System.out.println("\n\n\n\t\tMenu\n");
        System.out.println(" --> 1 ) Withdraw\n");
        System.out.println(" --> 2 ) Check Balance\n");
        System.out.println(" --> 3 ) Transaction History\n");
        System.out.println(" --> 4 ) Deposit\n");
        System.out.println(" --> 5 ) Change Pin\n");
        System.out.println(" --> 0 ) Exit\n");

        int choice = sc.nextInt();

        if (choice==0){
            break;
        }

        switch (choice) {
            case 1 -> withdraw();
            case 2 -> checkBalance();
            case 3 -> printHistory();
            case 4 -> deposit();
            case 5 -> changePin();
        }
        if (!getChoice())
            break;
        }
    }

    // ======================================================================================================== //
    private void withdraw() throws InterruptedException {
        System.out.println("1 -> Savings Account");
        System.out.println("2 -> Current Account");
        System.out.print("\n\nChoose an account type : ");

        int temp = sc.nextInt();

        if ((temp!=1)&&(temp!=2)){
            System.out.println("Incorrect account type");
            System.exit(0);
        }
        System.out.println("(Please enter amount as a multiple of one hundred)");
        System.out.print("Enter amount to withdraw : ");
        float amount = sc.nextFloat();

        if (checkPin()){
            if (this.CurrentAccount.balance - amount >= 0 ){

                this.CurrentAccount.balance -= amount ;

                String[] tempInfo = { Float.toString(amount),""+LocalDate.now(),"Withdrawn"};
                this.CurrentAccount.TransactionHistory.add(tempInfo);

                System.out.println("\nProcessing, Please wait ");
                Thread.sleep(3000);

                System.out.println("\n\nTransaction Successful\nPlease collect your cash");
                Thread.sleep(3000);
            }
            else{
                System.out.println("Insufficient Balance");
            }

        }


    }

    // ======================================================================================================== //
    private void deposit() throws InterruptedException {
        System.out.println("1 -> Savings Account");
        System.out.println("2 -> Current Account");
        System.out.print("\n\nChoose an account type : ");

        int temp = sc.nextInt();

        if ((temp!=1)&&(temp!=2)){
            System.out.println("Incorrect account type");
            System.exit(0);
        }
        System.out.print("\n\nEnter amount to deposit : ");
        float amount = sc.nextFloat();

        if (checkPin()){
            this.CurrentAccount.balance += amount;
            String[] tempInfo = { Float.toString(amount),""+LocalDate.now(),"Deposited"};
            this.CurrentAccount.TransactionHistory.add(tempInfo);

            System.out.println("\nProcessing, Please wait ");
            Thread.sleep(3000);

            System.out.println("\nDeposit Successful\n");
            Thread.sleep(3000);
            }
        }

    // ======================================================================================================== //
    private void changePin() {
        if (checkPin()) {
            System.out.print("Enter new Pin number : ");
            this.CurrentAccount.pinNumber = sc.nextInt();
        }
    }

    // ======================================================================================================== //
    private void checkBalance(){
        if (checkPin()) {
            System.out.println("Account number : " + this.CurrentAccount.accNumber);
            System.out.println("\nAccount Balance : " + this.CurrentAccount.balance);
        }
    }

    // ======================================================================================================== //
    private void printHistory(){
        if (checkPin()) {
            System.out.println("Your Transaction History\n\n");

            for (String[] i : this.CurrentAccount.TransactionHistory) {
                System.out.println(Arrays.toString(i));
            }
        }
    }

    // ======================================================================================================== //
    private boolean getChoice(){
        System.out.println("\n\n 1 --> GO BACK TO MENU ");
        System.out.println(" 2 --> EXIT");

        int temp = sc.nextInt();

        return temp==1;

    }

    // ======================================================================================================== //
    private Account getAccount(int num){   // this function is used to check if provided account exists or not
        for (Account acc:this.UserAccounts)
            if (acc.accNumber == num)
                return acc;
        return null;
    }

    // ======================================================================================================== //
    private boolean checkPin(){   // this function checks for the pin, gives 3 attempts to the customer

        System.out.print("Enter 4 digit pin : ");
        int pin = sc.nextInt();

        int attempt = 3;

        while (attempt > 0){
        if (this.CurrentAccount.pinNumber == pin)
            return true;
        else {
            System.out.println("Wrong pin : Re-enter Pin : ");
            pin = sc.nextInt();
            attempt--;  }
        }
        return false;
    }

    // ======================================================================================================== //

}


// ======================================================================================================== //
// ======================================================================================================== //


class Account{
    Scanner sc = new Scanner(System.in);
    ArrayList<String[]> TransactionHistory = new ArrayList<>();
    int accNumber,age, pinNumber;
    float balance = 10000f;  // default opening balance
    String userName, mailAddress,phoneNumber;

    // ======================================================================================================== //

    Account(){   // default constructor
        this.accNumber = 1331;
        this.pinNumber = 2077;
        this.phoneNumber = "9640204212";
        this.age = 20;
        this.userName = "Vinamra";
    }

    // ======================================================================================================== //

    Account(int AccNum, int accPin){   // parametrised constructor
        this.accNumber = AccNum;
        this.pinNumber = accPin;
        this.getDetails();
    }

    // ======================================================================================================== //

    private void getDetails() {

        System.out.print("Enter Name :");
        this.userName = sc.next();
        System.out.println();

        System.out.print("Enter age : ");
        this.age = sc.nextInt();
        if (this.age < 18){
            System.out.println("Individuals below 18 are not allowed to create an account");
            System.exit(0);
        }
        System.out.println();

        System.out.print("Enter Phone-Number : ");
        this.phoneNumber = sc.next();
        System.out.println();

        System.out.print("Enter email : ");
        this.mailAddress = sc.next();
        System.out.println();

        System.out.println("Your account has been created.");
    }

    // ======================================================================================================== //

}

// ======================================================================================================== //
// ======================================================================================================== //

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Account user = new Account();  // this default constructor has account-number 1331 and pin-number 2077

        /*
        We can use a custom-made account with real details using a parametrised constructor like these
                Account x = new Account(1331,2077);
                Account y = new Account(1114,9997);
                Account z = new Account(1318,2003); */

        ArrayList<Account> AccList= new ArrayList<>();
        AccList.add(user);
        /*
           AccList.add(x);
           AccList.add(y);
           AccList.add(z);  */

        ATMInterface atm = new ATMInterface(AccList);
        atm.printMenu();
    }
}