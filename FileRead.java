package managementSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRead {
	
	public List<Integer> readFile(String file) throws IOException{
		List<Integer> names = new ArrayList<Integer>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String ln = br.readLine();
		while (ln != null){
			names.add(Integer.parseInt(ln));
			ln = br.readLine();
		}
		br.close();
		return names;
	}
	
}
