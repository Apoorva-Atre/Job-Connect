package miniproject;

import java.util.*;

//cd=new candidate
//cp=new company

class company {
	int id;
	int eqp;
	String name;
	String role;// role offered
	int posn;// number of candidates required
	int posncount = 0;// counter for positions remaining initialized to 0
	double sal;
	String eduq;// educational qualification
	String email;
	String loc;// location of work
	ArrayList<candidate> l = new ArrayList<candidate>();

	public company(String name, String role, int posn, double sal, String eduq, int eqp, String email, String loc,
			int id) {
		this.name = name;
		this.role = role;
		this.posn = posn;
		this.sal = sal;
		this.eduq = eduq;
		this.eqp = eqp;
		this.email = email;
		this.loc = loc;
		this.id = id;
	}
}

class candidate {
	int eqp;
	int id;
	String name;
	String eduq;
	String email;
	String prefferedloc;// Preferred location of work
	ArrayList<company> l = new ArrayList<company>();

	public candidate(int id, String name, String eduq, int eqp, String email, String prefferedloc) {
		this.id = id;
		this.name = name;
		this.eduq = eduq;
		this.eqp = eqp;
		this.email = email;
		this.prefferedloc = prefferedloc;
	}
}

class Node {

	company comp;
	candidate cand;
	double priority;
	Node next;

	public Node(company comp, candidate cand, double priority) {
		this.comp = comp;
		this.cand = cand;
		this.priority = priority;
		next = null;
	}
}

class priorityq {
	Node front;

	public priorityq() {
		front = null;
	}

	public void enqueue(company comp, candidate cand, double priority, priorityq pq) {
		Node newNode = new Node(comp, cand, priority);

		if (pq.front == null || priority > pq.front.priority) {
			newNode.next = front;
			pq.front = newNode;
		} else {
			Node current = pq.front;
			while (current.next != null && current.next.priority > priority) {
				current = current.next;
			}
			newNode.next = current.next;
			current.next = newNode;
		}
	}

	public void dequeue(int f, LinkedList<candidate> arr2, LinkedList<company> arr1) {

		if (arr1 == null) {
			while (!isEmpty()) {
				arr2.get(f).l.add(front.comp);
				front = front.next;
			}
		} else if (arr2 == null) {
			while (!isEmpty()) {
				arr1.get(f).l.add(front.cand);
				front = front.next;
			}
		}
	}

	public boolean isEmpty() {
		return front == null;
	}

	public void display() {
		Node current = front;
		while (current != null) {
			System.out.println(" Company name: " + current.comp.name + "\n Salary: " + current.priority);
			current = current.next;
		}
	}

	public company peek() {
		if (isEmpty()) {
			throw new IllegalStateException("Priority queue is empty");
		}
		return front.comp;
	}
}

class total {
	Scanner sc = new Scanner(System.in);
	priorityq pq = new priorityq();
	int id1 = 300; // id for companies
	int id2 = 100; // id for candidates
	public LinkedList<company> arr1 = new LinkedList<company>();

	int counter1 = 0; // variable to track arr1

	public LinkedList<candidate> arr2 = new LinkedList<candidate>();

	int counter2 = 0;// variable to track arr2

	void addcomp() {

		String name = " ";
		while (true) {
			sc.nextLine();
			System.out.println("Enter company name:");
			name = sc.nextLine();

			if (name.matches("")) {
				System.out.println("Enter valid name!");
			}

			else {
				break;
			}
		}

		int id = ++id1;
		String role;

		while (true) {

			System.out.println("Enter role to be offered:");
			role = sc.nextLine();

			if (role.matches("")) {
				System.out.println("Enter valid role!");
			}

			else if (!role.matches("-?\\d+")) {
				break;
			}

			else {
				System.out.println("Enter valid role!");
			}
		}

		int posn = 0;
		String g;

		while (true) {

			System.out.println("Enter number of positions :");
			g = sc.nextLine();

			if (g.matches("-?\\d+")) {
				posn = Integer.parseInt(g);
				break;
			}

			else {
				System.out.println("Enter valid input!");
			}
		}

		double sal = 0;

		while (true) {

			System.out.println("Enter salary to be offered:");
			g = sc.nextLine();
			if (g.matches("-?\\d+")) {
				sal = Integer.parseInt(g);
				break;
			}

			else {
				System.out.println("Enter valid input!");
			}
		}
		String eduq = null;
		System.out.println("Enter educational qualification required:");
		int t = 1;
		int eqp = 0;
		String j = null;
		int op = 0;

		do {
			while (true) {
				System.out.println(
						"\n 1.Below 10th \n 2.10th pass \n 3.12th pass \n 4.Undergraduate \n 5.Postgraduate \n 6.Phd");
				System.out.println(" 7.Other");

				j = sc.nextLine();

				if (j.matches("-?\\d+")) {
					op = Integer.parseInt(j);
					break;
				} else {
					System.out.println("Enter valid input!");
				}
			}

			switch (op) {
			case 1: {
				eduq = "Below 10th";
				eqp = 1;
				t = 0;
				break;
			}
			case 2: {
				eduq = "10th pass";
				eqp = 2;
				t = 0;
				break;
			}
			case 3: {
				eduq = "12th pass";
				eqp = 3;
				t = 0;
				break;
			}
			case 4: {
				eduq = "Undergraduate";
				eqp = 4;
				t = 0;
				break;
			}
			case 5: {
				eduq = "Postgraduate";
				eqp = 5;
				t = 0;
				break;
			}
			case 6: {
				eduq = "Phd";
				eqp = 6;
				t = 0;
				break;
			}
			case 7: {
				System.out.println("Enter qualification: ");
				eduq = sc.nextLine();
				t = 0;
				break;
			}
			default: {
				System.out.println("Enter correct option!");
				break;
			}
			}
		} while (t == 1);
		String email;
		while (true) {
			System.out.println("Enter email id:");
			email = sc.next();
			if (email.contains(".com") || email.contains(".in")) {
				if (email.compareTo(".in") == 0 || email.compareTo(".com") == 0) {
					System.out.println("Enter a valid email id!");
				} else {
					break;
				}
			} else {
				System.out.println("Enter correct email id! Try again");
			}
		}
		String loc = " ";
		while (true) {
			System.out.println("work : online/offline?");
			String choice = sc.next();
			if (choice.matches("-?\\d+")) {
				System.out.println("Enter valid input!");
				continue;
			}

			if (choice.equalsIgnoreCase("online") || choice.equalsIgnoreCase("offline")) {
				if (choice.equalsIgnoreCase("offline")) {
					while (true) {
						System.out.println("Enter location:");
						loc = sc.next();
						if (loc.matches("-?\\d+")) {
							System.out.println("Enter valid input!");
						}

						else {
							break;
						}

					}
				}

				else {
					loc = "online";
				}
				break;
			} else {
				System.out.println("Enter correct option!");
			}
		}
		company cp = new company(name, role, posn, sal, eduq, eqp, email, loc, id);
		arr1.add(cp);
		counter1 += 1;
		System.out.println("\n");

		System.out.println("Thank you for registration!");
		System.out.println("Your details are:");
		System.out.println("Company name:                            " + name);
		System.out.println("Comapny registration id:                 " + id);
		System.out.println("Company email:                           " + email);
		System.out.println("\n");

//compare_candtocomp();
//compare_comptocand();
	}

	void addcand() {
		int id = ++id2;
		String eduq = null;
		String name = " ";
		while (true) {
			System.out.println("Enter candidate name:");
			name = sc.next();
			sc.nextLine();
			if (name.matches("-?\\d+")) {
				System.out.println("Enter valid input!");
			} else {
				break;
			}
		}
		System.out.print("Enter educational qualification :");

		int t = 1;
		int eqp = 0;
		String j = null;
		int op = 0;
		do {
			while (true) {
				System.out.println(
						"\n 1.Below 10th \n 2.10th pass \n 3.12th pass \n 4.Undergraduate \n 5.Postgraduate \n 6.Phd");
				System.out.println(" 7.Other");
				j = sc.next();
				if (j.matches("-?\\d+")) {
					op = Integer.parseInt(j);
					break;
				}

				else {
					System.out.println("Enter valid input!");
				}
			}
			switch (op) {
			case 1: {
				eduq = "Below 10th";
				eqp = 1;
				t = 0;

				break;

			}
			case 2: {
				eduq = "10th pass";
				eqp = 2;
				t = 0;
				break;

			}
			case 3: {
				eduq = "12th pass";
				eqp = 3;
				t = 0;
				break;
			}
			case 4: {
				eduq = "Undergraduate";
				eqp = 4;
				t = 0;
				break;
			}
			case 5: {
				eduq = "Postgraduate";
				eqp = 5;
				t = 0;
				break;
			}
			case 6: {
				eduq = "Phd";
				eqp = 6;
				t = 0;
				break;
			}
			case 7: {
				System.out.println("Enter qualification: ");
				eduq = sc.next();
				t = 0;
				break;
			}
			default: {
				System.out.println("Enter correct option!");
				break;
			}
			}
		} while (t == 1);
		String email;
		while (true) {
			System.out.println("Enter email id:");
			email = sc.next();
			if (email.contains(".com") || email.contains(".in")) {
				if (email.compareTo(".in") == 0 || email.compareTo(".com") == 0) {
					System.out.println("Enter a valid mail id!");
				}

				else {
					break;
				}
			} else {
				System.out.println("Enter correct email id! Try again");
			}
		}
		String prefferedloc = " ";
		while (true) {
			System.out.println("work : online/offline?");

			String choice = sc.next();
			if (choice.matches("-?\\d+")) {
				System.out.println("Enter valid input!");
				continue;
			}
			if (choice.equalsIgnoreCase("online") || choice.equalsIgnoreCase("offline")) {
				if (choice.equalsIgnoreCase("offline")) {
					while (true) {
						System.out.println("Enter location:");
						prefferedloc = sc.next();
						if (prefferedloc.matches("-?\\d+")) {

							System.out.println("Enter valid input!");

						}

						else {
							break;
						}
					}
				}

				else {

					prefferedloc = "online";
				}
				break;

			} else {
				System.out.println("Enter correct option!");
			}
		}
		candidate cd = new candidate(id, name, eduq, eqp, email, prefferedloc);
		arr2.add(cd);
		counter2 += 1;
		System.out.println("\n");
		System.out.println("Thank you for registration!");
		System.out.println("Your details are:");
		System.out.println("Candidate name:                    " + name);
		System.out.println("Candidate registration id:         " + id);
		System.out.println("Candidate email:                   " + email);
		System.out.println("\n");

//compare_candtocomp();
//compare_comptocand();
	}

	void compare_candtocomp() {
		// list of a candidate(consisting of companies)
//arr1=company
//arr2=candidate

		int t = 0;
		for (int i = 0; i < arr2.size(); i++) {
			if (arr2.get(i) != null) {
				for (int j = 0; j < arr1.size(); j++) { // till all companies are checked for that candidate
					if (arr1.get(j) != null) {
						if (arr2.get(i).eduq == arr1.get(j).eduq || arr2.get(i).eqp >= arr1.get(j).eqp) {// 1st-educational
																											// qualification
							if (arr2.get(i).prefferedloc.compareTo(arr1.get(j).loc) == 0) {// 2nd-location preference

//adding company to priority queue
								pq.enqueue(arr1.get(j), null, arr1.get(j).sal, pq);
								t = 1;
							}
						}
					}

					else {
						break;
					}
				}

//first empty the list
				arr2.get(i).l.clear();

//dequeue priority queue into list of that candidate (arr2[i].l)
				if (pq.isEmpty()) {
					if (t == 0) {
						System.out.println("No appropriate compoanies found!");
						t = 1;
					}
				}

				else {
					pq.dequeue(i, arr2, null);
				}

//now the priority queue is empty and ready for next candidate
			} else {
				break;
			}
		}
	}

	void compare_comptocand() {

		// list of a company(consisting of candidates)
		// arr1=company
		// arr2=candidate
		int t = 0;
		for (int j = 0; j < arr1.size(); j++) {// till all companies are checked for that candidate
			if (arr1.get(j) != null) {
				for (int i = 0; i < arr2.size(); i++) {
					if (arr2.get(i) != null) {
						if (arr2.get(i).eduq == arr1.get(j).eduq || arr2.get(i).eqp >= arr1.get(j).eqp) {// 1st-educational
																											// qualification
							if (arr2.get(i).prefferedloc.compareTo(arr1.get(j).loc) == 0) {// 2nd-location preference

								// adding company to priority queue
								pq.enqueue(null, arr2.get(i), arr2.get(i).eqp, pq);
								t = 1;

							}

						}
					} else {
						break;
					}
				}

				// first empty the list
				arr1.get(j).l.clear();

				// dequeue priority queue into list of that candidate (arr2[i].l)
				if (pq.isEmpty()) {
					if (t == 0) {
						System.out.println("No appropriate candidates found!");
						t = 1;
					}
				} else {
					pq.dequeue(j, null, arr1);
				}
				// now the priority queue is empty and ready for next candidate
			} else {
				break;
			}
		}

	}

	void jobstocand() {
		if (arr2.isEmpty()) {
			// arr2 empty
			System.out.println("No candidates !");
		}

		else if (arr2.isEmpty() == false) {
			int g;
			while (true) {
				System.out.println("Enter registration id :");
				g = sc.nextInt();
				if (g <= 100 || g > id2) {
					System.out.println("Candidate does not exist! Enter valid input!");
					break;
				}

				else {

					break;

				}
			}

			for (int i = 0; i < arr2.size(); i++) {
				if (arr2.get(i) != null) {
					if (arr2.get(i).id == g) {
						if (arr2.get(i).l.size() == 0) {
							System.out.println("No suggested companies for now!");
							System.out.println("\n");
							break;
						}

						for (int j = 0; j < arr2.get(i).l.size(); j++) {
							System.out.println("\n");
							System.out
									.println("**********************************************************************");
							System.out.println("Company name:                          " + arr2.get(i).l.get(j).name);
							System.out.println("Role offered:                          " + arr2.get(i).l.get(j).role);
							System.out.println("Salary offered:                        " + arr2.get(i).l.get(j).sal);
							System.out.println("Location:                              " + arr2.get(i).l.get(j).loc);
							System.out.println("Email:                                 " + arr2.get(i).l.get(j).email);
							System.out
									.println("**********************************************************************");
						}
					}
				}

			}
		}
	}

	void candtocomplist() {
		int o = 1;
		if (arr1.isEmpty()) {
			// arr1 empty
			System.out.println("No companies !");
		} else if (arr1.isEmpty() == false) {
			int g;
			while (true) {
				System.out.println("Enter registration id :");
				g = sc.nextInt();
				if (g <= 300 || g > id1) {
					System.out.println("Candidate does not exist! Enter valid input!");
					break;
				} else {
					break;
				}
			}

			for (int i = 0; i < arr1.size(); i++) {
				if (arr1.get(i) != null) {
					if (arr1.get(i).id == g) {
						if (arr1.get(i).l.size() == 0) {
							System.out.println("No suggested candidates for now!");

							System.out.println("\n");
							break;

						}

						for (int j = 0; j < arr1.get(i).l.size(); j++) {
							System.out.println("\n");
							System.out
									.println("**********************************************************************");
							System.out.println("Candidate name:                        " + arr1.get(i).l.get(j).name);
							System.out.println("Educational qualification:             " + arr1.get(i).l.get(j).eduq);
							System.out.println(
									"Preffered Location:                    " + arr1.get(i).l.get(j).prefferedloc);
							System.out.println("Email:                                 " + arr1.get(i).l.get(j).email);
							System.out
									.println("**********************************************************************");
						}
					}
				}
			}
		}
	}

	void delcomp() {

		int j = 1;
		System.out.println("Enter registration id of company to be deleted:");
		String s = sc.next();
		if (s.matches("-?\\d+")) {
			int d = Integer.parseInt(s);
			for (int i = 0; i < arr1.size(); i++) {
				if (arr1.get(i).id == d) {
					arr1.remove(i);
					System.out.println("Company deleted successfully!");
					j = 0;
				}
			}

			if (j == 1) {
				System.out.println("No such company found!");
			}
		} else {
			System.out.println("Enter valid input!");
		}

	}

	void delcand() {

		int j = 1;
		System.out.println("Enter registration id of candidate to be deleted:");
		String s = sc.next();
		if (s.matches("-?\\d+")) {
			int d = Integer.parseInt(s);
			for (int i = 0; i < arr2.size(); i++) {
				if (arr2.get(i).id == d) {
					arr2.remove(i);
					System.out.println("Candidate deleted successfully!");
					j = 0;

				}

			}

			if (j == 1) {
				System.out.println("No such candidate found!");
			}
		}

		else {

			System.out.println("Enter valid input!");

		}
	}

}

public class JobPortal {

	public static void main(String[] args) {
		total ob = new total();
		Scanner sc = new Scanner(System.in);
		int z = 1;
		int op = 0;
		do {
			while (true) {
				System.out.println("\n");
				System.out.println("Select an option");

				System.out.println(" 1.Company Side \n 2.Candidate Side \n 3.Exit");
				String q = "";
				q = sc.next();
				if (q.matches("-?\\d+")) {
					op = Integer.parseInt(q);
					break;
				} else {
					System.out.println("Enter valid option format!");
				}

			}

			switch (op) {

			case 1: {

				int h = 0;
				while (true) {
					System.out.println(" Enter option:");
					System.out.println(
							" 1.Add company \n 2.View suggested list of candidates to a company \n 3.Delete a company");
					String g = "";
					g = sc.next();

					if (g.matches("-?\\d+")) {
						h = Integer.parseInt(g);
						break;
					}

					else {
						System.out.println("Enter valid option format!");
					}

				}

				switch (h) {

				case 1: {
					ob.addcomp();
					break;
				}
				case 2: {
					ob.compare_candtocomp();
					ob.compare_comptocand();
					ob.candtocomplist();
					break;
				}
				case 3: {
					ob.delcomp();
					break;

				}

				default: {
					System.out.println("Enter correct option");
				}
				}

				break;
			}

			case 2: {

				int h = 0;

				while (true) {
					System.out.println("Enter option:");
					System.out
							.println(" 1.Add candidate \n 2.View jobs offered to a candidate \n 3.Delete a candidate");
					String g = "";
					g = sc.next();
					if (g.matches("-?\\d+")) {
						h = Integer.parseInt(g);

						break;

					}

					else {

						System.out.println("Enter valid option format!");

					}

				}

				switch (h) {

				case 1: {
					ob.addcand();

					break;

				}
				case 2: {
					ob.compare_candtocomp();
					ob.compare_comptocand();
					ob.jobstocand();

					break;
				}
				case 3: {

					ob.delcand();

					break;

				}

				default: {

					System.out.println("Enter correct option");

				}

				}

				break;
			}
			case 3: {
				System.out.println();
				System.out.println("Thank you.Visit again!");
				System.exit(0);
				break;
			}
			default: {
				System.out.println("Enter valid option! Try again");
				break;
			}
			}
		} while (z == 1);
	}
}

