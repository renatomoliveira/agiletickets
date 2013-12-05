package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

import sun.util.resources.LocaleData;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void deveCriarApenasUmaSessaoParaMesmoDia() {
		LocalDate dataAtual = new LocalDate();
		LocalTime horario = new LocalTime(21,00);
		DateTime dataHora = dataAtual.toDateTime(horario);
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(dataAtual, dataAtual, horario, Periodicidade.DIARIA);
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(dataHora, sessoes.get(0).getInicio());		 
	}
	
	@Test
	public void naoDeveCriarSessaoQuandoInicioMaiorQueFinal() {
		LocalDate dataInicio = new LocalDate().plusDays(1);
		LocalDate dataFinal = new LocalDate();
		LocalTime horario = new LocalTime(21,00);
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(dataInicio, dataFinal, horario, Periodicidade.DIARIA);
		Assert.assertEquals(0, sessoes.size());
	}
	
	@Test
	public void deveCriarCincoSessoesDiarias() {
		LocalDate dataInicio = new LocalDate();
		LocalDate dataFinal = new LocalDate().plusDays(4);		
		LocalTime horario = new LocalTime(21,00);
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(dataInicio, dataFinal, horario, Periodicidade.DIARIA);
		Assert.assertEquals(5, sessoes.size());
		//int i=0;
		//for (Sessao sessao : sessoes) {
		Sessao sessao = new Sessao();
		for(int i=0;i<sessoes.size();i++) {			
		    sessao=sessoes.get(i);
			DateTime dataHora = dataInicio.plusDays(i).toDateTime(horario);
			Assert.assertEquals(dataHora, sessao.getInicio());
			//i++;
		}		
	}
	
	public void deveCriarCincoSessoesSemanais() {
		LocalDate dataInicio = new LocalDate();
		LocalDate dataFinal = new LocalDate().plusWeeks(5);		
		LocalTime horario = new LocalTime(21,00);
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes(dataInicio, dataFinal, horario, Periodicidade.SEMANAL);
		Assert.assertEquals(5, sessoes.size());
		int i=0;
		for (Sessao sessao : sessoes) {
			DateTime dataHora = dataInicio.plusWeeks(i).toDateTime(horario);
			Assert.assertEquals(dataHora, sessao.getInicio());
			i++;
		}	
	}
}
