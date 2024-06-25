
public abstract class Employee extends Person {

	protected final int BASE_SALARY = 4500;
	protected int seniority;

	// constructor
	public Employee(String firstName, String lastName, String id, int seniority) {
		super(firstName, lastName, id);
		this.seniority = seniority;
	}

	// abstract function
	public abstract int getSalary();

	// set and get
	public void setSeniority(int seniority) {
		this.seniority = seniority;
	}

	public int getSeniority() {
		return this.seniority;
	}

	@Override
	public String toString() {
		return super.toString() + "\nseniority in years: " + this.seniority;
	}
}
