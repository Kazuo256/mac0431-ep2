/**
 * 
 */
package ep2;

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
		StringTokenizer tokens = new StringTokenizer(line, " ");
		String dateText = tokens.nextToken();
		String timeText = tokens.nextToken();
		String entry = tokens.nextToken();
		StringTokenizer entryTokens = new StringTokenizer(entry, ",");
		String entry_type = entryTokens.nextToken();
		long time = parseDate(dateText) + parseTime(timeText);
		return new Damage("", time, 0l);
	}
	
	private boolean isDamageEntry (String entryText) {
		StringTokenizer entryTokenizer;
		return true;
	}

	private long parseDate(String dateText) {
		StringTokenizer dateTokens = new StringTokenizer(dateText, "/");
		int month = Integer.parseInt(dateTokens.nextToken());
		int day = Integer.parseInt(dateTokens.nextToken());
		Calendar date = new GregorianCalendar(0, month, day);
		return date.getTimeInMillis() * 1000l;
	}

	private long parseTime(String timeText) {
		StringTokenizer timeTokens = new StringTokenizer(timeText, ":");
		int hour = Integer.parseInt(timeTokens.nextToken());
		int min = Integer.parseInt(timeTokens.nextToken());
		float second = Float.parseFloat(timeTokens.nextToken());
		Calendar time = new GregorianCalendar(0, 0, 0, hour, min, (int)second);
		return time.getTimeInMillis() * 1000l;
	}

}
