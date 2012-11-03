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

	private static class Entry {

		// Índices importantes na entrada de dano
		private final static int SOURCENAME_INDEX = 2;
		private final static int TARGETNAME_INDEX = 6;
		private final static int AMOUNT_INDEX = 9;

		// Índice do prefixo do tipo de entrada
		private final static int PREFIX_INDEX = 0;
		
		public long time;
		public String[] entryParams;
		public String[] entryTypeAffixes;

		Entry(long time, String[] entryParams, String[] entryTypeAffixes) {
			this.time = time;
			this.entryParams = entryParams;
			this.entryTypeAffixes = entryTypeAffixes;
		}

		public boolean isDamageEntry() {
			return entryTypeAffixes[entryTypeAffixes.length - 1]
					.equals("DAMAGE");
		}

		public boolean isResurrectEntry() {
			return entryTypeAffixes[entryTypeAffixes.length - 1]
					.equals("RESURRECT");
		}
		
		public String getTypePrefix() {
			return entryTypeAffixes[PREFIX_INDEX];
		}
		
		public String getSourceName() {
			return entryParams[SOURCENAME_INDEX];
		}
		
		public String getTargetName() {
			return entryParams[TARGETNAME_INDEX];
		}
		
		public String getAmount() {
			return entryParams[AMOUNT_INDEX];
		}
	}
	
	private final static int ENTRYTYPE_INDEX = 0;

	// Ano base usado para as medições de tempo
	private final static int EPOCH = 1970;

	public Damage parseDamage(String line) {
		Entry entry = parseEntry(line);
		if (!entry.isDamageEntry())
			return null;
		if (entry.getTypePrefix().equals("  ENVIRONMENTAL"))
			return null;
		String name;
		long amount;
		if (!entry.isResurrectEntry()) {
			name = entry.getSourceName();
			amount = Long.parseLong(entry.getAmount());
		} else {
			System.out.println("RESS");
			name = entry.getTargetName();
			amount = -1;
		}
		return new Damage(name, entry.time, amount);
	}

	private Entry parseEntry(String line) {
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
		return new Entry(time.getTimeInMillis() / 1000, entryParams,
				entryTypeAffixes);
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
