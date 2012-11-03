package ep2;

/**
 * <p>
 * Classe que representa uma entrada no relatório de combate.
 * </p>
 */
public class LogEntry {

	// Índices importantes na entrada de dano
	private final static int SOURCENAME_INDEX = 2;
	private final static int TARGETNAME_INDEX = 6;
	private final static int AMOUNT_INDEX = 9;

	// Índice do prefixo do tipo de entrada
	private final static int PREFIX_INDEX = 0;

	private long time;
	private String[] entryParams;
	private String[] entryTypeAffixes;

	LogEntry(long time, String[] entryParams, String[] entryTypeAffixes) {
		this.time = time;
		this.entryParams = entryParams;
		this.entryTypeAffixes = entryTypeAffixes;
	}

	public long getTime() {
		return this.time;
	}

	public boolean isDamageEntry() {
		return this.entryTypeAffixes[entryTypeAffixes.length - 1]
				.equals("DAMAGE") && !getTypePrefix().equals("  ENVIRONMENTAL");
	}

	public boolean isResurrectEntry() {
		return this.entryTypeAffixes[entryTypeAffixes.length - 1]
				.equals("RESURRECT");
	}

	public String getSourceName() {
		return this.entryParams[SOURCENAME_INDEX];
	}

	public String getTargetName() {
		return this.entryParams[TARGETNAME_INDEX];
	}

	public String getAmount() {
		return this.entryParams[AMOUNT_INDEX];
	}

	private String getTypePrefix() {
		return this.entryTypeAffixes[PREFIX_INDEX];
	}
}
