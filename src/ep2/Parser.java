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
		if (entryTypeAffixes[0].equals("  ENVIRONMENTAL"))
			return null;
		Calendar time = new GregorianCalendar(1970, 0, 0);
		parseDate(time, dateText);
		parseTime(time, timeText);
		String name = entryParams[2];
		long amount = Long.parseLong(entryParams[9]);
		return new Damage(name, time.getTimeInMillis()*1000, amount);
	}
	
	private boolean isDamageEntry (final String[] entryTypeAffixes) {
		return entryTypeAffixes[entryTypeAffixes.length-1].equals("DAMAGE");
	}

	private void parseDate(Calendar time, String dateText) {
		StringTokenizer dateTokens = new StringTokenizer(dateText, "/");
		int month = Integer.parseInt(dateTokens.nextToken());
		int day = Integer.parseInt(dateTokens.nextToken());
		time.set(Calendar.MONTH, month);
		time.set(Calendar.DAY_OF_MONTH, day);
	}

	private void parseTime(Calendar time, String timeText) {
		StringTokenizer timeTokens = new StringTokenizer(timeText, ":");
		int hour = Integer.parseInt(timeTokens.nextToken());
		int min = Integer.parseInt(timeTokens.nextToken());
		float second = Float.parseFloat(timeTokens.nextToken());
		time.set(Calendar.HOUR_OF_DAY, hour);
		time.set(Calendar.MINUTE, min);
		time.set(Calendar.SECOND, (int)second);
	}

}
