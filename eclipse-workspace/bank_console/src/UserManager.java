import java.util.Scanner;

public class UserManager {

	DataProvider dataProvider;
	public UserManager() {
		 dataProvider = new DataProvider();
	}
	public void CreateNewUser() {
		 
		System.out.println("Creating user....");
		System.out.println("Enter new user id");
        Scanner in = new Scanner(System.in);
        String username = in.nextLine();
        System.out.println("You entered username as " + username);
        System.out.println("Enter password");
        String pwd = in.nextLine();
        System.out.println("You entered password as " + pwd);
        
       
        boolean saveResponse = dataProvider.saveUser(username, pwd);
        
        if(saveResponse)
        {
        	 System.out.println("New user created successfully, username:" + username);
        }
        else
        {
        	System.out.println("Failed to create New sername:" + username);
        }
	}
	public boolean validateUser(String username, String password)
	{
		 if(dataProvider.isValidUser(username, password))
		 {
			 return true;
		 }
		 return false;
	}
	public void ShowAllUsers()
	{
		 dataProvider.ShowAllUsers();
	}
	
}