public class tester {
	
	public static void main(String[] args) {		
		atm atm = new atm(
			new bankAccount(						// create new BankAccount
				0,							// initial balance is 0
				new user(						// create new User
					1234,						// PIN is 1234
					"Ryan Wilson",					// name is Ryan Wilson
					"January 1, 1970",				// date of birth is January 1, 1970
					"123 Main St., Scotch Plains, NJ 07076"		// address is 123 Main St., Scotch Plains, NJ 07076
				)
			)
		);
		
		atm.run();
		
		// TODO
		
		// you need to start the program by calling some method of the ATM class
		
		
	}
}