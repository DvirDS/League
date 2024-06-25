
public abstract class Person {

	protected String firstName;
	protected String lastName;
	protected String id;

	// constructor
	public Person(String firstName, String lastName, String id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	// abstract function
	public abstract boolean outstanding();

	// set and get
	public void setFirstName(String FirstName) {
		this.firstName = FirstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void getLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "first name: " + this.firstName + "\nlast name: " + this.lastName + "\nid: " + this.id;
	}
}
