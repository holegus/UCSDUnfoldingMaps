import java.util.regex.Pattern;

import processing.core.PApplet;

public class RegExSplit extends PApplet {
	
	public void setup() {
	
		String[] rows = loadStrings("LifeExpectancyWorldBankModule31.csv");
		
		//"(,SP.)|(IN,)|(,)|(,)"
		//(^th,)(^,\\s)(,)
		
		String test = ",(?!\\s)";
		//Pattern regex = Pattern.compile(test);
		
		for (String row : rows) {
			String[] columns = Pattern.compile(test).split(row);
						
			//}
			
			for (String item : columns) {
			
				System.out.print(item + " probel ");
				
			}
			System.out.print(columns.length + "\n");
		}
	}
}