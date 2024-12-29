import java.util.*;
class bank
{
    public static void main(String []args)
    {
        showMenu();
        int number = 0;     
        LoginManager loginManager = new LoginManager();
         
        while(number != 7)
            {
             try {
            	 Scanner in = new Scanner(System.in);  
	             String input = in.nextLine();
	             System.out.println("You entered: " + input);
	             number = Integer.parseInt(input);
	            switch(number)
	            {
		            case 1:
							boolean retValue= loginManager.ProcessLogin();
							if(retValue)
							  {
								 System.out.println("User Logged off"); 
							  }
							break;
		            case 2: 
							UserManager userMgr = new UserManager();
							userMgr.CreateNewUser();
							break;
		            default: 
							System.out.println("Invalid input");
							showMenu();
							break;
	            }
        	  } catch (NumberFormatException e) {
                  System.out.println("Invalid input and should be an integer");
                  number = 0;
              }
              showMenu();
            }
           
           System.out.println("Exiting the application...good bye!");
    }

	private static void showMenu() {
		System.out.println("Welcome to Bank of Texas");
        System.out.println("Press 1 for login");
        System.out.println("Press 2 for create account:");
        System.out.println("Press 7 to exit");
         
	}
}