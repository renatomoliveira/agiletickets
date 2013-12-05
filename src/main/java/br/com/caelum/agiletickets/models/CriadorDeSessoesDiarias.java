package br.com.caelum.agiletickets.models;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class CriadorDeSessoesDiarias implements CriadorDeSessoes {

	@Override
	public List<Sessao> cria(LocalDate inicio, LocalDate fim, LocalTime horario, Espetaculo espetaculo) {
		List<Sessao> lista = new ArrayList<Sessao>();
		if(inicio.isAfter(fim)) return lista;
		int diferencaDeDias = Days.daysBetween(inicio, fim).getDays();
		int i =0;
		do{
			Sessao sessao = new Sessao();
			sessao.setInicio(inicio.plusDays(i).toDateTime(horario));
			sessao.setEspetaculo(espetaculo);
			lista.add(sessao);
			i++;
		}while(i <= diferencaDeDias);
		return lista;
	}

}
