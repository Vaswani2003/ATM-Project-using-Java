# ATM Project - Java Programming

This repository contains a Java-based ATM simulation project developed by Vinamra Vaswani (HU21CSEN0101331). The project consists of two main classes: `ATMInterface` and `Account`, which together simulate an ATM environment that provides various banking services based on user requests.

## Project Overview

- **ATM Interface**: Simulates the ATM's environment and provides services like withdrawals, balance checks, transaction history, deposits, and PIN changes.
- **Account**: Manages account details and works in sync with the ATM's authorization mechanism.

## Features

- **Account Management**: Create accounts with unique account numbers and PINs.
- **Authentication**: Secure PIN-based authentication for account access.
- **Withdrawal**: Withdraw funds from an account, ensuring sufficient balance.
- **Balance Check**: Display the current account balance.
- **Transaction History**: Record and display transaction history.
- **Deposit**: Deposit funds into an account.
- **PIN Change**: Change the account PIN securely.

## Default Credentials

For ease of testing, a default account is provided:

- **Account Number**: 1331
- **PIN**: 2077

## Classes and Methods

### ATMInterface Class

- **ATMInterface(ArrayList<Account> AccountsList)**: Constructor to initialize the ATM with a list of accounts.
- **void printMenu()**: Displays the main menu and initiates the ATM operations.
- **private void proceed()**: Handles user choices and directs to corresponding services.
- **private void withdraw()**: Manages withdrawal operations.
- **private void deposit()**: Manages deposit operations.
- **private void changePin()**: Allows the user to change their PIN.
- **private void checkBalance()**: Displays the current account balance.
- **private void printHistory()**: Prints the transaction history.
- **private boolean getChoice()**: Asks the user whether to return to the menu or exit.
- **private Account getAccount(int num)**: Checks if an account exists based on the account number.
- **private boolean checkPin()**: Verifies the account PIN with up to 3 attempts.

### Account Class

- **Account()**: Default constructor with preset account details (1331, 2077).
- **Account(int AccNum, int accPin)**: Parameterized constructor to create custom accounts.
- **private void getDetails()**: Collects and sets user details for the account.

### Main Class

- **public static void main(String[] args)**: Initializes accounts and starts the ATM interface.

## How to Run

1. **Clone the Repository**:
    ```sh
    git clone https://github.com/yourusername/atm-project.git
    cd atm-project
    ```

2. **Compile the Code**:
    ```sh
    javac Main.java
    ```

3. **Run the Application**:
    ```sh
    java Main
    ```

4. **Use Default Credentials for Testing**:
    - Account Number: 1331
    - PIN: 2077

## Contribution

Contributions are welcome! Feel free to fork this repository, make improvements, and submit pull requests. Whether it’s adding new features, fixing bugs, or improving documentation, your contributions are valuable.


Thank you for using the ATM Project! We hope this simulation helps you understand the basics of banking operations and secure transactions.
