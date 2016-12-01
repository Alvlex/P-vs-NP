package managementSolution;

import java.util.Scanner;

public class UserInterface {
	Scanner x = new Scanner(System.in);
	public int getNoNeeded(){
		System.out.println("How many people are needed?");
		return x.nextInt();
	}
}
