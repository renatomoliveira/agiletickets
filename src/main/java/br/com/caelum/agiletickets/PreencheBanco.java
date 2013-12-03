package br.com.caelum.agiletickets;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;

import br.com.caelum.agiletickets.models.Espetaculo;
import br.com.caelum.agiletickets.models.Estabelecimento;
import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;
import br.com.caelum.vraptor.util.jpa.EntityManagerCreator;
import br.com.caelum.vraptor.util.jpa.EntityManagerFactoryCreator;

public class PreencheBanco {

	// ALUNO: Não apague essa classe
	public static void main(String[] args) {
		EntityManager manager = getManager();
		manager.getTransaction().begin();
		apagarTabelas(manager);
		Estabelecimento estabelecimento = criaEstabelecimento();
		Espetaculo espetaculo = criaEspetaculo(estabelecimento);
		manager.persist(estabelecimento);
		manager.persist(espetaculo);
		criaSessoes(manager, espetaculo);
		manager.getTransaction().commit();
		manager.close();
	}

	private static void criaSessoes(EntityManager manager, Espetaculo espetaculo) {
		for (int i = 0; i < 10; i++) {
			Sessao sessao = criaSessao(espetaculo, i);
			manager.persist(sessao);
		}
	}

	private static EntityManager getManager() {
		EntityManagerFactoryCreator creator = new EntityManagerFactoryCreator();
		creator.create();
		EntityManagerCreator managerCreator = new EntityManagerCreator(creator.getInstance());
		managerCreator.create();
		EntityManager manager = managerCreator.getInstance();
		return manager;
	}

	private static Sessao criaSessao(Espetaculo espetaculo, int i) {
		Sessao sessao = new Sessao();
		sessao.setEspetaculo(espetaculo);
		sessao.setInicio(new DateTime().plusDays(7+i));
		sessao.setDuracaoEmMinutos(60 * 3);
		sessao.setTotalIngressos(10);
		sessao.setIngressosReservados(10 - i);
		return sessao;
	}

	private static Espetaculo criaEspetaculo(Estabelecimento estabelecimento) {
		Espetaculo espetaculo = new Espetaculo();
		espetaculo.setEstabelecimento(estabelecimento);
		espetaculo.setNome("Depeche Mode");
		espetaculo.setTipo(TipoDeEspetaculo.SHOW);
		return espetaculo;
	}

	private static Estabelecimento criaEstabelecimento() {
		Estabelecimento estabelecimento = new Estabelecimento();
		estabelecimento.setNome("Casa de shows");
		estabelecimento.setEndereco("Rua dos Silveiras, 12345");
		return estabelecimento;
	}

	private static void apagarTabelas(EntityManager manager) {
		manager.createQuery("delete from Sessao").executeUpdate();
		manager.createQuery("delete from Espetaculo").executeUpdate();
		manager.createQuery("delete from Estabelecimento").executeUpdate();
	}
}
