package managementSolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRead {
	
	public List<String> readFile(String file) throws IOException{
		List<String> names = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String ln = br.readLine();
		while (ln != null){
			names.add(ln);
			ln = br.readLine();
		}
		br.close();
		return names;
	}
	
}
