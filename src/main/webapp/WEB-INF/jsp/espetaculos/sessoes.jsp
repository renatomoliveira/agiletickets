<html>
	<head>
		<title>${espetaculo.nome}</title>
	</head>
	<body>
		<h2>Criar novas sess�es</h2>
		<form action="/espetaculo/${espetaculo.id}/sessoes" method="post">
			<label for="inicio">In�cio</label>
			<input type="text" name="inicio" id="inicio"/>
						
			<label for="fim">Fim</label>
			<input type="text" name="fim" id="fim"/>
			
			<label for="horario">Hor�rio</label>
			<input type="text" name="horario" id="horario"/>
			
			<label for="periodo">Periodicidade</label>
			<select name="periodo" id="periodo">
				<option value="DIARIA">Di�ria</option>		
				<option value="SEMANAL">Semanal</option>			
			</select>
<<<<<<< HEAD
			<input type ="submit" value="Criar" />
=======
			<input type="submit" value="Criar">
>>>>>>> 52f38f79cf1a8a4aa7033cb360e090036182d0e8
		</form>
	
	</body>
</html>