public class bankAccount {
	
	private static long generatedAccountNumber = 100000001L;
	
	private long accountNumber;
	private double balance;
	private user user;
	
	public bankAccount(double balance, user user) {
		this.accountNumber = bankAccount.generatedAccountNumber++;
		this.balance = balance;
		this.user = user;
	}
	
	/////////////////////////////////// GETTERS AND SETTERS ///////////////////////////////////
	
	public long getAccountNumber() {
		return accountNumber;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public user getUser() {
		return user;
	}
	
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void setUser(user user) {
		this.user = user;
	}
	
	/////////////////////////////////// INSTANCE METHODS ///////////////////////////////////
	
	public int deposit(double amount) {
		if (amount <= 0) {
			return 0;
		} else {
			balance = balance + (amount / 2);
			
			return 1;
		}
	}
	
	public int withdraw(double amount) {
		if (amount > balance) {
			return 0;
		} else if (amount <= 0) {
			return 1;
		} else {
			balance = balance - amount;
			
			return 2;
		}
	}
}