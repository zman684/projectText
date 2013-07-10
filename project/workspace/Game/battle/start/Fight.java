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
	private static boolean playerDie;

	public Fight(User player, Monster npc, String area) {
		this.player = player;
		this.npc = npc;
		this.area = area;
		turns = 0;
		playerWin = false;
		playerDie = false;
	}
	//TODO: remove the monster
	public String battle() {
		boolean flee = false;
		Scanner in = new Scanner(System.in);

		 System.out.println(player.getName() + " VS " + npc.getName());
		 System.out.println();
		// System.out.println(player);
		// System.out.println(npc);
		// System.out.println();
		while (true) {
			if (player.getHealth() <= 0){
				playerDie = true;
				break;
			}
			if (npc.getHealth() <= 0) {
				playerWin = true;
				break;
			}
			System.out.println("Please choose your move:");
			System.out.println("Attack");// Calls the attack method
			System.out.println("Use item");//
			System.out.println("Flee");//
			String option = in.nextLine();

			if (option.toLowerCase().equals("attack")) {
				System.out.println();
				attack(1);
				System.out.println();
				attack(2);
				System.out.println();
			} else if (option.toLowerCase().equals("use item")) {

			} else if (option.toLowerCase().equals("flee")) {
				attack(1);
				System.out.println();
				flee = true;
				break;
			} else {
				System.out.println("Please try again.");
			}
		}
		if (!flee) {
			if (playerWin) {
				return "You Won!";
			} else {
				return "You Died...";
			}
		} else {
			return "You run away.";
		}
	}

	/**
	 * 1 == npc (this method will mainly use one) 2 == user
	 */
	public void attack(int attacker) {
//		System.out.println("npc" + npc.getDef());
//		System.out.println("npc dmg " + npc.getDmg());
//		System.out.println("player" + player.getDef());
//		System.out.println("player dmg " + player.getDmg());
 		int npcDmg = (npc.getLevel() * npc.getDmg() * 3) - (player.getDef() / npc.getDmg());
		int playerDmg = (int) ((player.getLevel() * player.getDmg() * 3) - (npc
				.getDef() / npc.getDmg()));
		int playerRng = playerDmg/2;
		int npcRng = npcDmg/2;
//		System.out.println("before");
//		System.out.println(playerDmg);
//		System.out.println(npcDmg);
		playerDmg = playerDmg + range(playerRng);
		npcDmg = npcDmg + range(npcRng);
//		System.out.println("after");
//		System.out.println(playerDmg);
//		System.out.println(npcDmg);
		if (attacker == 1) {
//			if ((int) (Math.random() * 100) + 1 <= dodge()) {
//				System.out.println("You dodged their attack!");
//			} else
			if ((int) (Math.random() * 100) + 1 <= block()) {
				System.out.println("You blocked their attack!");
			} else if (npcDmg > 0) {
				System.out.println("You have been hit!");
				System.out.println(npcDmg);

				player.setHealth(player.getHealth() - npcDmg);

				if (player.getHealth() <= 0) {

				}else{
					System.out.println("You now have " + player.getHealth());
				}
			} else {
				System.out.println("He missed!");
			}
		}
		if (attacker == 2) {
			if (playerDmg > 0) {
				System.out.println("You hit them!");
				System.out.println(playerDmg);

				npc.setHealth(npc.getHealth() - playerDmg);

				if (npc.getHealth() <= 0) {
					playerWin = true;
				}else{
					System.out.println("They now have " + npc.getHealth());
				}
			} else {
				System.out.println("You missed!");
			}
		}

	}

//	public static int dodge() {
//		double dodge = (npc.getWt() / player.getWt()) * 10;
//		return (int) dodge;
//	}

	public int block() {
		double block = (player.getDef() / npc.getDmg()) * 10;
		return (int) block;
	}

	public int range(int range){
		int rand = (int)(Math.random() * range);
		int posneg = (int) (Math.random()*2);
		if(posneg == 0){//0 adds the rand
//			System.out.println("add "+ rand);
			return rand;
		}else if(posneg == 1){//1 subtracts the rand
//			System.out.println("minus " + rand * -1);
			return rand * -1;
		}else{
			return 0;
		}
	}

	public double[] hitPer() {
		int playerDmg = (int) ((player.getLevel() * player.getDmg()) - (npc
				.getDef() / 2));
		System.out.println(playerDmg);
		int d = 1;
		double[] dmgPer = new double[playerDmg];
		int j = playerDmg-1;
		for (int i = 0; i < playerDmg; i++) {
			dmgPer[i] = 1 / playerDmg;
			System.out.println(dmgPer[i]);
		}
		for (int w = d; w < (playerDmg / 2) - d; w++) {
			for (int i = 0; i < 2; i++) {
				dmgPer[i] = (1 / playerDmg) / (playerDmg - 2);
				System.out.println(dmgPer[i]);
				dmgPer[j] = (1 / playerDmg) / (playerDmg - 2);
				j--;
			}
			dmgPer[w] = (1 / playerDmg) / (playerDmg - 2) + (1 / playerDmg);
			System.out.println(dmgPer[w]);
		}
		d++;
		return null;
	}
	public boolean isPlayerDead() {
		return playerDie;
	}
	public boolean playerWin() {
		return playerWin;
	}
	public void restart(){
		npc.setHealth(100);
		turns = 0;
		playerWin = false;
		playerDie = false;
	}
	public void respawn(){
		playerDie = false;
	}
}
