import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Remote;

public class AddressBookServer {

	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT; // 1099

	public static void main(String[] args) throws Exception {
		System.setProperty("java.rmi.server.hostname", HOST);

		// dhmioyrgia object
		AddressBook addrObj = new AddressBookImpl();

		// create registry
		Registry registry = LocateRegistry.createRegistry(PORT);

		// bind remote object
		String rmiObjectName = "AddressBook";
		registry.rebind(rmiObjectName, addrObj);
		System.out.println("Remote object bounded.");

		System.out.println("Press <Enter> for exit.");
		System.in.read();

		UnicastRemoteObject.unexportObject((Remote) addrObj, true);
		registry.unbind(rmiObjectName);
		System.out.println("Remote object unbounded.");

	}

}
