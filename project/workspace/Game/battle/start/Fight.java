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
		this.turns = 0;
		this.playerWin = false;
	}

	public static String battle() {
		Scanner in = new Scanner(System.in);

		System.out.println(player.getName() + " VS " + npc.getName());
		System.out.println(player);
		System.out.println(npc);
		System.out.println();
//		while (true) {
			if (player.getHealth() <= 0) {
//				break;
			}
			if (npc.getHealth() <= 0) {
				playerWin = true;
//				break;
			}
			turns++;
			if (turns % 2 != 0) {
				attack(1);
				System.out.println();
			}
			if (turns % 2 == 0) {
				attack(2);
				System.out.println();
			}
//		}
//		if (playerWin) {
			return "You Won!";
//		} else {
//			return "You Died!";
//		}
	}

	/**
	 * 1 == npc (this method will mainly use one) 2 == user
	 */
	//TODO: FIX THE DMG ALGERITHYM
	public static void attack(int attacker) {
		int npcDmg = (npc.getLevel() * npc.getDmg()) - player.getDef();
		int playerDmg = (int) ((player.getLevel() * player.getDmg()) - npc.getDef());
		System.out.println(playerDmg);
		System.out.println(npcDmg);
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
		double dodge = (npc.getWt() / player.getWt()) / 10;
		return (int) dodge;
	}

	public static int block() {
		double block = (player.getDef() * 2) / 100;
		return (int) block;
	}

}
