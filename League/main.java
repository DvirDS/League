
public class main {

	public static void main(String[] args) {
		
		Ligat_HaAl league = new Ligat_HaAl();
		// play games
		for (int i = 0; i < league.gamesList.length; i++) {
			System.out.println("the match between " + league.gamesList[i].getHomeTeam().getTeamName() + " and "
					+ league.gamesList[i].getAwayTeam().getTeamName() + " has begun!");
			// open the menu. 
			// when press Exit (12) the current game end and the next game will start
			league.Menu();
			System.out.println("the match between " + league.gamesList[i].getHomeTeam().getTeamName() + " and "
					+ league.gamesList[i].getAwayTeam().getTeamName() + " has finished!");
			System.out.println("The score is: " + league.gamesList[i].resultOfTheGame() + "\n");
			System.out.println("---------------------------------------------------------------\n");
			// Moving on to the next match date
			league.today = league.today.plusDays(3);
		}
		System.out.println("-----------------The football league is over------------------");
		System.out.println("---------------to see the final table press 10----------------");
		league.Menu();
		// test update1
		
	}

}
