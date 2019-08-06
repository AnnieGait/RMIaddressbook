import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class AddressBookImpl extends UnicastRemoteObject implements AddressBook {

	private Hashtable<String, ArrayList<String>> storePhone = new Hashtable<String, ArrayList<String>>();

	public AddressBookImpl() throws RemoteException {

	}

	public Hashtable<String, ArrayList<String>> getStorePhone() {
		return storePhone;
	}

	@Override
	public void createContact(String phone, String id, String name, String mail) throws RemoteException {

		ArrayList<String> stringArray = new ArrayList<String>();
		stringArray.add(id);
		stringArray.add(name);
		stringArray.add(mail);

		storePhone.put(phone, stringArray);

	}

	@Override
	public ArrayList<String> getContact(String phone) throws RemoteException {
		if (!(storePhone.containsKey(phone)))
			return null;
		return storePhone.get(phone);
	}

	@Override
	public ArrayList<String> removeContact(String phone) throws RemoteException {

		if (storePhone.containsKey(phone)) {
			storePhone.remove(phone);
			// return deleted contact
			return getContact(phone);
		} else
			return null;

	}

	@Override
	public void updateContact(String phone, String id, String name, String mail) throws RemoteException {

		ArrayList<String> updateArray = new ArrayList<String>();
		updateArray.add(id);
		updateArray.add(name);
		updateArray.add(mail);

		storePhone.replace(phone, updateArray);

	}

	public Hashtable<String, ArrayList<String>> showContact() throws RemoteException {
		return storePhone;
	}
	
	//boolean exists contact

}
