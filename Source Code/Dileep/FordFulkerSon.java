package FordFulkerson;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

class FordFulkerson {

	private static HashMap<String, HashMap<String, Nodes>> InputGraph,
			LevelGraph, tempGraph;
	private static HashMap<String, Nodes> ConnectedEdges, BfsNodes, NewFlowG,
			NewFlow2;
	static int NodeNumber = 0, DestLevel = 0, MaxFlow = 0, MinCapacity,
			level = 0,count=0;
	private static LinkedList<Nodes> ShortPath, TraversalPath;
	static long startTime,endTime ;
	static String nline = "", source, destination;
	private static Scanner s;
	static boolean visited[];
	private static BufferedReader r;
    private static int g;
    private static BufferedWriter bw1;
    private static String s2;
	public static void main(String args[]) throws Exception {
		
		System.out.println("Max flow:"+getMax(args[1],"0","0",0,"out.txt"));
		g=0;
		
		}

	static int getMax(String file, String src, String des,int g1,String file1) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		startTime = System.currentTimeMillis();
		g=g1;
		File fout1 = new File(file1);
		FileOutputStream fos = new FileOutputStream(fout1);
		bw1 = new BufferedWriter(new OutputStreamWriter(fos));
		r = new BufferedReader(new FileReader(file));
		InputGraph = new HashMap<String, HashMap<String, Nodes>>();
		while ((nline = r.readLine()) != null) {
			ConnectedEdges = new HashMap<String, Nodes>();
			StringTokenizer st = new StringTokenizer(nline, " ");
			while (st.hasMoreTokens()) {
				String name = st.nextToken();
				int capacity = Integer.parseInt(st.nextToken());
				ConnectedEdges.put(name, new Nodes(name, capacity));
				
			}
			count++;
			InputGraph.put(Integer.toString(NodeNumber++), ConnectedEdges);
		}
		
		setBFS(InputGraph);
		source = new Integer(0).toString();
		destination = new Integer(count-1).toString();
		BfsNodes = doBfs(new Nodes(source));
		ShortPath = getShortPath();
		if(g!=0)
		for(int j=1;j<ShortPath.size()-2;j++)
		{
			String name=ShortPath.get(j).getName();
			int k= Integer.parseInt(ShortPath.get(j+1).getName());
			k=k-g;
			if(k<0)
				k=-k;
			s2="G"+name+":A"+k;
			bw1.write(s2);
			System.out.println("G"+name+":A"+k);
		}
			/*ArrayList<String> als;
			if(watch.keySet().contains(name))
			{
			als=watch.get(name);
			int k= Integer.parseInt(ShortPath.get(j+1).getName());
			k=k-g;
			als.add("A"+k);
			watch.put(name, als);
			}
			else
			{
				als=new ArrayList<String>();
				int k= Integer.parseInt(ShortPath.get(j+1).getName());
				k=k-g;
				als.add("A"+k);
				watch.put(name, als);
			}*/
 		
		
		
		
		/*fos = new FileOutputStream(fout);
		bw = new BufferedWriter(new OutputStreamWriter(fos));
		bw.append(ShortPath.toString());
		
		bw.newLine();
		bw.close();
		*/
		MinCapacity = getmaxFlow(ShortPath);

		constructResidualGraph(MinCapacity, ShortPath);
		
		endTime=System.currentTimeMillis();
		System.out.println("\nThe time of execution(Ford Fulkerson) is "
				+ (endTime - startTime) + " millisecs");
		String s5= new String("");
		String s6= new String("");
		/*if(g!=0)
		{
			System.out.println("Yes");
			for(String s:watch.keySet())
			{
				System.out.println("okay");
				s5="G"+s+":";
				ArrayList<String> aa=watch.get(s);
				for(String s7: aa)
				{
					s6=s6+" "+s7;
				}
				System.out.println(s5+s6);
			}
		}*/
		
		return MaxFlow;
	}

	private static HashMap<String, HashMap<String, Nodes>> constructResidualGraph(
			int c, LinkedList<Nodes> shortPath2) {
		// TODO Auto-generated method stub

		MaxFlow = MaxFlow + c;
		tempGraph = InputGraph;
		for (int i = 0; i <= shortPath2.size() - 2; i++) {
			Nodes one = ShortPath.get(i);
			Nodes two = ShortPath.get(i + 1);
			NewFlowG = InputGraph.get(one.getName());
			Iterator<String> it = NewFlowG.keySet().iterator();
			while (it.hasNext()) {
				Nodes sec = NewFlowG.get(it.next());
				int cc = sec.getCapacity();
				if (sec.getName().equals(two.getName())) {
					if (cc == c) {
						NewFlowG.remove(sec.getName());
					} else {
						int updcap = sec.getCapacity() - c;
						sec.setCapacity(updcap);
					}
					NewFlow2 = InputGraph.get(sec.getName());
					Iterator<String> it2 = NewFlow2.keySet().iterator();
					int flag = -1;
					while (it2.hasNext()) {
						if (it2.next().equals(one.getName())) {
							flag = 1;

						}
					}
					if (flag == -1) {
						Nodes n = new Nodes(one.getName(), c);
						NewFlow2.put(n.getName(), n);
						flag = -1;
					}

					tempGraph.put(one.getName(), NewFlowG);
					tempGraph.put(two.getName(), NewFlow2);
					break;
				}
			}
		}
		setBFS(tempGraph);

		BfsNodes = doBfs(new Nodes(source));
		try {
			ShortPath = getShortPath();
			if(g!=0)
			for(int j=1;j<ShortPath.size()-2;j++)
			{
				String name=ShortPath.get(j).getName();
				int k= Integer.parseInt(ShortPath.get(j+1).getName());
				k=k-g;

				if(k<0)
					k=-k;
				s2="G"+name+":A"+k;
				bw1.write(s2);
				bw1.newLine();
				System.out.println("G"+name+":A"+k);
			}
/*			for(int j=1;j<ShortPath.size()-2;j++)
			{
				String name=ShortPath.get(j).getName();
				
				System.out.println("G"+name+":A"+ShortPath.get(j+1).getName());
				ArrayList<String> als;
				if(watch.keySet().contains(name))
				{
				als=watch.get(name);
				int k= Integer.parseInt(ShortPath.get(j+1).getName());
				k=k-g;
				als.add("A"+k);
				watch.put(name, als);
				}
				else
				{
					als=new ArrayList<String>();
					int k= Integer.parseInt(ShortPath.get(j+1).getName());
					k=k-g;
					als.add("A"+k);
					watch.put(name, als);
				}
*/			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Nodes> its = ShortPath.iterator();

		if (its.hasNext() && ShortPath.size() != 1) {
						int tempFlow = getmaxFlow(ShortPath);
			
			if (tempFlow != -1 && tempFlow != 0) {
				return constructResidualGraph(tempFlow, ShortPath);
			} else {
								return tempGraph;
			}
		} else {
			
	/*		System.out.println("Max Flow of Graph source : " + source
					+ " to destination : " + destination + " is : " + MaxFlow);
*/
			return tempGraph;
		}

	}

	
	private static int getmaxFlow(LinkedList<Nodes> shortPath2) {
		// TODO Auto-generated method stub
		int min = 0;
		for (Nodes s : shortPath2) {
			if (min == 0)
				min = s.getCapacity();
			if (min > s.getCapacity())
				min = s.getCapacity();
		}
		return min;
	}

	private static LinkedList<Nodes> getShortPath() throws IOException {
		LinkedList<Nodes> ShortestPath = new LinkedList<Nodes>();
		Nodes dest = new Nodes("a");
		Iterator<Nodes> it = TraversalPath.iterator();
		ArrayList<Nodes> path = new ArrayList<Nodes>(TraversalPath);
		while (it.hasNext()) {
			Nodes node = (Nodes) it.next();
			if (node.getName().equals(destination)) {
				DestLevel = node.getLevel();
				dest = node;
				
				break;
			} else
				DestLevel = -1;
		}
		// ShortestPath.put(dest.getName(), dest);
		ShortestPath.addFirst(dest);
		Iterator<Nodes> it1 = TraversalPath.iterator();
		
		for (int i = path.size() - 2; i >= 0; i--) {
			Nodes n1 = (Nodes) path.get(i);
			if (n1.getLevel() == (DestLevel - 1) && Connected(n1, dest)) {
				// ShortestPath.put(n1.getName(), n1);
				ShortestPath.addFirst(n1);
				
				dest = n1;
				DestLevel = n1.getLevel();
			}
			
		}
		
		return ShortestPath;
	}

	public static void setBFS(HashMap<String, HashMap<String, Nodes>> Input) {
		visited = new boolean[Input.keySet().size()];
		for (int i = 0; i < Input.keySet().size(); i++)
			visited[i] = false;
		InputGraph = Input;
	}

	private static boolean Connected(Nodes n1, Nodes dest) {
		// TODO Auto-generated method stub
		HashMap<String, Nodes> CheckList = LevelGraph.get(n1.getName());
		Iterator<String> it = CheckList.keySet().iterator();
		while (it.hasNext()) {
			Nodes test = (Nodes) CheckList.get(it.next());
			if (test.getName().equals(dest.getName())) {
				return true;
			}
		}
		return false;
	}

	public static HashMap<String, Nodes> doBfs(Nodes source) {
		// TODO Auto-generated method stub
		// setBFS(InputGraph);
		level = 0;
		LinkedList<Nodes> Queue = new LinkedList<Nodes>();
		TraversalPath = new LinkedList<Nodes>();
		HashMap<String, Nodes> levelNodes;
		HashMap<String, Nodes> xxx = new HashMap<String, Nodes>();
		LevelGraph = new HashMap<String, HashMap<String, Nodes>>();
		source.setLevel(level);
		visited[Integer.parseInt(source.getName())] = true;
		Queue.addFirst(source);
		TraversalPath.add(source);
		xxx.put(source.getName(), source);
		level++;
		while (!Queue.isEmpty()) {

			Nodes head = Queue.remove();
			LinkedList<Nodes> ConnectedNodes = new LinkedList<Nodes>();
			HashMap<String, Nodes> ConnectedNodes1 = InputGraph.get(head
					.getName());
			for (String s : ConnectedNodes1.keySet()) {
				ConnectedNodes.add(ConnectedNodes1.get(s));
			}
			levelNodes = new HashMap<String, Nodes>();
			level = head.getLevel() + 1;
			Iterator<Nodes> it = ConnectedNodes.iterator();
			while (it.hasNext()) {
				Nodes n1 = (Nodes) it.next();
				if ((visited[Integer.parseInt(n1.getName())] != true)
						&& n1.getCapacity() > 0) {
					n1.setLevel(level);
					visited[Integer.parseInt(n1.getName())] = true;
					n1.setVisited(true);
					Queue.add(n1);
					levelNodes.put(n1.getName(), n1);
					TraversalPath.add(n1);
					xxx.put(n1.getName(), n1);

				}
			}
			LevelGraph.put(head.getName(), levelNodes);
		}
		return xxx;
	}

}