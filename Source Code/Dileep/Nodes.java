package FordFulkerson;

class Nodes {
	String Name;
	int Capacity;
	int Level;
	boolean visited;

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public int getCapacity() {
		return Capacity;
	}

	public void setCapacity(int Capacity) {
		this.Capacity = Capacity;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int Level) {
		this.Level = Level;
	}

	public Nodes(String Name) {
		this.Name = Name;
		visited = false;
	}

	public Nodes(String Name, int capacity) {
		this.Name = Name;
		Capacity = capacity;
		visited = false;
	}

	public void printme() {
		System.out.println("Name: " + getName());
		System.out.println("Capacity: " + getCapacity());
		System.out.println("Level :" + getLevel() + "\n");
	}
}
