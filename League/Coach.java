
public class Coach extends Employee {

	private String teamName;
	private boolean formerPlayer;
	private int numberOfChampionships;

	// constructor
	public Coach(String firstName, String lastName, String id, int seniority, String teamName, boolean pastPlayer,
			int numberOfChampionships) {
		super(firstName, lastName, id, seniority);
		this.teamName = teamName;
		this.formerPlayer = pastPlayer;
		this.numberOfChampionships = numberOfChampionships;
	}

	@Override
	public boolean outstanding() {
		if (numberOfChampionships >= 2) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getSalary() {
		int x = seniority;
		int y = numberOfChampionships;
		return BASE_SALARY + (200 * x) + (500 * y);
	}

	// Adding more championships
	public void addOneMoreChampionship() {
		numberOfChampionships += 1;
	}

	// set and get
	public void setPastPlayer(boolean pastPlayer) {
		this.formerPlayer = pastPlayer;
	}

	public boolean getPastPlayer() {
		return formerPlayer;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public int getNumberOfChampionships() {
		return this.numberOfChampionships;
	}

	@Override
	public String toString() {
		return super.toString() + "\nteam name: " + this.teamName + "\nformer player: " + this.formerPlayer
				+ "\nnumber of championships: " + this.numberOfChampionships + "\noutstanding: " + outstanding()
				+ "\nsalary: " + getSalary();

	}

}
