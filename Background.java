package managementSolution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Background {
	public static void main(String[] args) throws IOException {
		UserInterface userInterface = new UserInterface();
		FileRead fileRead = new FileRead();
		Background background = new Background();
		Time time = new Time();
		int needed = userInterface.getNoNeeded();
		long startTime = time.getStartTime();
		List<Integer> names = fileRead.readFile("Names");
		List<Integer> Restrictions = fileRead.readFile("Restrictions");
		Map<Integer, Integer> restrictions = new HashMap<Integer, Integer>();
		for (int i = 0; i < Restrictions.size(); i += 2){
			restrictions.put(Restrictions.get(i), Restrictions.get(i+1));
		}
		names = background.possible(needed, names, restrictions);
		if (names.size() == 0){
			System.out.println("No possible solution was reached!");
		}
		else{
			System.out.println("Acceptable solution:");
			for (int i = 0; i < names.size(); i ++){
				System.out.println(names.get(i));
			}
		}
		time.outputTotalTime(startTime);
	}

	public List<Integer> possible(int needed, List<Integer> names, Map<Integer, Integer> restrictions){
		List<Integer> acceptable = new ArrayList<Integer>();
		List<Integer> banned = new ArrayList<Integer>();
		for(int i = 0; i < needed; i ++){
			for(int j = 0; j < banned.size(); j ++){
				if (i >= names.size()){
					break;
				}
				if (banned.contains(names.get(i))){
					i ++;
					needed ++;
				}
				else{
					break;
				}
			}
			if (i >= names.size()){
				acceptable.clear();
				break;
			}
			if (restrictions.containsKey(names.get(i)) || restrictions.containsValue(names.get(i))){
				for (int j = 0; j < restrictions.size(); j ++){
					if (restrictions.keySet().toArray()[j] == names.get(i)){
						banned.add(restrictions.get(names.get(i)));
					}
					if (restrictions.values().toArray()[j] == names.get(i)){
						banned.add((Integer) restrictions.keySet().toArray()[j]);
					}
				}
			}
			acceptable.add(names.get(i));
		}
		return acceptable;
	}

}
