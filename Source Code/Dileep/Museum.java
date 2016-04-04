package FordFulkerson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;



class Guard {
	Point p;
	int level, name;

	public Guard(Point p1, int l, int n) {
		p = p1;
		level = l;
		name = n;
	}

	public int getName() {
		return name + 1;
	}

	public void setName(int name) {
		this.name = name;
	}

	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void printGuard() {
		System.out.println("Guard Name: " + this.name + "\n X=" + p.x + "Y="
				+ p.y);
	}

}

class Art {
	int name;
	Point p;
	int level;

	public void printArt() {
		System.out.println("Art Name: " + this.name + "\n X=" + p.x + "Y="
				+ p.y);
	}

	public Art(Point p1, int l, int n) {
		p = p1;
		level = l;
		name = n;
	}

	public int getName() {
		return name + 1;
	}

	public void setName(int name) {
		this.name = name;
	}

	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}

class Point {
	Double x, y;

	public Point(Double x1, Double y1) {
		x = x1;
		y = y1;
	}

	public boolean equals(Point p) {
		if ((this.x == p.getX()) && (this.y == p.getY()))
			return true;
		else
			return false;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Point Calc_difference(Point p1, Point p2) {
		Double x, y;
		x = p1.x - p2.x;
		y = p1.y - p2.y;

		return new Point(x, y);

	}

	public Double Calc_distance(Point p1, Point p2) {
		Double x1, y1, x2, y2, d;
		x1 = p1.x;
		x2 = p2.x;
		y1 = p1.y;
		y2 = p2.y;
		d = Math.sqrt((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));
		return d;
	}

}

class Arc {
	Point a, b, centre;
	Double dy, dx;
	Double radius;

	public Arc(Point p1, Point p2, double dx2, double dy2) {
		// TODO Auto-generated constructor stub
		this.a = p1;
		this.b = p2;
		this.dx = dx2;
		this.dy = dy2;
	}

	public Point getA() {
		return a;
	}

	public void setA(Point a) {
		this.a = a;
	}

	public Point getB() {
		return b;
	}

	public void setB(Point b) {
		this.b = b;
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public Double getDy() {
		return dy;
	}

	public void setDy(Double dy) {
		this.dy = dy;
	}

	public Double getDx() {
		return dx;
	}

	public void setDx(Double dx) {
		this.dx = dx;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}
}

class Line {
	Point a, b;

	public void printLine(int n) {
		System.out.println("Line " + n + "\nx1= " + a.getX() + "y1= "
				+ a.getY());
		System.out.println("x2= " + b.getX() + "y2= " + b.getY() + "\n");

	}

	public Point getA() {
		return a;
	}

	public void setA(Point a) {
		this.a = a;
	}

	public Point getB() {
		return b;
	}

	public void setB(Point b) {
		this.b = b;
	}

	public Line(Point p1, Point p2) {
		a = p1;
		b = p2;
	}

	public boolean contains(Point p) {
		// TODO Auto-generated method stub
		if ((this.a.equals(p)) || (this.b.equals(p)))
			return true;
		return false;
	}

}

public class Museum {
	private static int ii = 0;
	private static int n,size;
	private static int a;
	private static int g;
	private static HashMap<Integer, Guard> guards;
	private static HashMap<Integer, Art> arts;
	private static HashMap<Integer, Line> Possible_Paths;
	private static HashMap<Integer, Line> l;
	private static HashMap<Integer, Line> Actual_Paths;
	private static int levels;
	private static int Mf;
	private static HashMap<Integer, Arc> c;
	private static double dx;
	private static double dy;
	private static int ik = 0;
	static long startTime;
	private static String files;
	private static File fout1;
	private static FileOutputStream fos1;
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		// TODO Auto-generated method stub
		guards = new HashMap<Integer, Guard>();
		arts = new HashMap<Integer, Art>();
		startTime = System.currentTimeMillis();
		files=args[2];
		BufferedReader r = new BufferedReader(new FileReader(args[1]));
		String s = "";
		int i = 0;
		Map<Integer, ArrayList<String>> m1 = new HashMap<Integer, ArrayList<String>>();
		int key = 0,total=0;
		
		while ((s = r.readLine()) != null) {
			String n;
			StringTokenizer st = new StringTokenizer(s, " ");
			ArrayList<String> l = new ArrayList<String>();
			while (st.hasMoreElements()) {

				n = st.nextToken();
				l.add(n);
			}
			m1.put(key, l);
			key++;
		}
		ArrayList<String> l1 = new ArrayList<String>();

		int k = m1.keySet().size();
		l1 = m1.get(total);
		n = Integer.parseInt(l1.get(0));
		a = Integer.parseInt(l1.get(1));
		g = Integer.parseInt(l1.get(2));
		size=g;
		l = new HashMap<Integer, Line>();
		c = new HashMap<Integer, Arc>();
		Possible_Paths = new HashMap<Integer, Line>();
		Actual_Paths = new HashMap<Integer, Line>();
		int key1 = 0;
		total++;
		Point p1, p2;
		int count;
		count = Integer.parseInt(m1.get(total).get(0));
		key = 0;
		while (n > 0) {
			int count2 = count;
			total = total + count;
			Double x1, y1, x2, y2;
			while (count > 0) {
				count--;
				if (m1.get(total - count).contains("s")) {
					x1 = Double.parseDouble(m1.get(total - count).get(0));
					y1 = Double.parseDouble(m1.get(total - count).get(1));
					p1 = new Point(x1, y1);
					if (count == 0) {
						x2 = Double.parseDouble(m1.get(total - count2 + 1).get(
								0));
						y2 = Double.parseDouble(m1.get(total - count2 + 1).get(
								1));
					} else {
						x2 = Double.parseDouble(m1.get(total - count + 1)
								.get(0));
						y2 = Double.parseDouble(m1.get(total - count + 1)
								.get(1));
					}
					p2 = new Point(x2, y2);
					l.put(key++, new Line(p1, p2));
				}

				else if (m1.get(total - count).contains("c")) {
					x1 = Double.parseDouble(m1.get(total - count).get(0));
					y1 = Double.parseDouble(m1.get(total - count).get(1));
					dx = Double.parseDouble(m1.get(total - count).get(3));
					dy = Double.parseDouble(m1.get(total - count).get(4));
					p1 = new Point(x1, y1);
					if (count == 0) {
						x2 = Double.parseDouble(m1.get(total - count2 + 1).get(
								0));
						y2 = Double.parseDouble(m1.get(total - count2 + 1).get(
								1));
					} else {
						x2 = Double.parseDouble(m1.get(total - count + 1)
								.get(0));
						y2 = Double.parseDouble(m1.get(total - count + 1)
								.get(1));
					}
					p2 = new Point(x2, y2);
					c.put(key1++, new Arc(p1, p2, dx, dy));

				}
			}
			n--;
			if (n != 0) {
				total++;
				count = Integer.parseInt(m1.get(total).get(0));
			}
		}

		int q = a;
		key = 0;
		total = total + q;
		while (q > 0) {
			q--;
			Point p;
			Double x, y;
			int lvl = 0;
			x = Double.parseDouble(m1.get(total - q).get(0));
			y = Double.parseDouble(m1.get(total - q).get(1));
			lvl = Integer.parseInt(m1.get(total - q).get(2));
			arts.put(key, new Art(new Point(x, y), lvl, key++));

		}
		q = g;
		total = total + q;
		key = 0;
		while (q > 0) {
			q--;
			Point p;
			Double x, y;
			int lvl = 0;
			x = Double.parseDouble(m1.get(total - q).get(0));
			y = Double.parseDouble(m1.get(total - q).get(1));
			lvl = Integer.parseInt(m1.get(total - q).get(2));
			guards.put(key, new Guard(new Point(x, y), lvl, key++));

		}

		key = 0;

		for (Integer i1 : guards.keySet()) {
			for (Integer j1 : arts.keySet()) {

				Possible_Paths.put(key++, new Line(guards.get(i1).getP(), arts
						.get(j1).getP()));
			}
		}
		int flag = 0;
		key = 0;
		for (Integer integer : Possible_Paths.keySet()) {
			flag = 0;
			for (Integer integer2 : l.keySet()) {
				if (Check_IntersectLine(Possible_Paths.get(integer),
						l.get(integer2))) {

					flag = 1;
					break;
				}
			}
			for (Integer integer3 : c.keySet()) {
				if (Check_IntersectArc(Possible_Paths.get(integer),
						c.get(integer3))) {
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				Actual_Paths.put(key++, Possible_Paths.get(integer));
			}
		}

		try {
			Build_adj_graph();
			long endtime=System.currentTimeMillis();
			System.out.println("Time of Execution of Museum problem is"+(endtime-startTime+"milli secs"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println("Build_adj_graph exception");

		}
	}

	private static boolean Check_IntersectLine(Line gtoa, Line line) {
		// TODO Auto-generated method stub
		Double cx, cy, dx, dy, x1, y1, x2, y2;
		x1 = gtoa.getA().getX();
		y1 = gtoa.getA().getY();
		x2 = gtoa.getB().getX();
		y2 = gtoa.getB().getY();
		cx = line.getA().getX();
		cy = line.getA().getY();
		dx = line.getB().getX();
		dy = line.getB().getY();

		if (counter_clock_wise(x1, y1, cx, cy, dx, dy) == counter_clock_wise(
				x2, y2, cx, cy, dx, dy))
			return false;
		else if (counter_clock_wise(x1, y1, x2, y2, cx, cy) == counter_clock_wise(
				x1, y1, x2, y2, dx, dy))
			return false;
		else
			return true;

	}

	public static boolean counter_clock_wise(double x1, double y1, double x2,
			double y2, double x3, double y3) {
		return (y3 - y1) * (x2 - x1) > (y2 - y1) * (x3 - x1);
	}

	public static boolean arc_pt_test(double x1, double y1, double x2,
			double y2, double x3, double y3, double x4, double y4) {
		if (counter_clock_wise(x1, y1, x3, y3, x4, y4) == counter_clock_wise(
				x2, y2, x3, y3, x4, y4))
			return false;
		else if (counter_clock_wise(x1, y1, x2, y2, x3, y3) == counter_clock_wise(
				x1, y1, x2, y2, x4, y4))
			return false;
		else
			return true;

	}

	public static boolean test_dist(double x1, double y1, double x2,
			double y2, double x3, double y3) {

		if ((Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3)) + Math
				.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3))) == Math
				.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)))
			return true;
		return false;
	}

	private static boolean Check_IntersectArc(Line line, Arc arc) {

		Double x3 = arc.getA().getX();
		Double y3 = arc.getA().getY();
		Double x4 = arc.getB().getX();
		Double y4 = arc.getB().getY();

		double xc = 0;
		double yc = 0;

		double dx = arc.getDx();// Integer.parseInt(l4.get(3));
		double dy = arc.getDy();// Integer.parseInt(l4.get(4));

		double sl = dy / dx;
		if (dx == 0) {
			yc = y3;
			xc = (x4 * x4 - x3 * x3 + y3 * y3 + y4 * y4 - 2 * y3 * y4)
					/ (2 * (x4 - x3));
			//System.out.println("centre is" + xc + " " + yc);

		} else {
			yc = ((x3 + sl * y3) - (x3 * x3 - x4 * x4 + y3 * y3 - y4 * y4)
					/ (2 * (x3 - x4)))
					/ (sl - ((y3 - y4) / (x3 - x4)));
			xc = x3 + sl * y3 - sl * yc;
			//System.out.println("centre is" + xc + " " + yc);
		}

		double r = Math.sqrt((xc - x3) * (xc - x3) + (yc - y3) * (yc - y3));
		boolean flag1 = false, flag2 = false;
		Double xg, yg, xa, ya, m2;
		xg = line.getA().getX();
		yg = line.getA().getY();
		xa = line.getB().getX();
		ya = line.getB().getY();//ERROR DX
		boolean checkresult = false;
		double distance, ix1, ix2, iy1, iy2, c1, c2, a, b;
		double x1 = x3 + arc.getDx();
		double y1 = y3 + arc.getDy();
		if ((xg - xa) == 0) {
			distance = xc + xg;
			if (distance < r) {

				ix1 = xa;
				ix2 = xa;
				c1 = (r * r) - (xc * xc) - (yc * yc) - (xa * xa)
						+ (2 * xc * xa);
				iy1 = yc + Math.sqrt((yc * yc) + c1);
				iy2 = yc - Math.sqrt((yc * yc) + c1);

				if (arc_pt_test(x1, y1, ix1, iy1, x3, y3, x4, y4) == false) {
					flag1 = test_dist(xg, yg, xa, ya, ix1, iy1);
				}
				if (arc_pt_test(x1, y1, ix2, iy2, x3, y3, x4, y4) == false) {
					flag2 = test_dist(xg, yg, xa, ya, ix2, iy2);
				}
				if (flag1 || flag2) {
					checkresult = true;
				}

			}

		} else if ((yg - ya) == 0) {
			distance = yc + yg;
			iy1 = ya;
			iy2 = ya;
			c1 = (r * r) - (xc * xc) - (yc * yc) - (ya * ya) + (2 * yc * ya);
			ix1 = xc + Math.sqrt((xc * xc) + c1);
			ix2 = xc - Math.sqrt((xc * xc) + c1);
			if (arc_pt_test(x1, y1, ix1, iy1, x3, y3, x4, y4) == false) {
				flag1 = test_dist(xg, yg, xa, ya, ix1, iy1);
			}
			if (arc_pt_test(x1, y1, ix2, iy2, x3, y3, x4, y4) == false) {
				flag2 = test_dist(xg, yg, xa, ya, ix2, iy2);
			}
			if (flag1 || flag2) {
				checkresult = true;
			}

		} else {
			m2 = (yg - ya) / (xg - xa);
			distance = ((m2 * xc) - yc + (ya - xa)) / Math.sqrt((m2 * m2) + 1);
			c1 = ya - xa;
			c2 = (r * r) - (c1 * c1) + (2 * yc * c1) - (xc * xc) - (yc * yc);
			a = 1 + (m2 * m2);
			b = (2 * m2 * c1) - (2 * xc) - (2 * yc * m2);
			ix1 = (-b + Math.sqrt((b * b + (4 * a * c2))) / (2 * a));
			ix2 = (-b - Math.sqrt((b * b + (4 * a * c2))) / (2 * a));
			iy1 = m2 * ix1 + c1;
			iy2 = m2 * ix2 + c1;
			if (arc_pt_test(x1, y1, ix1, iy1, x3, y3, x4, y4) == false) {
				flag1 = test_dist(xg, yg, xa, ya, ix1, iy1);
			}
			if (arc_pt_test(x1, y1, ix2, iy2, x3, y3, x4, y4) == false) {
				flag2 = test_dist(xg, yg, xa, ya, ix2, iy2);
			}
			if (flag1 || flag2) {
				checkresult = true;
			}

		}

		return (checkresult);
	}

	private static void Build_adj_graph() throws Exception {
		String s[] = new String[a + g + 2];
		String s1 = "";
		int src = 0, des = 0;
		for (Integer integer : guards.keySet()) {
			if (s1.equals(""))
				s1 = s1 + guards.get(integer).getName() + " "
						+ guards.get(integer).getLevel();
			else
				s1 = s1 + " " + guards.get(integer).getName() + " "
						+ guards.get(integer).getLevel();

		}
		s[0] = s1;
		s1 = "";
		int key = 1;
		for (Integer integer2 : guards.keySet()) {

			for (Integer integer : Actual_Paths.keySet()) {
				Line l = Actual_Paths.get(integer);
				if (l.contains(guards.get(integer2).getP())) {
					for (Integer integer3 : arts.keySet()) {
						if (l.contains(arts.get(integer3).getP())) {
							if (s1.equals(""))
								s1 = s1 + (arts.get(integer3).getName() + g)
										+ " " + 1;
							else
								s1 = s1 + " "
										+ (arts.get(integer3).getName() + g)
										+ " " + 1;
						}
					}
				}
			}
			if (s1.equals(""))
				s1 = " ";
			//System.out.println("Guard----->Art :" + s1);
			s[key++] = s1;
			s1 = "";
		}

		s1 = "";
		levels = 0;
		Mf = 0;
		for (Integer integer4 : arts.keySet()) {
			s1 = s1 + (g + a + 1) + " " + arts.get(integer4).getLevel();
			levels = levels + arts.get(integer4).getLevel();

			s[key++] = s1;
			s1 = "";
		}
		s[key] = " ";
		des = key;
		File fout = new File("sam.txt");
		
		 fout1 = new File(files);
		 if(!fout1.exists())
		 {
			 fout1.createNewFile();
		 }
		 if(!fout.exists())
		 {
			 fout.createNewFile();
		 }
		FileOutputStream fos = new FileOutputStream(fout);
		fos1 = new FileOutputStream(fout);
		FileWriter fw1 = new FileWriter(fout1.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		BufferedWriter bw1 = new BufferedWriter(fw1);
		for (int i = 0; i < s.length; i++) {
			bw.write(s[i]);
			if (i != (s.length - 1))
				bw.newLine();
		}
		bw.close();
		//FordFulkerson f = new FordFulkerson();
		Mf = FordFulkerson.getMax("sam.txt",Integer.toString(src), Integer.toString(des),g,"abc.txt");
		System.out.println("Max Flow :"+Mf);
		String op="";
		if(Mf >= levels)
		{
		op="true";
		
			System.out.println("True");
		}
		else
		{
			System.out.println("False");
			op="false";
		}
		bw1.write(op);
		
	}
}
