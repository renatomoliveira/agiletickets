package br.com.caelum.agiletickets.models;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Weeks;

public class CriadorDeSessoesSemanais implements CriadorDeSessoes {

	@Override
	public List<Sessao> cria(LocalDate inicio, LocalDate fim, LocalTime horario, Espetaculo espetaculo) {
		List<Sessao> lista = new ArrayList<Sessao>();
		if(inicio.isAfter(fim)) return lista;
		int diferencaDeSemanas = Weeks.weeksBetween(inicio, fim).getWeeks();
		int i = 0;
		do{
			Sessao sessao = new Sessao();
			sessao.setInicio(inicio.plusWeeks(i).toDateTime(horario));
			sessao.setEspetaculo(espetaculo);
			lista.add(sessao);
			i++;
		}while(i <= diferencaDeSemanas);
		return lista;
	}

}
