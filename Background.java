package managementSolution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Background {

	public static void main(String[] args) throws IOException {
		Map<String, Integer> banned = new HashMap<String, Integer>();
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
		banned = background.makeBanned(needed, names, keyRestrictions, valueRestrictions, banned);
		background.reduceBanned(banned, keyRestrictions, valueRestrictions);
		names = background.possible(needed, names, keyRestrictions, valueRestrictions, banned);
		userInterface.answer(names);
		time.outputTotalTime(startTime);
	}

	public Map<String, Integer> makeBanned(int needed, List<String> names, List<String> keyRestrictions, List<String> valueRestrictions, Map<String, Integer> banned){
		for (int i = 0; i < needed; i ++){
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
		}
		return banned;
	}

	public List<String> possible(int needed, List<String> names, List<String> keyRestrictions, List<String> valueRestrictions, Map<String, Integer> banned){
		List<String> acceptable = new ArrayList<String>();
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

			acceptable.add(names.get(i));
		}
		return acceptable;
	}

	public Map<String, Integer> reduceBanned(Map<String, Integer> tempBanned, List<String> keyRestrictions, List<String> valueRestrictions){
		String tempName;
		int lowest = tempBanned.size();
		int bannedLowest = getLowestValue(tempBanned);
		Map<String, Integer> banned = tempBanned;
		for (int i = 0; i < keyRestrictions.size(); i ++){
			for (int j = 0; j < tempBanned.size(); j ++){
				tempName = (String) tempBanned.keySet().toArray()[j];
				if (tempBanned.values().toArray()[j].equals(bannedLowest)){ //Broken for some reason (Null pointer exception)
					reduceBannedMethod(tempName, tempBanned, keyRestrictions, valueRestrictions, lowest, bannedLowest, banned);
				}
			}

			//Need to add functionality
		}
		return banned;
	}
	
	public void reduceBannedMethod(String tempName, Map<String, Integer> tempBanned, List<String> keyRestrictions, List<String> valueRestrictions, int lowest, int bannedLowest, Map<String, Integer> banned){
		tempBanned.remove(tempName);
		for(int k = 0; k < keyRestrictions.size(); k ++){
			if (valueRestrictions.get(k).equals(tempName)){
				if (tempBanned.containsKey(keyRestrictions.get(k))){
					tempBanned.replace(keyRestrictions.get(k), tempBanned.get(keyRestrictions.get(k) + 1));
				}
				else{
					tempBanned.put(keyRestrictions.get(k), 1);
				}
			}
			else if (keyRestrictions.get(k).equals(tempName)){
				if (tempBanned.containsKey(valueRestrictions.get(k))){
					tempBanned.replace(valueRestrictions.get(k), tempBanned.get(valueRestrictions.get(k) + 1));
				}
				else{
					tempBanned.put(valueRestrictions.get(k), 1);
				}
			}
			System.out.println(tempBanned.keySet());
			if (tempBanned.size() <= lowest){
				lowest = tempBanned.size();
				banned = tempBanned;
			}
			else{
				tempBanned = banned;
			}
		}
	}
	
	public int getLowestValue(Map<String, Integer> basis){
		int lowest = (Integer) basis.values().toArray()[0];
		for (int i = 0; i < basis.size(); i ++){
			if ((Integer) basis.values().toArray()[i] < lowest){
				lowest = (Integer) basis.values().toArray()[i];
			}
		}
		return lowest;
	}
}
