package br.com.caelum.agiletickets.models;

public enum Periodicidade {
	DIARIA {
		@Override
		public CriadorDeSessoes getCriadorDeSessoes() {
			return new CriadorDeSessoesDiarias();
		}
	},SEMANAL {
		@Override
		public CriadorDeSessoes getCriadorDeSessoes() {
			return new CriadorDeSessoesSemanais();
		}
	};
	public abstract CriadorDeSessoes getCriadorDeSessoes();
}
