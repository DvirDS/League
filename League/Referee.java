
public class Referee extends Employee {

	private int errorsOfJudgment;
	private int totalGames;
	private int maxErrorsOfJudgment;

	// constructor
	public Referee(String firstName, String lastName, String id, int seniority, int errorsOfJudgment, int totalGames,
			int maxErrorsOfJudgment) {
		super(firstName, lastName, id, seniority);
		this.errorsOfJudgment = errorsOfJudgment;
		this.totalGames = totalGames;
		this.maxErrorsOfJudgment = maxErrorsOfJudgment;
	}

	@Override
	public boolean outstanding() {
		if (maxErrorsOfJudgment == 0) {
			return false;
		}
		double outstandingReferee = errorsOfJudgment / maxErrorsOfJudgment;

		if (outstandingReferee <= 0.5) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getSalary() {
		if (errorsOfJudgment == maxErrorsOfJudgment) {
			return (BASE_SALARY + (30 * totalGames)) - 500;
		} else {
			return BASE_SALARY + (30 * totalGames);
		}
	}

	// Adding errors of judgment
	public void addMoreErrorsOfJudgment(int addMoreErrorsOfJudgment) {
		this.errorsOfJudgment += addMoreErrorsOfJudgment;
		this.maxErrorsOfJudgment += addMoreErrorsOfJudgment;
		System.out.println("judgment errors has been updated");
	}

	// Adding games
	public void addGames(int addGames) {
		totalGames += addGames;
	}

	// set and get
	public int getErrorsOfJudgment() {
		return this.errorsOfJudgment;
	}

	public int getMaxErrorsOfJudgment() {
		return this.maxErrorsOfJudgment;
	}

	public void setTotalGames(int setTotalGames) {
		this.totalGames = setTotalGames;
	}

	public int getTotalGames() {
		return this.totalGames;
	}

	@Override
	public String toString() {
		return super.toString() + "\nerrors of judgment: " + this.errorsOfJudgment + "\nmax errors of judgment:"
				+ this.maxErrorsOfJudgment + "\ntotal games: " + this.totalGames + "\noutstanding: " + outstanding()
				+ "\nsalary: " + getSalary();
	}

}
