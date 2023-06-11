function buscaDados(urlJson, params, jwt, accept = 'application/json') {
	return new Promise(function(resolve, reject) {
		const xhr = new XMLHttpRequest();
		let urlAjustada = urlJson;
		if (params) {
			urlAjustada = urlAjustada + '?' + params
		}
		xhr.open('GET', urlJson, true);
		xhr.setRequestHeader('Content-type', 'application/json');
		xhr.setRequestHeader('Accept', accept);
		if (jwt != null) {
			xhr.setRequestHeader('Authorization', 'Bearer ' + jwt);
		}

		xhr.onload = function() {
			if (xhr.status === 200) {
				const dados = JSON.parse(xhr.responseText);
				resolve(dados);
			} else {
				reject({
					status: xhr.status,
					mensagem: 'Erro na requisição'
				});
			}
		};
		xhr.send();
	});
}

function enviaDados(urlJson, dados) {
	return new Promise(function(resolve, reject) {
		let xhr = new XMLHttpRequest();
		xhr.open('POST', urlJson, true);
		xhr.setRequestHeader('Content-Type', 'application/json');

		xhr.onload = function() {
			if (xhr.status === 200) {
				resolve(JSON.parse(xhr.responseText));
			} else {
				reject({
					codigo: xhr.status,
					mensagem: 'Erro ao buscar dados'
				});
			}
		};
		xhr.send(JSON.stringify(dados));
	});
}