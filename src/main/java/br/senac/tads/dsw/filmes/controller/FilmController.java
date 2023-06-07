package br.senac.tads.dsw.filmes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.tads.dsw.filmes.model.ResponseMessage;
import br.senac.tads.dsw.filmes.model.dto.FilmDTO;
import br.senac.tads.dsw.filmes.model.entity.FilmEntity;
import br.senac.tads.dsw.filmes.service.FilmService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/film")
@Slf4j
public class FilmController {

	@Autowired
	private FilmService filmService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<ResponseMessage> getAllFilms() {

		List<FilmEntity> listFilms = filmService.getAllFilms();

		if (!listFilms.isEmpty()) {
			log.info("[INFO] Films list Sucess");
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage(202, "Sucesso ao listar os filmes", listFilms));
		} else if (listFilms.isEmpty()) {
			log.info("[INFO] Films list Sucess, list is empty");
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage(202, "Lista esta vazia, adicione um filme", listFilms));
		}
		log.error("[ERROR] Erro in list films");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(502, "Erro ao listar filmes", listFilms));
	}

	@PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseMessage> addNewFilm(@RequestBody FilmDTO filmDto) {

		Integer idFilm = filmService.addNewFilm(filmDto);

		if(idFilm != null) {
			log.info("[INFO] Films register Sucess");
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(202, "Sucesso ao registra novo filme", idFilm));
		}
		
		log.error("[ERROR] Error in register film");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(502, "Error ao registrar film", null));
	}

}
