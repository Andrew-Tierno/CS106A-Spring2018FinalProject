import acm.program.*;
import java.io.*;

/**
 * This program prompts the user for a state code, asks
 * whether they would like emails or phone numbers, and
 * communicates with the ContActivistServer to print out
 * a list of all congress members for that state and the
 * requested information about these congress members.
 */
public class ContActivistClient extends ConsoleProgram {

	/** The address of the server that should be contacted when sending
	 * any Requests. */
	private static final String HOST = "http://localhost:8000/";

	public void run() {
		println("Welcome to ContActivist!");
		while (true) {
			String stateCode = readLine("State code [EMPTY TO EXIT]: ");
			if (stateCode.length() == 0) {
				break;
			}

			boolean isEmail = readBoolean("Email or phone? ", "email",
					"phone");
			sendRequest(stateCode, isEmail);
		}
	}

	/* Sends a request to get the given congress member information
	 * for the given state and with email or phone information.
	 * Prints out the results to the console, or an error message if
	 * an error occurs.
	 */
	private void sendRequest(String stateCode, boolean isEmail) {
		try {
			String command = "";
			if(isEmail) {
				command = "getCongressEmailsForState";
			} else {
				command = "getCongressPhonesForState";
			}
			Request request = new Request(command);
			request.addParam("stateCode", stateCode);
			String contactInfo = SimpleClient.makeRequest(HOST, request);
			println(contactInfo);
		} catch (IOException e) {
			System.out.println("sadBoiii");
		}
	}
}

