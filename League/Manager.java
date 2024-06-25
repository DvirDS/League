
public class Manager extends Employee {

	//constructor
	public Manager(String firstName, String lastName, String id, int seniority) {
		super(firstName, lastName, id, seniority);
	}
	
	@Override
	public int getSalary() {
		int y = seniority;
		return BASE_SALARY * 3 + y * 500;
	}
	
	@Override
	public boolean outstanding() {
		if (seniority > 4) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return super.toString() +
				"\noutstanding: " + outstanding() +
				"\nsalary: " + getSalary();
	}
}
