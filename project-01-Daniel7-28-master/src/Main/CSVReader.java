/**
 * 
 */
package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import DataStructures.ArrayList;

/**
 * @author juan3
 *
 */
public class CSVReader{
	
	private String path;

	public CSVReader(String path){
		this.path = path;
	}

	public String getPath(){	
		return this.path;
	}

	public ArrayList<ArrayList<String>> read(String filePath){

		ArrayList<String> values = new ArrayList<>();	
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		String entry;

		try {
			BufferedReader buffer = new BufferedReader(new FileReader(path+filePath));
			String[] line;
			while((entry = buffer.readLine()) != null) {
				values = new ArrayList<>();
				line = entry.split(",");
				values.add(line);
				result.add(values);
				
			}
			buffer.close();
		}
		catch(IOException f){	
			f.printStackTrace();
		}

		
		return result;
	}
}
