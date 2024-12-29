import java.util.Scanner;

public class LoginManager {

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
        if(userMgr.validateUser(username, password))
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
       	            		    
       	            		   break;
     		            case 2: //deposit money
     		            		 
     		            		break;
     		            case 3: //withdraw money
		            		 
		            		break;
     		            case 4: //Transfer money
		            		 
		            		break;
     		           case 5: //show all users
     		        	  if(userName.equals("admin"))
     		        	  { userMgr.ShowAllUsers();
     		        	  	break;	
     		        	  }
     		            default: 
     		            	System.out.println("Invalid input");
     		            	 
     		            	break;
     	            }
             	  } catch (NumberFormatException e) {
                       System.out.println("Invalid input and should be an integer");
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
        	System.out.println("Invalid username or password!");
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