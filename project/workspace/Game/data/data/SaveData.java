package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import engine.User;

public class SaveData {
	/**
	 * Reads a CSVfile and returns an array of users
	 * @param path File path of CSV file
	 * @return User from the file
	 */
	public static User[] read(String path) {

		// Create our array of friends
		User[] user = new User[0];

		// Open the file

		try {

			BufferedReader file = new BufferedReader(new FileReader(path));

			// Read the file
			String line;
			while((line = file.readLine()) != null) {
				// Parse the line that we just read
				String[] parts = line.split(",");

				// Create a User object

				User a = new User(parts[0], Integer.parseInt(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), Integer.parseInt(parts[4]),
						false, (int) Double.parseDouble(parts[5]), parts[6],parts[7],parts[8],parts[9],parts[10],parts[11],parts[12]);

				// Add the object to the array
				// (1) create a User array, with one extra element
				User[] user2 = new User[user.length + 1];
				// (2) copy all old elements into new
				for(int i = 0;i < user.length; i++)  {
					user2[i] = user[i];
				}
				// (3) assign new User object to last element of the array
				user2[user2.length - 1] = a;
				// (4) assign new array's address to user
				user = user2;

			}

			// Close the file
			file.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Input/output exception");
		}

		return user;
	}

	/**
	 * Use this to save the game
	 * Rewrites entire array of users
	 * @param path CSV file that holds all users
	 * @param a the user array that is going to be rewritten
	 */
	public static void writeToSave(String path,User[] a) {
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(path));

			for(int i = 0; i < a.length; i++) {
				file.write(toCSV(a[i]));
				if(i < a.length - 1) {
					file.newLine();
				}

			}

			// Close the file
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Writes a new User,
	 * It appends the file by write in a new line of code to the specifications of the User constructor
	 * @param path path of the User CSV file
	 * @param a the new User array that is to be written in
	 */
	public static void writeNewAccount(String path,User[] a) {
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(path,true));
			file.append("\n");

			for(int i = 0; i < a.length; i++) {
				file.append(toCSV(a[i]));


			}

			// Close the file
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static String toCSV(User a) {
		String summary = a.getName() + ",";
		summary += a.getPassword() + ",";
		summary += a.getHealth() + ",";
		summary += a.getMana() + ",";
		summary += a.getScore() + ",";
		summary += a.getRightHand() + ",";
		summary += a.getLeftHand() + ",";
		summary += a.getFeet() + ",";
		summary += a.getLegs() + ",";
		summary += a.getTorso() + ",";
		summary += a.getHead() + ",";
		summary += a.getBack();
		return summary;


	}
}
