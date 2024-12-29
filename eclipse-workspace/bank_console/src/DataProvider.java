
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataProvider {

	public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    
	public DataProvider() {
		 // hello testing
	}
	public boolean saveUser(String username,String password)
	{
		 String text = username + ","+ password;
	     String filePath = "accounts.txt";
	     if(isUserExists(username))
	     {
	    	 System.out.println(username + " already exists...chose a different username");    
	    	 return false;
	     }
	   try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
		   LocalDateTime now = LocalDateTime.now();
		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		   String formattedDateTime = now.format(formatter);
		   long number = Long.parseLong(formattedDateTime);
            writer.write(number+","+text +",0");
            writer.newLine(); 
        	return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public void getUserDetails(String username)
	{
		
	}
	public boolean isUserExists(String username)
	{
		return usernameExists(username);
	}
	public  Long isValidUser(String username,String password) {
        try (Scanner fileScanner = new Scanner(new File("accounts.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts[1].equals(username) && parts[2].equals(password)) {
                	Long accountNumber = Long.parseLong(parts[0]);
                	return accountNumber;
                }
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + "Error reading file: " + e.getMessage() + ANSI_RESET);
        }
        return 0L;
    }
	
	public  void ShowAllUsers() {
        try (Scanner fileScanner = new Scanner(new File("accounts.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                System.out.println(ANSI_GREEN + "Account Number:"+ parts[0]+ ", Username:" + parts[1] 
                		+", Password:" +parts[2] 
                		+", Balance:" +parts[3] +ANSI_RESET);
                
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + "Error reading file: " + e.getMessage()+ ANSI_RESET);
        }
    }
	
	public Long showBalance(Long accountNumber)
	{
		try (Scanner fileScanner = new Scanner(new File("accounts.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(accountNumber.toString())) {
                	if(parts.length > 3)
                	{ 
                		Long balance = Long.parseLong(parts[3]);
                		return balance;
                	}
                	else
                		return 0L;
                }
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED +"Error reading file: " + e.getMessage()+ ANSI_RESET);
        }
        return 0L;
	}
	
	public Long depositMoney(Long accountNumber, Long amount) {
		 
		try (Scanner fileScanner = new Scanner(new File("accounts.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(accountNumber.toString())) {
                	Long balance = 0L;
                	if(parts.length > 3)
                	{ 
                		balance = Long.parseLong(parts[3]);
                	    balance += amount;
                	}
                	else
                		 balance += amount;
                	updateRow("accounts.txt",accountNumber.toString(),3,balance.toString());
                	return balance;
                }
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED +"Error reading file: " + e.getMessage()+ ANSI_RESET);
        }
        return 0L;
	}
	
	 private static boolean usernameExists(String username) {
	        try (Scanner fileScanner = new Scanner(new File("accounts.txt"))) {
	            while (fileScanner.hasNextLine()) {
	                String line = fileScanner.nextLine();
	                String[] parts = line.split(",");
	                if (parts[1].equals(username)) {
	                    return true;
	                }
	            }
	        } catch (IOException e) {
	            System.out.println(ANSI_RED +"Error reading file: " + e.getMessage()+ ANSI_RESET);
	        }
	        return false;
	    }
	 
	 
	private static void updateRow(String filePath, String rowToUpdate, int columnToUpdate, String newValue) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(rowToUpdate)) {
                    values[columnToUpdate] = newValue;
                    line = String.join(",", values);
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}