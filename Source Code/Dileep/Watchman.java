package FordFulkerson;

public class Watchman {
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		if((args[0]).equals("-f"))
			FordFulkerson.main(args);
		else if((args[0]).equals("-b"))
			BFS.main(args);
		else if((args[0]).equals("-m"))
			Museum.main(args);
		else
			System.out.println("Invalid Arguments");
	}
}
