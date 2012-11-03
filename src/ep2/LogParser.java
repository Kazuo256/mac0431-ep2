package ep2;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * <p>
 * Classe do analisador léxico dos relatórios de combate gerados pelo jogo World
 * of Warcraft.
 * </p>
 * <p>
 * Uma referência desatualizada de como as entradas dos relatórios gerados são
 * estruturadas pode ser encontrada nesse link:
 * <url>http://www.wowwiki.com/API_COMBAT_LOG_EVENT</url>
 * </p>
 */
public class LogParser {

	// Índice do parâmetro que indica o tipo da entrada
	private final static int ENTRYTYPE_INDEX = 0;

	// Ano base usado para as medições de tempo
	private final static int EPOCH = 1970;

	public LogEntry parseEntry(String line) {
		// Como sempre, código para analisar strings é uma bagunça.
		StringTokenizer tokens = new StringTokenizer(line, " ");
		String dateText = tokens.nextToken();
		String timeText = tokens.nextToken();
		String entryText = tokens.nextToken("\n\r");
		String[] entryParams = entryText.split(",");
		String[] entryTypeAffixes = entryParams[ENTRYTYPE_INDEX].split("_");
		Calendar time = new GregorianCalendar(EPOCH, 0, 0);
		parseDate(time, dateText);
		parseTime(time, timeText);
		return new LogEntry(time.getTimeInMillis() / 1000, entryParams,
				entryTypeAffixes);
	}

	public Damage parseDamage(LogEntry entry) {
		String name;
		long amount;
		name = entry.getSourceName();
		amount = Long.parseLong(entry.getAmount());
		return new Damage(name, entry.getTime(), amount);
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
		time.set(Calendar.SECOND, (int) second);
	}

}
