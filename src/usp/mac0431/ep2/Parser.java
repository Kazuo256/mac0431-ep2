/**
 * 
 */
package usp.mac0431.ep2;

import java.util.StringTokenizer;

/**
 * 
 *
 */
public class Parser {

	Parser () {
		
	}
	
	Damage parseDamage (String line) {
		StringTokenizer tokens = new StringTokenizer(line, ",");
		String date = tokens.nextToken(" ");
		
		return date != null ? null : null;
	}
	
}
