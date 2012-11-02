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
		String entryText = tokens.nextToken("\n\r");
		String[] entryParams = entryText.split(",");
		String[] entryTypeAffixes = entryParams[0].split("_");
		if (!isDamageEntry(entryTypeAffixes))
			return null;
		long time = parseDate(dateText) + parseTime(timeText);
		String name = entryParams[2];
		int offset = 14;
		if (entryTypeAffixes[0] == "SWING")
			offset -= 3;
		else if (entryTypeAffixes[0] == "ENVIRONMENTAL")
			offset -= 2;
		long amount = Long.parseLong(entryParams[offset]);
		return new Damage(name, time, amount);
	}
	
	private boolean isDamageEntry (final String[] entryTypeAffixes) {
		return entryTypeAffixes[entryTypeAffixes.length-1].equals("DAMAGE");
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
