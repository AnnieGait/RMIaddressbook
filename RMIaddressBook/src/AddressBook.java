import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;

public interface AddressBook extends Remote {
	
	public void createContact(String phone, String id, String name, String mail) throws RemoteException;
	public ArrayList<String> getContact(String phone) throws RemoteException;
	public ArrayList<String> removeContact(String phone) throws RemoteException;
	public void updateContact(String phone, String id, String name, String mail) throws RemoteException;
	public Hashtable<String,ArrayList<String>> showContact() throws RemoteException;

}
