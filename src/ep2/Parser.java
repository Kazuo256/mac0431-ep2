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
public class Parser {
	
	// Índices importantes na entrada de dano
	private final static int ENTRYTYPE_INDEX = 0;
	private final static int SOURCENAME_INDEX = 2;
	private final static int AMOUNT_INDEX = 9;
	
	// Índice do prefixo do tipo de entrada
	private final static int PREFIX_INDEX = 0;
	
	// Ano base usado para as medições de tempo
	private final static int EPOCH = 1970;

	public Damage parseDamage(String line) {
		// Como sempre, código para analisar strings é uma bagunça.
		StringTokenizer tokens = new StringTokenizer(line, " ");
		String dateText = tokens.nextToken();
		String timeText = tokens.nextToken();
		String entryText = tokens.nextToken("\n\r");
		String[] entryParams = entryText.split(",");
		String[] entryTypeAffixes = entryParams[ENTRYTYPE_INDEX].split("_");
		if (!isDamageEntry(entryTypeAffixes))
			return null;
		if (entryTypeAffixes[PREFIX_INDEX].equals("  ENVIRONMENTAL"))
			return null;
		Calendar time = new GregorianCalendar(EPOCH, 0, 0);
		parseDate(time, dateText);
		parseTime(time, timeText);
		String name = entryParams[SOURCENAME_INDEX];
		long amount = Long.parseLong(entryParams[AMOUNT_INDEX]);
		return new Damage(name, time.getTimeInMillis() / 1000, amount);
	}

	private boolean isDamageEntry(final String[] entryTypeAffixes) {
		return entryTypeAffixes[entryTypeAffixes.length - 1].equals("DAMAGE");
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
