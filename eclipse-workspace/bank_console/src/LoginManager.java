import java.util.Scanner;

public class LoginManager {
	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    
	UserManager userMgr;
	String userName = "";
	public LoginManager() {
		 
		userMgr = new UserManager();
	}
	public boolean ProcessLogin() {
		System.out.println("Enter username:");
		Scanner in = new Scanner(System.in);  
		String username = in.nextLine();
		System.out.println("Enter password:");
		in = new Scanner(System.in);  
		String password = in.nextLine();
        System.out.println("You entered username: " + username);
        System.out.println("You entered password: " + password);
        Long accountNumber = 0L;
        if((accountNumber = userMgr.validateUser(username, password))>0)
        {
        	System.out.println("Successfully logged in as " + username);
        	userName = username;
        	 showMenu();
        	 int number = -1;     
              
             while(number != 0)
                 {
             	
                  try {
                	 Scanner inScan = new Scanner(System.in);  
     	             String input = inScan.nextLine();
     	             System.out.println("You entered: " + input);
     	             number = Integer.parseInt(input);
     	            switch(number)
     	            {
     		            case 1: // check for balance
       	            		  double balance = userMgr.showBalance(accountNumber);
       	            		  System.out.println(ANSI_GREEN + "Your account "+ accountNumber + ", balance: " + balance +ANSI_RESET);
       	            		   break;
     		            case 2: //deposit money
     		            		System.out.println("Enter amount to deposit");
     		            		double amount = Double.parseDouble(inScan.nextLine());
     		            		if(amount < 0)
     		            		{
     		            			System.out.println("Deposit amount should be greater than 0");
     		            			break;
     		            		}
     		            		
     		            		System.out.println("You entered amount: " + amount);
     		            		Double newBalance = userMgr.depositMoney(accountNumber, amount);
     		            		System.out.println(ANSI_GREEN +"Total balance after deposit: " + newBalance+ANSI_RESET);
     		            		break;
     		            case 3: //withdraw money
	     		            	System.out.println("Enter amount to withdraw from ATM");
	     		            	double amountToWithdraw = Double.parseDouble(inScan.nextLine());
	     		            	if(amountToWithdraw < 0)
	 		            		{
	 		            			System.out.println("Withdraw amount should be greater than 0");
	 		            			break;
	 		            		}
	     		            	System.out.println("You entered amount: " + amountToWithdraw);
	     		            	Double newBalanceAfter = userMgr.withDrawMoney(accountNumber, amountToWithdraw);
	 		            		System.out.println(ANSI_GREEN +"Total balance after deposit: " + newBalanceAfter+ANSI_RESET);
     		            	
		            		break;
     		            case 4: //Transfer money
		            		
     		            	System.out.println("Enter destination account number for transfer");
     		            	Long toAccountNumber = Long.parseLong(inScan.nextLine());
     		            	System.out.println("Enter amount to transfer");
     		            	double amountToSend = Double.parseDouble(inScan.nextLine());
     		            	if(amountToSend < 0)
 		            		{
 		            			System.out.println("Amount should be greater than 0");
 		            			break;
 		            		}
     		            	
     		            	System.out.println("FROM ACCOUNT NUMBER:" + accountNumber);
     		            	System.out.println("TO ACCOUNT NUMBER:" + toAccountNumber);
     		            	System.out.println("Transfer amount:" + amountToSend);
     		            	
     		            	Double newBalanceAfterTransfer = userMgr.transferMoney(accountNumber,toAccountNumber, amountToSend);
     		            	System.out.println(ANSI_GREEN +"Amount transferred: " + amountToSend+ANSI_RESET);
 		            		System.out.println(ANSI_GREEN +"Total balance after transfer: " + newBalanceAfterTransfer+ANSI_RESET);
 		            		
		            		break;
     		           case 5: //show all users
     		        	  if(userName.equals("admin"))
     		        	  { userMgr.ShowAllUsers();
     		        	  	break;	
     		        	  }
     		            default: 
     		            	System.out.println(ANSI_RED +"Invalid input"+ANSI_RESET);
     		            	 
     		            	break;
     	            }
             	  } catch (NumberFormatException e) {
                       System.out.println(ANSI_RED +"Invalid input and should be an integer"+ANSI_RESET);
                       number = 0;
                   }
                   showMenu();
                 }
             //inScan.close();
             System.out.println("Exiting the user screen...");    
        	 return true;
        }
        else
        {
        	System.out.println(ANSI_RED +"Invalid username or password!"+ANSI_RESET);
        	return false;
        }
      
	}
	private void showMenu() {
		System.out.println("Press 1 for check your Balance");
        System.out.println("Press 2 deposit money");
        System.out.println("Press 3 withdraw money");
        System.out.println("Press 4 Transfer money");
        if(userName.equals("admin"))
        {
        	 System.out.println("Press 5 to view all users by admin user");
        }
        System.out.println("Press 0 Log off");
  }
}