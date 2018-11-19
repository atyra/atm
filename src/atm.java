import java.util.Scanner;

public class atm
{
  private Scanner in;
  private bankAccount account;
  
  public static void main(String[] args)
  {
    atm atm = new atm(new bankAccount(0.0D, new user(1234, "Ryan Wilson", "January 1, 1970", "123 Main St., Scotch Plains, NJ 07076")));
    atm.run();
  }
  
  public atm(bankAccount account)
  {
    this.account = account;
  }

  public bankAccount getAccount()
  {
    return account;
  }

  public void setAccount(bankAccount account)
  {
    this.account = account;
  }

  public void run()
  {
    in = new Scanner(System.in);
    boolean running = true;
    boolean accountCheck = false;
    System.out.println("This is an ATM. We are in our testing phase, so there is only an example account.");
    System.out.println("The example account number is 100000001. Example PIN is 1234.");
    while (running == true) {
      if (accountCheck == false) {
        System.out.print("Enter Account Number: ");
        long accountNumber = in.nextLong();
        if (accountNumber == -1) {
          break;
        }
        System.out.print("Enter PIN Number: ");
        int pin = in.nextInt();
        if (check(accountNumber, pin) == true) {
          accountCheck = true;
          System.out.println("Welcome to the example account. Choose one of the options below.");
        }
        else {
          System.out.println("Account number and/or PIN is invalid.");
        }
      }
      else {
        showMenu();
        int selection = in.nextInt();
        if (selection == 1) {
          deposit();
        }
        else if (selection == 2) {
          withdraw();
        }
        else if (selection == 3) {
          checkBalance();
        }
        else if (selection == 4) {
          running = false;
        }        
      }
    }
    System.out.println("Thank you for using this ATM.");
    in.close();
  }

  public boolean check(long accountNumber, int pin)
  {
    return (account.getAccountNumber() == accountNumber) && (account.getUser().getPIN() == pin);
  }

  public void showMenu()
  {
    System.out.println("   [1] Deposit\n   [2] Withdraw\n   [3] View Balance\n   [4] Exit");
    System.out.print("What do you wish to do?: ");
  }

  public void deposit()
  {
    boolean valid = false;
    while (!valid) {
      System.out.print("Enter the amount you wish to add: ");
      double amount = in.nextDouble();
      if (account.deposit(amount) == 0) {
          System.out.println("Invalid. Value is either negative or zero.");
          break;
      }
      else if (account.deposit(amount) == 1) {
        System.out.println(format(amount) + " have been deposited into your account.");
        System.out.println("Your current balance is " + format(account.getBalance()) + ".");
        valid = true;
      }      
    }
  }

  public void withdraw()
  {
    if (account.getBalance() == 0) {
      System.out.println("There is no money in your account.");
    }
    else {
      boolean valid = false;
      while (valid == false) {
        /*Note: Withdraw may ask you to withdraw again after initial withdraw input. Unknown why this error occurs.*/
    	System.out.print("Enter the amount you wish to withdraw: ");
        double amount = in.nextDouble();
        int result = account.withdraw(amount);
        if (result == 0) {
            System.out.println("You are attempting to withdraw more money than you have.");
            break;
        }
        else if (result == 1) {
            System.out.println("You are attempting to withdraw nothing.");
            break;
        }
        else if (result == 2) {
            System.out.println(format(amount) + " have been withdrawn from your account.");
            System.out.println("Your current balance is " + format(account.getBalance()) + ".");
            valid = true;
        }
      }
    }
  }

  public void checkBalance()
  {
    System.out.println("Your current balance is " + format(account.getBalance()) + ".");
  }

  private static String format(double amount)
  {
    return "$" + String.format("%,.2f", amount);
  }
}

	
	/*  This was my original ATM, written in 1 file.
	  
	    public static void main(String[] args) {
		atm guest = new atm();
		double account_balance = 0;
		
		System.out.println("This is an atm. As we are in the testing phase, only the guest account is available.");
		System.out.println("Guest account number is 000000000. Guest PIN is 1234");
		boolean account = guest.account();
		while (account == false) {
			System.out.println("Invalid account.");
			account = guest.account();
		}
		
		boolean exit = false;
		
		while (exit == false) {
			int menu_choice = guest.menu();
			if (menu_choice == 1) {
				double deposit = guest.deposit();
				account_balance += deposit;
				System.out.println("Deposited $" + deposit + ". Current account balance is $" + account_balance + ".");
			}
			else if (menu_choice == 2) {
				double withdraw = guest.withdraw();
				if (withdraw > account_balance) {
					System.out.println("You are attempting to withdraw more money than you have in your account.");
				}
				else {
					account_balance -= withdraw;
					System.out.println("Withdrew $" + withdraw + ". Current account balance is $" + account_balance + ".");
				}
			}
			else if (menu_choice == 3) {
				System.out.println("Account balance is: $" + account_balance);
				
			}
			else if (menu_choice == 4) {
				exit = true;
			}
		}
		
		System.out.println("Thank you for using this atm.");
	}
	
	public boolean account() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Account Number: ");
		int account = sc.nextInt();
		System.out.print("Enter PIN Number: ");
		int pin = sc.nextInt();
		if (account == 000000000 && pin == 1234) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int menu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome, GUEST. What would you like to do?\n");
		System.out.println("    [1] Deposit");
		System.out.println("    [2] Withdraw");
		System.out.println("    [3] View Balance");
		System.out.println("    [4] Exit");
		int select = sc.nextInt();
		while (select < 1 || select > 4) {
			System.out.println("Invalid choice. Select valid task.");
			select = sc.nextInt();
		}
		return select;
	}
	
	public double deposit() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter amount to deposit: ");
		double deposit = sc.nextDouble();
		while (deposit < 0) {
			System.out.print("Cannot deposit negative amounts. Enter amount to deposit: ");
			deposit = sc.nextDouble();
		}
		return deposit;
	}
	
	public double withdraw() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter amount to withdraw: ");
		double withdraw = sc.nextDouble();
		while (withdraw < 0) {
			System.out.print("Cannot withdraw negative amounts. Enter amount to withdraw: ");
			withdraw = sc.nextDouble();
		}
		return withdraw;
	}
*/	