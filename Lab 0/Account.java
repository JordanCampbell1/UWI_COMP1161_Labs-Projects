// A class that represents an Account
// Author: put your name here
public class Account 
{
    // Attributes
    private int balance;
    private int accountno;
    // Method that returns the balance
    public int getBalance()
    {
        return balance;
    }
    // Method to deposit an amount to the account
    public void deposit(int amount) 
    {
        balance += amount;
    }
    // Method to withdraw an amount from the account public void withdraw(int amount) 
    // write some code to do this

    public void withdraw(int amount)
    {
        balance -= amount;
    }

    public int getAccountNo()
    {
        return accountno;
    }
}
