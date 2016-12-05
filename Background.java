package managementSolution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Background {

	public static void main(String[] args) throws IOException {
		UserInterface userInterface = new UserInterface();
		Time time = new Time();
		int needed = userInterface.getNoNeeded();
		if (needed == 0){
			//Need to add functionality
		}
		long startTime = time.getStartTime();

		FileRead fileRead = new FileRead();
		Background background = new Background();

		List<String> names = fileRead.readFile("Names"), Restrictions = fileRead.readFile("Restrictions"), keyRestrictions = new ArrayList<String>(), valueRestrictions = new ArrayList<String>();
		for (int i = 0; i < Restrictions.size(); i += 2){
			keyRestrictions.add(Restrictions.get(i));
			valueRestrictions.add(Restrictions.get(i + 1));
		}
		names = background.possible(needed, names, keyRestrictions, valueRestrictions);
		userInterface.answer(names);
		time.outputTotalTime(startTime);
	}

	public List<String> possible(int needed, List<String> names, List<String> keyRestrictions, List<String> valueRestrictions){
		List<String> acceptable = new ArrayList<String>();
		Map<String, Integer> banned = new HashMap<String, Integer>();
		for(int i = 0; i < needed; i ++){
			for(int j = 0; j < banned.size(); j ++){
				if (i >= names.size()){
					break;
				}
				if (banned.keySet().contains(names.get(i))){
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
			if (keyRestrictions.contains(names.get(i)) || valueRestrictions.contains(names.get(i))){
				if (banned.keySet().contains(names.get(i))){
					for (int j = 0; j < keyRestrictions.size(); j ++){
						if (keyRestrictions.get(j).equals(names.get(i))){
							banned.replace(valueRestrictions.get(j), banned.get(valueRestrictions.get(j)) + 1);
						}
						if (valueRestrictions.get(j).equals(names.get(i))){
							banned.replace(keyRestrictions.get(j), banned.get(keyRestrictions.get(j)) + 1);
						}
					}
				}
				else{
					for (int j = 0; j < keyRestrictions.size(); j ++){
						if (keyRestrictions.get(j).equals(names.get(i))){
							banned.put(valueRestrictions.get(j), 1);
						}
						if (valueRestrictions.get(j).equals(names.get(i))){
							banned.put(keyRestrictions.get(j), 1);
						}
					}
				}
			}
			acceptable.add(names.get(i));
		}
		return acceptable;
	}

	public void reduceBanned(Map<String, Integer> banned, List<String> keyRestrictions, List<String> valueRestrictions){
		int noOfBanned = banned.size();
		String tempName;
		int lowest = noOfBanned;
		for (int i = 0; i > -1; i ++){
			if (noOfBanned < lowest){
				lowest = noOfBanned;
			}
			for (int j = 0; j < banned.size(); j ++){
				tempName = (String) banned.keySet().toArray()[j];
				if (banned.get(tempName) == i){
					banned.remove(tempName);
					
				}
			}

			//Need to add functionality
		}
	}
}
