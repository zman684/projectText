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

	public Fight(User player, Monster npc, String area) {
		this.player = player;
		this.npc = npc;
		this.area = area;
		turns = 0;
		playerWin = false;
	}

	public static String battle() {
		boolean flee = false;
		Scanner in = new Scanner(System.in);

		// System.out.println(player.getName() + " VS " + npc.getName());
		// System.out.println(player);
		// System.out.println(npc);
		// System.out.println();
		while (true) {
			System.out.println("Please choose your move:");
			System.out.println("Attack");// Calls the attack method
			System.out.println("Use item");//
			System.out.println("Flee");//
			String option = in.nextLine();

			if (option.toLowerCase().equals("attack")) {
				if (player.getHealth() <= 0) {
					break;
				}
				if (npc.getHealth() <= 0) {
					playerWin = true;
					break;
				}
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
	// TODO: FIX THE DMG ALGERITHYM
	public static void attack(int attacker) {
		int npcDmg = (npc.getLevel() * npc.getDmg()) - (player.getDef() / 2);
		int playerDmg = (int) ((player.getLevel() * player.getDmg()) - (npc
				.getDef() / 2));
		hitPer();
		if (attacker == 1) {
			if ((int) (Math.random() * 100) + 1 <= dodge()) {
				System.out.println("You dodged their attack!");
			} else if ((int) (Math.random() * 100) + 1 <= block()) {
				System.out.println("You blocked their attack!");
			} else if (npcDmg > 0) {
				System.out.println("You have been hit!");
				System.out.println(npcDmg);

				player.setHealth(player.getHealth() - npcDmg);

				System.out.println("You now have " + player.getHealth());
			} else {
				System.out.println("He missed!");
			}
		}
		if (attacker == 2) {
			if (playerDmg > 0) {
				System.out.println("You hit them!");
				System.out.println(playerDmg);

				npc.setHealth(npc.getHealth() - playerDmg);

				System.out.println("They now have " + npc.getHealth());
			} else {
				System.out.println("You missed!");
			}
		}

	}

	public static int dodge() {
		double dodge = (npc.getWt() / player.getWt()) * 10;
		return (int) dodge;
	}

	public static int block() {
		double block = (player.getDef() / npc.getDmg()) * 10;
		return (int) block;
	}

	public static double[] hitPer() {
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
}
