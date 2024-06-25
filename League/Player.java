
public class Player extends Employee {

	private String teamName;
	private int totalAttempts;
	private final int BASE_SALARY = 6500;
	private int successfulAttempts;
	private String position;

	// constructor
	public Player(String firstName, String lastName, String id, int seniority, String teamName, int totalAttempts,
			int successfulAttempts, String position) {
		super(firstName, lastName, id, seniority);
		this.teamName = teamName;
		this.totalAttempts = totalAttempts;
		this.successfulAttempts = successfulAttempts;
		this.position = position;
	}

	// Calculation of success rates
	public double calculateSuccessPercentage() {
		if (totalAttempts == 0) {
			return 0;
		} else {
			double successPercentage = (((double) successfulAttempts / (double) totalAttempts)) * 100;
			successPercentage = Math.round(successPercentage * 10.0) / 10.0;
			return successPercentage;
		}
	}

	@Override
	public boolean outstanding() {
		if (totalAttempts == 0) {
			return false;
		}
		if (calculateSuccessPercentage() > 75) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getSalary() {
		double x = calculateSuccessPercentage();
		double sum = (6500 + (x / 100) * 2000);
		int result = (int) sum;
		return result;
	}

	// Adding attempts
	public void addMoreAttempts(int addMoreAttempts) {
		this.totalAttempts += addMoreAttempts;
	}

	// Adding successful attempts
	public void addMoreSuccessfulAttempts(int addMoreSuccessfulAttempts) {
		this.successfulAttempts += addMoreSuccessfulAttempts;
		this.totalAttempts += addMoreSuccessfulAttempts;
	}

	// set and get
	public int getBaseSalary() {
		return this.BASE_SALARY;
	}

	public String getPosition() {
		return this.position;
	}

	public int getAttempts() {
		return this.totalAttempts;
	}

	public int getSuccessfulAttempts() {
		return this.successfulAttempts;
	}

	public String getTeamName() {
		return this.teamName;
	}

	@Override
	public String toString() {
		return super.toString() + "\ntotal attempts: " + this.totalAttempts + "\nsuccessful attempts: "
				+ successfulAttempts + "\nposition: " + this.position + "\nsuccess percentage: "
				+ calculateSuccessPercentage() + "\noutstanding: " + outstanding() + "\nsalary: " + getSalary();
	}

}
