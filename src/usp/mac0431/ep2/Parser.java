/**
 * 
 */
package usp.mac0431.ep2;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * 
 *
 */
public class Parser {

	Parser() {

	}

	public Damage parseDamage(String line) {
		StringTokenizer tokens = new StringTokenizer(line, ",");
		long time = parseDate(tokens.nextToken(" "))
				+ parseTime(tokens.nextToken(" "));
		
		return new Damage("", time, 0l);
	}

	private long parseDate(String dateText) {
		StringTokenizer dateTokens = new StringTokenizer(dateText, "/");
		int month = Integer.parseInt(dateTokens.nextToken());
		int day = Integer.parseInt(dateTokens.nextToken());
		Calendar date = new GregorianCalendar(0, month, day);
		return date.getTimeInMillis() * 1000l;
	}

	private long parseTime(String timeText) {
		StringTokenizer timeTokens = new StringTokenizer(timeText, ":.");
		int hour = Integer.parseInt(timeTokens.nextToken());
		int min = Integer.parseInt(timeTokens.nextToken());
		int second = Integer.parseInt(timeTokens.nextToken());
		Calendar time = new GregorianCalendar(0, 0, 0, hour, min, second);
		return time.getTimeInMillis() * 1000l;
	}

}
