import java.time.LocalDate;
import java.util.Scanner;

public class Ligat_HaAl {

	LocalDate today = LocalDate.now();
	Scanner s = new Scanner(System.in);

	protected Team[] teamsList;
	protected Game[] gamesList;
	protected Employee[] employeesList;
	protected Referee[] refereesList;

	// constructor
	public Ligat_HaAl() {
		// Initialize the arrays
		employeesList = new Employee[0];
		teamsList = new Team[4];
		refereesList = new Referee[4];
		gamesList = new Game[12];
		// create the teams
		teamsList[0] = createHBS();
		teamsList[1] = createFCA();
		teamsList[2] = createMHA();
		teamsList[3] = createMTA();
		// create the referees
		refereesList = createReferees();
		// create Game board
		LocalDate currentDate = today;
		int k = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i != j) {
					gamesList[k] = new Game(currentDate, teamsList[i], teamsList[j], addRefereesToTheGame());
					currentDate = currentDate.plusDays(3);
					k++;
				}
			}
		}
	}

	public void Menu() {
		int num = 0;
		while (num != 12) {
			// Prints of the menu, the menu will be GREEN when the program is running
			System.out.println("\u001B[32mWhich option would you like to perform?");
			System.out.println("1  - Add Player");
			System.out.println("2  - Add Referee");
			System.out.println("3  - Print Team details");
			System.out.println("4  - Print referee details");
			System.out.println("5  - Print future games details");
			System.out.println("6  - Print employee salary");
			System.out.println("7  - Add Goal");
			System.out.println("8  - Add judgment errors");
			System.out.println("9  - Print outstanding people");
			System.out.println("10 - Print table");
			System.out.println("11 - Print player details with smallest salary");
			System.out.println("12 - Exit\u001B[0m");
			num = s.nextInt();
			s.nextLine();
			switch (num) {
			case 1:
				System.out.print("What is the name of the team? ");
				String team = s.nextLine();
				// A variable that checks if the player is present or not
				int playerFound = 0;
				// A variable that checks if the team is present or not
				int teamExist = 0;
				// A loop that goes through all the teams
				for (int i = 0; i < teamsList.length; i++) {
					// Checks if the team exist
					if (teamsList[i].getTeamName().equals(team)) {
						teamExist = 1;
						System.out.println("The selected team is: " + teamsList[i].getTeamName());
						System.out.println("Enter the player details");
						System.out.print("name: ");
						String firstName = s.next();
						System.out.print("last name: ");
						String lastName = s.next();
						System.out.print("id: ");
						String id = s.next();
						System.out.print("seniority: ");
						int seniority = s.nextInt();
						System.out.print("position: ");
						String position = s.next();
						// A loop checks if the player exists
						for (int j = 0; j < employeesList.length; j++) {
							if (employeesList[j].getId().equals(id)) {
								System.out.println("The player already exists\n");
								playerFound = 1;
								break;
							}
						}
						if (playerFound == 0) {
							// If no player is found then the player is created
							Player newPlayer = new Player(firstName, lastName, id, seniority,
									teamsList[i].getTeamName(), 0, 0, position);
							teamsList[i].addPlayer(newPlayer);
							System.out.println("The player joined the team\n");
							addPlayerToEmployeeList(newPlayer);
							break;
						}
					}
				}
				if (teamExist == 0) {
					// If the team does not exist a message will be printed
					System.out.println("The team does not exist\n");
				}
				break;

			case 2:
				// A variable that checks if the referee is present
				int refereeFound = 0;
				System.out.println("Enter the referee details");
				System.out.print("name: ");
				String firstName = s.next();
				System.out.print("last name: ");
				String lastName = s.next();
				System.out.print("id: ");
				String id = s.next();
				System.out.print("seniority: ");
				int seniority = s.nextInt();
				// A loop that checks if the referee exists
				for (int j = 0; j < employeesList.length; j++) {
					if (employeesList[j].getId().equals(id)) {
						System.out.println("The referee already exists\n");
						refereeFound = 1;
						break;
					}
				}
				if (refereeFound == 0) {
					// If the referee does not exist, he will be created
					Referee newReferee = new Referee(firstName, lastName, id, seniority, 0, 0, 0);
					Referee[] tempRefereeList = new Referee[refereesList.length + 1];
					for (int k = 0; k < refereesList.length; k++) {
						tempRefereeList[k] = refereesList[k];
					}
					// The new referee entered to the referees list
					tempRefereeList[tempRefereeList.length - 1] = newReferee;
					refereesList = tempRefereeList;
					addRefereeToEmployeeList(newReferee);
					System.out.println("The referee joined to the list\n");
				}
				break;

			case 3:
				// A variable that checks if the team is found
				int teamFound = 0;
				// Printing of the teams
				for (Team teamsList : teamsList) {
					System.out.println(teamsList.getTeamName());
				}
				// A loop that will continue until the input is correct for one of the teams
				while (teamFound == 0) {
					System.out.print("Which football team are you interested in? ");
					String teamInfo = s.nextLine();
					for (int i = 0; i < teamsList.length; i++) {
						if (teamsList[i].getTeamName().equals(teamInfo)) {
							System.out.println(teamsList[i]);
							teamFound = 1;
						}
					}
					if (teamFound == 0) {
						System.out.println("Invalid input, try again\n");
					}
				}
				break;

			case 4:
				// A loop that goes through the current referee each time
				for (Referee currentReferees : refereesList) {
					System.out.println(currentReferees);
					System.out.println("The games that the referee officiated:");
					// A loop goes through the entire list of games
					for (int i = 0; i < gamesList.length; i++) {
						// A loop that running over the list of referees in the current game
						for (int j = 0; j < gamesList[i].getRefereesList().length; j++) {
							// Checks by ID if the referee participated in the current game
							if (gamesList[i].getRefereesList()[j].getId().equals(currentReferees.getId())) {
								System.out.println(gamesList[i].getHomeName() + " - " + gamesList[i].getHomeGoals());
								System.out.println(gamesList[i].getAwayName() + " - " + gamesList[i].getAwayGoals());
								System.out.println();
								break;
							}
						}
					}
					System.out.println();
				}
				break;

			case 5:
				// Entering a date input
				System.out.println("enter a date:");
				System.out.print("day: ");
				int day = s.nextInt();
				System.out.print("month: ");
				int month = s.nextInt();
				System.out.print("year: ");
				int year = s.nextInt();
				LocalDate inputDate = LocalDate.of(year, month, day);
				System.out.println("These are the games that will take place in the future:");
				// A loop goes through the list of games
				for (int i = 0; i < gamesList.length; i++) {
					int result = gamesList[i].getDate().compareTo(inputDate);
					// Checks if the date of the current game is greater than the date entered as input
					if (result > 0) {
						System.out.println("date: " + gamesList[i].getDate());
						System.out.println("home team: " + gamesList[i].getHomeName());
						System.out.println("away team: " + gamesList[i].getAwayName());
						System.out.println();
					}
				}
				System.out.println();
				break;

			case 6:
				// A variable that checks if the ID is found
				int foundById = 0;
				while (foundById == 0) {
					System.out.print("enter id: ");
					String searchById = s.next();
					for (int i = 0; i < employeesList.length; i++) {
						// check if the ID already exists
						if (employeesList[i].getId().equals(searchById)) {
							System.out.print("This employee's salary is: " + employeesList[i].getSalary() + "\n\n");
							foundById = 1;
							break;
						}
					}
					// Comes here if ID not found
					if (foundById == 0)
						System.out.println("No employee exists with this ID, try again\n");
				}
				break;

			case 7:
				// Add a goal to the current game
				// Printing the list of games
				System.out.println("Game List: ");
				for (int gameIndex = 0; gameIndex < gamesList.length; gameIndex++) {
					System.out.println(gameIndex + 1 + ". " + gamesList[gameIndex].getDate() + " "
							+ gamesList[gameIndex].getHomeName() + " - " + gamesList[gameIndex].getAwayName());
				}
				System.out.print("Select a game number: ");
				// Select the **CURRENT** game.
				// if the current game is not selected, a message will be printed that it is not the correct game
				int gameNumber = s.nextInt();
				if (gameNumber >= 1 && gameNumber <= 12) {
					if (gamesList[gameNumber - 1].getDate().equals(today)) {
						System.out.print("Select PlayerID number: ");
						String PlayerID = s.next();
						gamesList[gameNumber - 1].updateGoalsInGame(PlayerID);
						break;
					} else {
						System.out.println("This is not the current game\n");
					}
				} else {
					System.out.println("Invalid Input\n");
				}
				break;

			case 8:
				// Print the list of all the games and the referees that were in each game
				System.out.println("Game List: ");
				for (int gameIndexReferee = 0; gameIndexReferee < gamesList.length; gameIndexReferee++) {
					System.out.println(gameIndexReferee + 1 + ". " + gamesList[gameIndexReferee].getDate() + " "
							+ gamesList[gameIndexReferee].getHomeName() + " - " + gamesList[gameIndexReferee].getAwayName());
					for (int j = 0; j < gamesList[gameIndexReferee].getRefereesList().length; j++)
						System.out.println(gamesList[gameIndexReferee].getRefereesList()[j] + "\n\n");
				}
				// Choosing the game and the referee
				System.out.print("Select a game number: ");
				int refereeGame = s.nextInt();
				System.out.print("Referee first name: ");
				String refereeFirstName = s.next();
				System.out.print("Referee last name: ");
				String refereeLastName = s.next();
				// A variable that checks if the referee is present in the game
				int refereeExists = 0;
				for (int i = 0; i < gamesList[refereeGame - 1].getRefereesList().length; i++) {
					// Checking that the referee was actually present at the selected game
					if (gamesList[refereeGame - 1].getRefereesList()[i].getFirstName().equals(refereeFirstName)
							&& gamesList[refereeGame - 1].getRefereesList()[i].getLastName().equals(refereeLastName)) {
						refereeExists = 1;
						System.out.println("How many judgment errors would you like to add?");
						int addMoreErrorsOfJudgment = s.nextInt();
						// Adding errors to the referee
						gamesList[refereeGame - 1].getRefereesList()[i].addMoreErrorsOfJudgment(addMoreErrorsOfJudgment);
						System.out.println();
						break;
					}
				}
				// If the referee is not in the game
				if (refereeExists == 0) {
					System.out.println("The referee is not in this game\n");
					break;
				}
				break;

			case 9:
				// Printing outstanding people according to the criteria
				System.out.println("outstanding people:");
				for (int i = 0; i < employeesList.length; i++) {
					if (employeesList[i].outstanding() == true) {
						if (employeesList[i] instanceof Player)
							System.out.println("Player: \n" + employeesList[i]);
						if (employeesList[i] instanceof Coach)
							System.out.println("Coach: \n" + employeesList[i]);
						if (employeesList[i] instanceof Manager)
							System.out.println("Manager: \n" + employeesList[i]);
						if (employeesList[i] instanceof Referee)
							System.out.println("Referee: \n" + employeesList[i]);
						System.out.println();
					}
				}
				break;

			case 10:
				// Printing the game board
				printTable();
				break;

			case 11:
				System.out.println("\nplayer details with smallest salary: ");
				// A variable that holds the lowest salary
				int lowestSalary = 0;
				// A variable that holds the position of the player with the lowest salary
				int index = 0;
				// Creates generic number for variable "lowestSalary" to have some high number
				for (int i = 0; i < employeesList.length; i++) {
					lowestSalary += employeesList[i].getSalary();
				}
				// Checks who has the lowest salary
				for (int i = 0; i < employeesList.length; i++) {
					if (employeesList[i] instanceof Player) {
						int current = employeesList[i].getSalary();
						if (current < lowestSalary) {
							lowestSalary = current;
							index = i;
						}
					}
				}
				// Print the player with the lowest salary
				System.out.println(employeesList[index] + "\n");
				break;

			case 12:
				// Exit the current game and continue to the next game
				num = 12;
				break;

			default:
				System.out.println("Invalid input");

				break;
			}
		}
	}
	// Private function to create the referees and add them to the list of employees
	private Referee[] createReferees() {
		Referee[] refereesList = new Referee[4];
		refereesList[0] = new Referee("Liran", "Liany", "8000", 8, 20, 10, 30);
		addRefereeToEmployeeList(refereesList[0]);
		refereesList[1] = new Referee("Orel", "Grinfeld", "8001", 20, 2, 10, 10);
		addRefereeToEmployeeList(refereesList[1]);
		refereesList[2] = new Referee("Ziv", "Adler", "8002", 5, 8, 12, 20);
		addRefereeToEmployeeList(refereesList[2]);
		refereesList[3] = new Referee("Sapir", "Berman", "8003", 10, 6, 1, 12);
		addRefereeToEmployeeList(refereesList[3]);
		return refereesList;
	}
	// Private function to create a team
	private Team createHBS() {
		Player[] hbsPlayers = new Player[] {
				new Player("Omri", "Glazer", "9000", 1, "Hapoel Beer Sheva", 49, 30, "Goal Keeper"),
				new Player("Ariel", "Harush", "9001", 2, "Hapoel Beer Sheva", 7, 3, "Goal Keeper"),
				new Player("Miguel", "Vitor", "9002", 6, "Hapoel Beer Sheva", 75, 68, "Defender"),
				new Player("Or", "Dadia", "9005", 3, "Hapoel Beer Sheva", 40, 20, "Defender"),
				new Player("Eytan", "Tibi", "9006", 1, "Hapoel Beer Sheva", 62, 47, "Defender"),
				new Player("Ramsey", "Safury", "9008", 3, "Hapoel Beer Sheva", 17, 12, "Mid Fielder"),
				new Player("Dor", "Micha", "9009", 1, "Hapoel Beer Sheva", 26, 21, "Mid Fielder"),
				new Player("Shai", "Elias", "9010", 1, "Hapoel Beer Sheva", 9, 5, "Mid Fielder"),
				new Player("Roi", "Gordana", "9011", 4, "Hapoel Beer Sheva", 14, 9, "Mid Fielder"),
				new Player("Tomer", "Hemed", "9012", 1, "Hapoel Beer Sheva", 13, 9, "Forward"),
				new Player("Rotem", "Hatuel", "9013", 2, "Hapoel Beer Sheva", 22, 16, "Forward"),
				new Player("Itay", "Shechter", "9014", 1, "Hapoel Beer Sheva", 30, 30, "Forward") };
		// Adding the team's players to the list of employees
		for (int i = 0; i < hbsPlayers.length; i++) {
			addPlayerToEmployeeList(hbsPlayers[i]);
		}
		// Creating the coach and adding him to the list of employees
		Coach hbsCoach = new Coach("Elyaniv", " Barda", "3000", 3, "Hapoel Beer Sheva", true, 2);
		addCoachToEmployeeList(hbsCoach);
		// Creating the manager and adding him to the list of employees
		Manager hbsManager = new Manager("Alona", "Barkat", "1001", 16);
		addManagerToEmployeeList(hbsManager);

		Team hbs = new Team("Hapoel Beer Sheva", "Turner Stadium", hbsPlayers, hbsCoach, hbsManager);

		return hbs;
	}
	// Private function to create a team
	private Team createFCA() {
		Player[] fcaPlayers = new Player[] {
				new Player("Yoav", "Gerafi", "9115", 1, "F.C. Ashdod", 40, 20, "Goal Keeper"),
				new Player("Tomer", "Amar", "9116", 5, "F.C. Ashdod", 25, 13, "Goal Keeper"),
				new Player("Timothy", "Awany", "9117", 6, "F.C. Ashdod", 44, 35, "Defender"),
				new Player("Nenad", "Cvetkovic", "9118", 3, "F.C. Ashdod", 41, 30, "Defender"),
				new Player("Gil", "Cohen", "9119", 1, "F.C. Ashdod", 55, 27, "Defender"),
				new Player("Michael", "Ohana", "9120", 3, "F.C. Ashdod", 18, 15, "Mid Fielder"),
				new Player("Mohammed", "Knaan", "9121", 1, "F.C. Ashdod", 30, 21, "Mid Fielder"),
				new Player("Naor", "Sabag", "9122", 1, "F.C. Ashdod", 20, 14, "Mid Fielder"),
				new Player("Yaakov", "Berihon", "9123", 4, "F.C. Ashdod", 15, 10, "Mid Fielder"),
				new Player("Elton", "Acolatse", "9124", 1, "F.C. Ashdod", 19, 9, "Forward"),
				new Player("Roey", "Ben-Shimon", "9125", 2, "F.C. Ashdod", 25, 17, "Forward"),
				new Player("Adir", "Levi", "9126", 1, "F.C. Ashdod", 7, 4, "Forward") };
		// Adding the team's players to the list of employees
		for (int i = 0; i < fcaPlayers.length; i++) {
			addPlayerToEmployeeList(fcaPlayers[i]);
		}
		// Creating the coach and adding him to the list of employees
		Coach fcaCoach = new Coach("Ran", "Ben-Shimon", "3001", 3, "F.C. Ashdod", true, 2);
		addCoachToEmployeeList(fcaCoach);
		// Creating the manager and adding him to the list of employees
		Manager fcaManager = new Manager("Rafi", "Nidam", "1002", 5);
		addManagerToEmployeeList(fcaManager);

		Team fca = new Team("F.C. Ashdod", "Yud-Alef Stadium", fcaPlayers, fcaCoach, fcaManager);

		return fca;
	}
	// Private function to create a team
	private Team createMHA() {
		Player[] mhaPlayers = new Player[] {
				new Player("Josh", "Cohen", "9339", 1, "Maccabi Haifa", 33, 27, "Goal Keeper"),
				new Player("Roee", "Fuchs", "9340", 5, "Maccabi Haifa", 29, 20, "Goal Keeper"),
				new Player("Rami", "Gershon", "9341", 6, "Maccabi Haifa", 60, 35, "Defender"),
				new Player("Inon", "Eliyahu", "9342", 3, "Maccabi Haifa", 37, 26, "Defender"),
				new Player("Sun", "Menahem", "9343", 1, "Maccabi Haifa", 20, 15, "Defender"),
				new Player("Mohammad", "Abu Fani", "9344", 3, "Maccabi Haifa", 17, 9, "Mid Fielder"),
				new Player("Dolev", "Haziza", "9345", 1, "Maccabi Haifa", 34, 28, "Mid Fielder"),
				new Player("Aviel", "Zargari", "9346", 1, "Maccabi Haifa", 18, 14, "Mid Fielder"),
				new Player("Ali", "Mohamed", "9347", 4, "Maccabi Haifa", 23, 13, "Mid Fielder"),
				new Player("Dean", "David", "9348", 1, "Maccabi Haifa", 20, 12, "Forward"),
				new Player("Nikita", "Rukavytsya", "9349", 2, "Maccabi Haifa", 29, 20, "Forward"),
				new Player("Frantzdy", "Pierrot", "9350", 1, "Maccabi Haifa", 11, 5, "Forward") };
		// Adding the team's players to the list of employees
		for (int i = 0; i < mhaPlayers.length; i++) {
			addPlayerToEmployeeList(mhaPlayers[i]);
		}
		// Creating the coach and adding him to the list of employees
		Coach mhaCoach = new Coach("Barak", " Bakhar", "3002", 5, "Maccabi Haifa", true, 3);
		addCoachToEmployeeList(mhaCoach);
		// Creating the manager and adding him to the list of employees
		Manager mhaManager = new Manager("Yaakov", "Shahar", "1003", 31);
		addManagerToEmployeeList(mhaManager);

		Team mha = new Team("Maccabi Haifa", "Sammy Ofer Stadium", mhaPlayers, mhaCoach, mhaManager);

		return mha;
	}
	// // Private function to create a team
	private Team createMTA() {
		Player[] mtaPlayers = new Player[] {
				new Player("Daniel", "Peretz", "9451", 1, "Maccabi Tel Aviv", 40, 30, "Goal Keeper"),
				new Player("Daniel", "Tenenbaum", "9452", 5, "Maccabi Tel Aviv", 31, 17, "Goal Keeper"),
				new Player("Enric", "Saborit", "9453", 6, "Maccabi Tel Aviv", 24, 20, "Defender"),
				new Player("Idan", "Nachmias", "9454", 3, "Maccabi Tel Aviv", 36, 26, "Defender"),
				new Player("Sheran", "Yeini", "9455", 1, "Maccabi Tel Aviv", 15, 8, "Defender"),
				new Player("Dan", "Glazer", "9456", 3, "Maccabi Tel Aviv", 20, 11, "Mid Fielder"),
				new Player("Dan", "Biton", "9457", 1, "Maccabi Tel Aviv", 40, 24, "Mid Fielder"),
				new Player("Dor", "Peretz", "9458", 1, "Maccabi Tel Aviv", 24, 11, "Mid Fielder"),
				new Player("Eyal", "Golasa", "9459", 4, "Maccabi Tel Aviv", 30, 22, "Mid Fielder"),
				new Player("Eran", "Zahavi", "9460", 1, "Maccabi Tel Aviv", 32, 27, "Forward"),
				new Player("Yonatan", "Cohen", "9461", 2, "Maccabi Tel Aviv", 22, 20, "Forward"),
				new Player("Eylon", "Almog", "9462", 1, "Maccabi Tel Aviv", 19, 18, "Forward") };
		// Adding the team's players to the list of employees
		for (int i = 0; i < mtaPlayers.length; i++) {
			addPlayerToEmployeeList(mtaPlayers[i]);
		}
		// Creating the coach and adding him to the list of employees
		Coach mtaCoach = new Coach("Aitor", "Karanka", "3003", 3, "Maccabi Tel Aviv", true, 7);
		addCoachToEmployeeList(mtaCoach);
		// Creating the manager and adding him to the list of employees
		Manager mtaManager = new Manager("Mitchell", "Goldhar", "1004", 3);
		addManagerToEmployeeList(mtaManager);

		Team mta = new Team("Maccabi Tel Aviv", "Bloomfield Stadium", mtaPlayers, mtaCoach, mtaManager);

		return mta;
	}
	// A private function for adding the players to the list of employees
	private void addPlayerToEmployeeList(Player addPlayer) {
		Employee[] temp = new Employee[employeesList.length + 1];
		for (int i = 0; i < employeesList.length; i++) {
			temp[i] = employeesList[i];
		}
		temp[temp.length - 1] = addPlayer;
		employeesList = temp;
	}
	// A private function for adding the coach to the list of employees
	private void addCoachToEmployeeList(Coach addCoach) {
		Employee[] temp = new Employee[employeesList.length + 1];
		for (int i = 0; i < employeesList.length; i++) {
			temp[i] = employeesList[i];
		}
		temp[temp.length - 1] = addCoach;
		employeesList = temp;
	}
	// A private function for adding the manager to the list of employees
	private void addManagerToEmployeeList(Manager addManager) {
		Employee[] temp = new Employee[employeesList.length + 1];
		for (int i = 0; i < employeesList.length; i++) {
			temp[i] = employeesList[i];
		}
		temp[temp.length - 1] = addManager;
		employeesList = temp;
	}
	// A private function for adding the referees to the list of employees
	private void addRefereeToEmployeeList(Referee addReferee) {
		Employee[] temp = new Employee[employeesList.length + 1];
		for (int k = 0; k < temp.length - 1; k++) {
			temp[k] = employeesList[k];
		}
		temp[temp.length - 1] = addReferee;
		this.employeesList = temp;
	}

	private static int currentIndex = 0;
	// A private function to add only 3 referees for each game
	private Referee[] addRefereesToTheGame() {
		Referee[] gameRefereesList = new Referee[3];
		for (int j = 0; j < 3; j++) {
			gameRefereesList[j] = refereesList[currentIndex % refereesList.length];
			currentIndex++;
		}
		return gameRefereesList;
	}
	// A private function for printing the game board and also printing the final board
	private void printTable() {
		// Creating arrays for each parameter in the game board
		int[] teamPoints = { 0, 0, 0, 0 };
		int[] goalsFor = { 0, 0, 0, 0 };
		int[] goalsAgainst = { 0, 0, 0, 0 };
		int[] won = { 0, 0, 0, 0 };
		int[] lost = { 0, 0, 0, 0 };
		int[] drawn = { 0, 0, 0, 0 };
		// add points to the teams
		// A loop that goes through the list of games
		for (int i = 0; i < gamesList.length; i++) {
			int result = gamesList[i].getDate().compareTo(today);
			// A condition that checks only the games that have already been played and updates the team table
			if (result <= -1) {
				for (int j = 0; j < teamsList.length; j++) { 
					// A condition that checks if the home team is in the current game
					if (gamesList[i].getHomeName().equals(teamsList[j].getTeamName())) {
						for (int k = 0; k < teamsList.length; k++) {
							// A condition that checks if the away team is in the current game
							if (gamesList[i].getAwayName().equals(teamsList[k].getTeamName())) {
								// Updates the parameters if the game ends in a draw
								if (gamesList[i].getHomeGoals() == gamesList[i].getAwayGoals()) {
									teamPoints[j] += 1;
									teamPoints[k] += 1;
									goalsFor[j] += gamesList[i].getHomeGoals();
									goalsFor[k] += gamesList[i].getAwayGoals();
									goalsAgainst[j] += gamesList[i].getAwayGoals();
									goalsAgainst[k] += gamesList[i].getHomeGoals();
									drawn[j] += 1;
									drawn[k] += 1;
									break;
								}
								// Updates the parameters if the home team wins
								if (gamesList[i].getHomeGoals() > gamesList[i].getAwayGoals()) {
									teamPoints[j] += 3;
									goalsFor[j] += gamesList[i].getHomeGoals();
									goalsFor[k] += gamesList[i].getAwayGoals();
									goalsAgainst[j] += gamesList[i].getAwayGoals();
									goalsAgainst[k] += gamesList[i].getHomeGoals();
									won[j] += 1;
									lost[k] += 1;
									break;
								}
								// Updates the parameters if the away team wins
								if (gamesList[i].getHomeGoals() < gamesList[i].getAwayGoals()) {
									teamPoints[k] += 3;
									goalsFor[j] += gamesList[i].getHomeGoals();
									goalsFor[k] += gamesList[i].getAwayGoals();
									goalsAgainst[j] += gamesList[i].getAwayGoals();
									goalsAgainst[k] += gamesList[i].getHomeGoals();
									won[k] += 1;
									lost[j] += 1;
									break;
								}
							}
						}
					}
				}
				// Sorts the table according to the parameters
				for (int j = 0; j < teamPoints.length - 1; j++) {
					for (int k = 0; k < teamPoints.length - j - 1; k++) {
						if (teamPoints[k] < teamPoints[k + 1]) {
							int tempPoints = teamPoints[k];
							Team tempTeam = teamsList[k];
							teamPoints[k] = teamPoints[k + 1];
							teamsList[k] = teamsList[k + 1];
							teamPoints[k + 1] = tempPoints;
							teamsList[k + 1] = tempTeam;
							int tempGoalsFor = goalsFor[k];
							goalsFor[k] = goalsFor[k + 1];
							goalsFor[k + 1] = tempGoalsFor;
							int tempGoalsAgainst = goalsAgainst[k];
							goalsAgainst[k] = goalsAgainst[k + 1];
							goalsAgainst[k + 1] = tempGoalsAgainst;
							int tempWon = won[k];
							won[k] = won[k + 1];
							won[k + 1] = tempWon;
							int tempLost = lost[k];
							lost[k] = lost[k + 1];
							lost[k + 1] = tempLost;
							int tempDrawn = drawn[k];
							drawn[k] = drawn[k + 1];
							drawn[k + 1] = tempDrawn;
						}
					}
				}
			}
		}
		// print the table
		int sum = gamesList[gamesList.length - 1].getDate().compareTo(today);
		// A condition that checks if all games have finished
		if (sum < -1) {
			// Printing the final table after all games are finished
			for (int t = 0; t < 4; t++) {
				System.out.println(t + 1 + " - " + teamsList[t].getTeamName() + " - Points: " + teamPoints[t] + " Won: "
						+ won[t] + " Drawn: " + drawn[t] + " Lost: " + lost[t] + " Goals For: " + goalsFor[t]
						+ " Goals Against: " + goalsAgainst[t]);
			}
		} else {
			// Printing the table during the tournament
			for (int t = 0; t < teamPoints.length; t++) {
				System.out.println(t + 1 + " - " + teamsList[t].getTeamName() + ", Points: " + teamPoints[t]);
			}
			System.out.println();
		}
	}

}
