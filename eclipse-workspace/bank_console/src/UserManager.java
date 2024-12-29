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
	public Long validateUser(String username, String password)
	{
		return dataProvider.isValidUser(username, password);
	}
	public void ShowAllUsers()
	{
		 dataProvider.ShowAllUsers();
	}
	public double showBalance(Long accountNumber)
	{
		 return dataProvider.showBalance(accountNumber);
	}
	public double depositMoney(Long accountNumber,double amount)
	{
		 return dataProvider.depositMoney(accountNumber,amount);
	}
	public Double withDrawMoney(Long accountNumber, double amountToWithdraw) {
		 
		 return dataProvider.withDrawMoney(accountNumber,amountToWithdraw);
	}
	public Double transferMoney(Long accountNumber, Long toAccountNumber, double amountToSend) {
		return dataProvider.transferMoney(accountNumber,toAccountNumber,amountToSend);
		 
	}
}