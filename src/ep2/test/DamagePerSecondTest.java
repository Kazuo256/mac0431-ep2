package ep2.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import ep2.DamagePerSecond;

public class DamagePerSecondTest {
	private List<String> personagens = Lists.newArrayList();

	private DamagePerSecond dps;

	@Before
	public void before() {
		personagens.add("Umabel");
		personagens.add("Dorthellon");
		personagens.add("Alach");
		personagens.add("Manzo");
		this.dps = new DamagePerSecond(0L);
	}

	@Test
	public void danoNormal() {
		this.dps.adicionaDano("Umabel", 30000, 0);
		this.dps.adicionaDano("Umabel", 30000, 1);
		this.dps.adicionaDano("Umabel", 30000, 2);
		this.dps.adicionaDano("Umabel", 30000, 3);

		Assert.assertEquals(40000.0, this.dps.geraEstatistica("Umabel"));
	}

	@Test
	public void variosPersonagens() {
		for (String personagem : this.personagens) {
			for (long i = 1; i < 11; i++) {
				this.dps.adicionaDano(personagem, i*10000, i);
			}
		}
		for (String personagem : this.personagens) {
			Assert.assertEquals(55000.0 ,this.dps.geraEstatistica(personagem));
		}
	}
}
