import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AddressBookClient {
	
	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT; // 1099
	
	public static void main(String[] args) {
		
		try {
			//Locate rmi registry
			Registry registry = LocateRegistry.getRegistry(HOST, PORT);
			
			String rmiObjectName = "AddressBook";
			AddressBook ref = (AddressBook) registry.lookup(rmiObjectName);
			
			char ans;
			do {
				Scanner scan = new Scanner(System.in);
				
				System.out.println("Enter phone");
				String phone = scan.nextLine();
				
				System.out.println("Enter id");
				String id = scan.nextLine();
				
				System.out.println("Enter name");
				String name = scan.nextLine();
				
				System.out.println("Enter email");
				String mail = scan.nextLine();
				
				//Do remote method invocation
				ref.createContact(phone, id, name, mail);
				
				//Get a contact
				System.out.println("Would you like to get a contact? (Y or y:yes)");
				char getChar = scan.next().charAt(0);
				if(getChar == 'Y' || getChar == 'y') {
					System.out.println("Enter the contact's phone number");
					String cPhone = scan.next();
					System.out.println(cPhone + " Contact's data:");
					System.out.println(ref.getContact(cPhone));
				}
				
				//Remove a contact
				System.out.println("Would you like to remove a contact? (Y or y:yes)");
				char remChar = scan.next().charAt(0);
				if(remChar == 'Y' || remChar =='y') {
					System.out.println("Enter the contact's phone number");
					String rPhone = scan.next();
					if(ref.getContact(rPhone)==null)
						System.out.println("Contact does not exist");
					else {
						System.out.println("Deleted contact is :" + ref.getContact(rPhone));
						ref.removeContact(rPhone);
					}
				}
				
				//Updarw a contact
				System.out.println("Would you like to update a contact? (Y or y:YES)");
				char upChar = scan.next().charAt(0);
				if(upChar== 'Y' || upChar =='y') {
					System.out.println("Enter the contact's phone number");
					String uPhone = scan.next();
					
					if(ref.getContact(uPhone) != null) {
						System.out.println("Enter your new data");
						
						System.out.println("New id:");
						String newId = scan.next();
						
						System.out.println("New name:");
						String newName = scan.next();
						
						System.out.println("New email:");
						String newMail = scan.next();
						
						ref.updateContact(uPhone, newId, newName, newMail);
					}
					else {
						System.out.println("Phone number not found in contacts...");
					}
					
				}
				
				//See your current contacts
				System.out.println("Would you like to see your current contacts? (Y or y:YES) ");
				char cChar = scan.next().charAt(0);
				if(cChar == 'Y' || cChar=='y') {
					System.out.println(ref.showContact());
				}
			
				//Create new contact
				System.out.println("Would you like to create a contact? (Y or y:YES)");
				ans = scan.next().charAt(0);
			}while(ans == 'Y' || ans == 'y');
			
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
