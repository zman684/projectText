package engine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import maps.Point;
import methods.Invo;

public class Engine {
	private static User user;
	private static Invo[] userInvo;
	private static Point location;
	private static int Heading;
	
	public static void goForward(){
		//north == 1
		//east == 2
		//south == 3
		//west == 4
		if(Heading == 1){
			Point newLocation = new Point(location.getX(),location.getY()+1);
			location = newLocation;
		}else if(Heading == 2){
			Point newLocation = new Point(location.getX()+1,location.getY());
			location = newLocation;
		}else if(Heading == 3){
			Point newLocation = new Point(location.getX(),location.getY()-1);
			location = newLocation;
		}else{
			Point newLocation = new Point(location.getX()-1,location.getY());
			location = newLocation;
		}
	}
	
	public static void goBack(){
		if(Heading == 1){
			Point newLocation = new Point(location.getX(),location.getY()-1);
			location = newLocation;
		}else if(Heading == 2){
			Point newLocation = new Point(location.getX()-1,location.getY());
			location = newLocation;
		}else if(Heading == 3){
			Point newLocation = new Point(location.getX(),location.getY()+1);
			location = newLocation;
		}else{
			Point newLocation = new Point(location.getX()+1,location.getY());
			location = newLocation;
		}
	}
	
	public static void turnRight(){
		Heading += 1;
	}
	
	public static void turnLeft(){
		Heading -= 1;
	}
	
	public static void location(){
		if(Heading == 1){
			System.out.println("You are facing North.");
		}else if(Heading == 2){
			System.out.println("You are facing East.");
		}else if(Heading == 3){
			System.out.println("You are facing South.");
		}else{
			System.out.println("You are facing West.");
		}
		System.out.println("Your Coords are, " + location.toString());
	}
	
	public static void pickUp(){
		Scanner in = new Scanner(System.in);
		System.out.print("Pickup what: ");
		String item = in.nextLine();
		Invo a = new Invo(item);
		System.out.println("Do you want to pick up: " + item + "?");
		System.out.print("If this is correct please press 1, or press 2 if it is not: "); 
		String c = in.nextLine();
		int option = Integer.parseInt(c);
		
		if(option == 1){
			 Invo[] temp = new Invo[userInvo.length + 1];
             for(int i = 0; i < userInvo.length; i++){
                 temp[i] = userInvo[i]; 
             }
         temp[temp.length - 1] = a;
         userInvo = temp;
         
         saveItem("list/invo.csv",userInvo);
		}
	}
	
	public static void saveItem(String path, Invo[] acqs) {
		BufferedWriter file;
		
		try {
			file = new BufferedWriter(new FileWriter(path));
			for(int i = 0; i < acqs.length; i++){
				file.write(				
			 acqs[i].getItem() + "\n");
			}
			file.write("\n");
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void commands(){
		String commands = "Commands:\n-help";
		commands += "\n-forward";
		commands += "\n-back";
		commands += "\n-left";
		commands += "\n-right";
		commands += "\n-look around";
		commands += "\n-location";
		commands += "\n-status";
		commands += "\n-pickup";
		commands += "\n-equipment";
		commands += "\n-exit";
		System.out.println(commands);
	}
	
	public static void help(){
		String help = "Help:\n";
		help += "This game is a text adventure created by";
		help += "\nZach Eriksen, if you need to know your";
		help += "\nsurrounding's description again please type";
		help += "\n'look around' or if you need to know what you";
		help += "\ncan type for commands please type 'commands'";
		help += "\nif you have any more questions, ideas, or just";
		help += "\nwant to say somthing please email me at: ";
		help += "\ntoorealc@yahoo.com";
		System.out.println(help);
	}
	
	public static void start(){
		Scanner in = new Scanner(System.in);
		System.out.print("What is your name: ");
		String name = in.nextLine();
		user = new User(name);
		location = new Point(0,0);
		Heading = 1;
		String summary = "\nWelcome " + name + "!";
				summary += "\nIf you need help please type 'help'";
				summary += "\nOr if you need to know the commands you can type in 'commands'";
		System.out.println(summary);
	}
	
	public static void menu(){
		while(true){
			Scanner in = new Scanner(System.in);
			System.out.print(": ");
			String action = in.nextLine();
			if(action.equals("forward")){
				System.out.println("You moved forward");
				goForward();
			}else if(action.equals("back")){
				System.out.println("You back up");
				goBack();
			}else if(action.equals("left")){
				System.out.println("You turn left");
				turnLeft();
			}else if(action.equals("right")){
				System.out.println("You turn right");
				turnRight();
			}else if(action.equals("location")){
				location();
			}else if(action.equals("equipment")){
				System.out.println(user.equipment());
			}else if(action.equals("pickup")){
				pickUp();
			}else if(action.equals("status")){
				System.out.println(user.toString());
			}else if(action.equals("help")){
				help();
			}else if(action.equals("commands")){
				commands();
			}else if(action.equals("look around")){
				System.out.println("You look are and see...");
			}else if(action.equals("exit")){
				System.out.println("goodbye");
				break;
			}else{
				System.out.println("That is not a correct command please try again.");
			}
		}
	}
}
