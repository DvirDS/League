import java.time.LocalDate;
import java.util.Scanner;

public class Game {

	Scanner s = new Scanner(System.in);

	private LocalDate gameDate;
	private Team homeTeam;
	private Team awayTeam;
	private String winnerOfTheGame;
	private int homeGoals;
	private int awayGoals;
	private Referee[] refereesList;

	// constructor
	public Game(LocalDate date, Team homeTeam, Team awayTeam, Referee[] refereesList) {
		gameDate = date;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.refereesList = refereesList;
	}

	// A function returns the result of the game
	public String resultOfTheGame() {
		return this.homeTeam.getTeamName() + ": " + getHomeGoals() + " - " + this.awayTeam.getTeamName() + ": "
				+ getAwayGoals();
	}

	public void updateErrorsOfJudgmentInGame(String id) {
		// A variable that checks if the referee is found
		int found = 0;
		// A loop that ran over the list of referees in the game
		for (int i = 0; i < refereesList.length; i++) {
			// Checks the referee by ID number
			if (refereesList[i].getId().equals(id)) {
				found = 1;
				System.out.println("The referee participates in the game."
						+ "\nHow many judgment errors would you like to update? ");
				int num = s.nextInt();
				// Update judgment errors
				refereesList[i].addMoreErrorsOfJudgment(num);
				System.out.println("\nThe errors of judgment have been updated\n");
				break;
			}
		}
		// If the referee is not found then the referee does not participate in this game
		if (found == 0)
			System.out.println("The referee does not participate in this game");
	}
	// update goal in game
	public void updateGoalsInGame(String id) {
		// A variable that checks if the player is found
		int found = 0;
		// Search for the player in the home team
		for (int i = 0; i < homeTeam.getPlayersList().length; i++) {
			// Checks the players by ID number
			if (homeTeam.getPlayersList()[i].getId().equals(id)) {
				// update the successful attempts
				homeTeam.getPlayersList()[i].addMoreSuccessfulAttempts(1);
				// update goal for home team
				this.homeGoals += 1;
				// print the goal and the name of the player
				System.out.println("\n" + homeTeam.getPlayersList()[i].getFirstName() + " "
						+ homeTeam.getPlayersList()[i].getLastName() + " scored a goal for " + homeTeam.getTeamName());
				// update the game score
				System.out.println("Current score is: " + resultOfTheGame());
				found = 1;
				break;
			}
		}
		// If the player is not found in the home team, then the search goes to the away
		// team
		if (found == 0) {
			// Search for the player in the away team
			for (int i = 0; i < awayTeam.getPlayersList().length; i++) {
				// Checks the players by ID number
				if (awayTeam.getPlayersList()[i].getId().equals(id)) {
					// update the successful attempts
					awayTeam.getPlayersList()[i].addMoreSuccessfulAttempts(1);
					// update goal for away team
					this.awayGoals += 1;
					// print the goal and the name of the player
					System.out.println("\n" + awayTeam.getPlayersList()[i].getFirstName() + " "
							+ awayTeam.getPlayersList()[i].getLastName() + " scored a goal for "
							+ awayTeam.getTeamName());
					// update the game score
					System.out.println("Current score is: " + resultOfTheGame());
					found = 1;
					break;
				}
			}
		}
		// If the player is not found then the player is not in these teams
		if (found == 0) {
			System.out.println("The player is not in these teams\n");
		}
	}

	// set and get
	public Referee[] getRefereesList() {
		return this.refereesList;
	}

	public String getHomeName() {
		return this.homeTeam.getTeamName();
	}

	public String getAwayName() {
		return this.awayTeam.getTeamName();
	}

	public int getHomeGoals() {
		return this.homeGoals;
	}

	public int getAwayGoals() {
		return this.awayGoals;
	}

	public LocalDate getDate() {
		return this.gameDate;
	}

	public String getWinnerOfTheGame() {
		return this.winnerOfTheGame;
	}

	public Team getHomeTeam() {
		return this.homeTeam;
	}

	public Team getAwayTeam() {
		return this.awayTeam;
	}

	// Function that update the winner of the game when the game is end
	private String winnerOfTheGame() {
		if (homeGoals == awayGoals)
			return this.winnerOfTheGame = "The teams ended in a draw\n";
		if (homeGoals > awayGoals)
			return this.winnerOfTheGame = getHomeName();
		if (homeGoals < awayGoals)
			return winnerOfTheGame = getAwayName();
		return winnerOfTheGame;
	}

	@Override
	public String toString() {
		return "date: " + gameDate + "\nhome team: " + this.homeTeam + "\ncoach: " + this.homeTeam.getCoach()
				+ "\n\naway team: " + this.awayTeam + "\ncoach: " + this.awayTeam.getCoach()
				+ "\n\nTHE WINNER TEAM IS: " + winnerOfTheGame();
	}
}
