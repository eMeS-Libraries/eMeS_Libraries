package lv.emes.libraries.examples;

import static lv.emes.libraries.utilities.MS_CodingUtils.inRange;
import static lv.emes.libraries.utilities.MS_CodingUtils.randomNumber;

public class MSToolsExample {

	public static void main(String[] args) {
		String s = Character.toString((char)9835);
		System.out.println(s);		
		System.out.println(randomNumber(0, 3));	
		System.out.println(inRange(2, 1, 1));			
	}
}
