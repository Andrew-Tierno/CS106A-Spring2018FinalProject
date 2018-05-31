import acm.program.*;
import java.util.*;
import java.io.*;

/**
 * This server reads in data about all members of congress,
 * and can respond to "getCongressEmailsForState" and
 * "getCongressPhonesForState" requests.  Both request types
 * should include a "stateCode" parameter. Both send back a string
 * containing a list of all that state's congress members, and their
 * requested information (phone or email).  Note that not all members
 * are guaranteed to have an email address. 
 */
public class ContActivistServer extends ConsoleProgram 
	implements SimpleServerListener {
	
	
	/* CONSTANTS */
	private static final int PORT = 8000; // The internet port that requests are made over
	private static final String DATA_FILENAME = "congress.txt"; // the data filename
	
	
	/* INSTANCE VARIABLES */
	private SimpleServer server;
	private HashMap<String, ArrayList<CongressMember>> congressMap;
	private ArrayList<CongressMember> memberList = new ArrayList<CongressMember>();

	public void run() {
		readCongressFile(DATA_FILENAME);
		
		println("Starting server on port " + PORT);
		server = new SimpleServer(this, PORT);
		server.start();
	}
	
	private void readCongressFile(String filename) {

		try {
			Scanner scanner = new Scanner(new File(filename));
			//hashmap of key: state, value: ArrayList of congress members
			congressMap = new HashMap<String, ArrayList<CongressMember>>();
			
			while (scanner.hasNextLine()) {
				// TODO: How should we store the congress members' information?
				// If only we had a variable type... 
				String name = scanner.nextLine();
				String stateCode = scanner.nextLine();
				String phone = scanner.nextLine();
				String email = scanner.nextLine();
				scanner.nextLine(); //takes care of blank
				CongressMember newMember = new CongressMember(name, stateCode, phone, email);
				if (!congressMap.containsKey(stateCode)) {
					congressMap.put(stateCode, new ArrayList<CongressMember>());
				}
				congressMap.get(stateCode).add(newMember);
			}	
			scanner.close();
		} catch (IOException e) {
			println("bruh: " + e);
		}
	}

	public String requestMade(Request request) {
		String cmd = request.getCommand();
		println("Request received: " + request); //request.toString() is automatically called
		
		String response = "";
		String stateCode = request.getParam("stateCode");
		ArrayList<CongressMember> members = congressMap.get(stateCode);
		println(request.toString());
		if (cmd.equals("getCongressPhonesForState")) {
			for (CongressMember member : members) {
				response += member.getName() + ": " + member.getPhone();
			}
		} else if (cmd.equals("getCongressEmailsForState")) {
			for (CongressMember member : members) {
				response += member.getName() + ": " + member.getEmail();
			}
		} else {
			response = "Error: Unknown command " + cmd + ".";
		}
		return response;
	}
}

