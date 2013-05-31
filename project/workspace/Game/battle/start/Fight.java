package start;

import java.util.Scanner;
import monsters.Monster;
import engine.User;

public class Fight {
	private static int turns;
	private static User player;
	private static Monster npc;
	private static String area;
	private static boolean playerWin;
	
	public Fight(User player, Monster npc, String area){
		Fight.player = player;
		Fight.npc = npc;
		Fight.area = area;
		Fight.turns = 0;
		Fight.playerWin = false;
	}
	
	public static boolean battle(){
		Scanner in = new Scanner(System.in);
		
		System.out.println(player.getName() + " VS " + npc.getName());
		
		while(playerWin == false){
			turns++;
			attack(1);
		}
		return playerWin = true;
	}
	
	/**
	 * 1 == npc (this method will mainly use one)
	 * 2 == user
	 */
	public static void attack(int attacker){
		if(attacker == 1){
			int damage = npc.getLevel() * 5;
			
			System.out.println("You have been hit!");
            System.out.println(damage);
			
			player.setHealth(player.getHealth()-damage);
			
			System.out.println("You now have " + player.getHealth());
		}
	}
}
