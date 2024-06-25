
public class Team {

	private String teamName;
	private String stadiumName;
	private Player[] playersList;
	private int numOfPlayers;
	private Coach teamCoach;
	private Manager teamManager;

	//constructor
	public Team(String teamName, String stadiumName, Player[] playersList, Coach teamCoach, Manager teamManager) {
		this.teamName = teamName;
		this.stadiumName = stadiumName;
		this.teamCoach = teamCoach;
		this.teamManager = teamManager;
		this.playersList = playersList;
		this.numOfPlayers = playersList.length;
	}

	//Function to add a player
	public void addPlayer(Player newPlayer) {
		Player[] temp = new Player[playersList.length + 1];
		for (int i = 0; i < temp.length-1; i++) {
			temp[i] = playersList[i];
		}
		temp[temp.length-1] = newPlayer;
		this.playersList = temp;
	}

	//Function to add a coach
	public void addCoach(Coach newCoach) {
		if (teamCoach == null) {
			this.teamCoach = newCoach;
		} else {
			System.out.println("There is already a coach for this team");
		}
	}

	//Function to add a Manager
	public void addManager(Manager newManager) {
		if (teamManager == null) {
			this.teamManager = newManager;
		} else {
			System.out.println("There is already a manager for this team");
		}
	}

	//Player search function by ID
	public void playerById(String id) {
		int found = 0;
		for (int i = 0; i < playersList.length; i++) {
			if (playersList[i].getId().equals(id)) {
				System.out.println(playersList[i].toString());
				found = 1;
				break;
			}
		}
		if (found == 0) {
			System.out.println("There is no player according to this id");
		}
	}

	//Player search function by name
	public void playerByName(String firstName, String lastName) {
		int found = 0;
		for (int i = 0; i < playersList.length; i++) {
			if (playersList[i].getFirstName().equals(firstName) && playersList[i].getLastName().equals(lastName)) {
				System.out.println(playersList[i].toString());
				found = 1;
				break;
			}
		}
		if (found == 0) {
			System.out.println("There is no player according to this name");
		}
	}

	//set and get
	public String getTeamName() {
		return this.teamName;
	}

	public String getStadiumName() {
		return this.stadiumName;
	}

	public int getNumOfPlayers() {
		return this.numOfPlayers;
	}

	public Coach getCoach() {
		return this.teamCoach;
	}

	public Manager getManager() {
		return this.teamManager;
	}
	
	public Player[] getPlayersList() {
		return this.playersList;
	}
	
	@Override
	public String toString() {
		String print = "team name: " + this.teamName + "\nstadium name: " + this.stadiumName;
		System.out.print("\nPlayers:");
		for (Player players : playersList) {
			System.out.print("\n"+players+"\n");
		}
		print = "\nCoach:\n" + this.teamCoach + "\n\nManager: \n" + this.teamManager + "\n";
		return print;
	}

}
