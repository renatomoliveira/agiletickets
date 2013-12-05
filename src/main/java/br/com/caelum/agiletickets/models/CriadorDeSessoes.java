package br.com.caelum.agiletickets.models;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public interface CriadorDeSessoes {
	
	public List<Sessao> cria(LocalDate inicio, LocalDate fim, LocalTime horario, Espetaculo espetaculo);

}
