package managementSolution;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
	Scanner x = new Scanner(System.in);
	
	public int getNoNeeded(){
		System.out.println("How many people are needed?\nType '0' to get the maximum group that you can get");
		return x.nextInt();
	}
	
	public void answer(List<String> names){
		if (names.size() == 0){
			System.out.println("No possible solution was reached!");
		}
		else{
			System.out.println("Acceptable solution:");
			for (int i = 0; i < names.size(); i ++){
				System.out.println(names.get(i));
			}
		}
	}
}
