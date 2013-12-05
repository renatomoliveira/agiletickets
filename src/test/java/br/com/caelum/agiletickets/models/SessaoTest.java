package br.com.caelum.agiletickets.models;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class SessaoTest {

	@Test
	public void naoDeveVenderSeNaoHaVagas() throws Exception {
		Sessao sessao = new Sessao();
        sessao.setTotalIngressos(0);

        Assert.assertFalse(sessao.podeReservar(5));
	}

	@Test
	public void deveVender1ingressoSeHa2vagas() throws Exception {
		Sessao sessao = new Sessao();
        sessao.setTotalIngressos(2);

        Assert.assertTrue(sessao.podeReservar(1));
	}

	@Test
	public void naoDeveVender3ingressoSeHa2vagas() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(2);

		Assert.assertFalse(sessao.podeReservar(3));
	}
	
	@Test
	public void deveVender2ingressoSeHa2vagas() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(2);

		Assert.assertTrue(sessao.podeReservar(2));
	}

	@Test
	public void reservarIngressosDeveDiminuirONumeroDeIngressosDisponiveis() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(5);

		sessao.reserva(3);
		Assert.assertEquals(2, sessao.getIngressosDisponiveis().intValue());
	}
	
	//@Test
	/*public void deveReservar7IngressosSeHa7Vagas() {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(7);
		sessao.reserva(7);
		Assert.assertTrue(sessao.podeReservar(7));
	}*/
	
	
}
