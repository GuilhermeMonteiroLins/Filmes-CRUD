package br.senac.tads.dsw.filmes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.tads.dsw.filmes.model.ResponseMessage;
import br.senac.tads.dsw.filmes.model.dto.FilmDTO;
import br.senac.tads.dsw.filmes.service.FilmService;

@RestController
@RequestMapping("/film")
public class FilmController {

	@Autowired
	private FilmService filmService;

	private ResponseMessage responseMessage;

	/**
	 * Register new Film;
	 * 
	 * @param filmDto
	 * @return
	 */
	@PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseMessage> addNewFilm(@RequestBody FilmDTO filmDto) {

		responseMessage = filmService.addNewFilm(filmDto);

		if (responseMessage.getStatus() == 202) {
			return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	}

	/**
	 * Lists all movies in the database.
	 * 
	 * @return Lista de filmes.
	 */
	@GetMapping(produces = "application/json")
	public ResponseEntity<ResponseMessage> findAllFilms() {

		responseMessage = filmService.findByAllFilms();

		if (responseMessage.getStatus() == 202) {
			return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	}

	/**
	 * Get a movie from the database based on its id.
	 * 
	 * @param title film
	 * @return Lista de filmes pesquisado
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<ResponseMessage> findIdFilm(@PathVariable Integer id) {

		responseMessage = filmService.findByIdFilm(id);

		if (responseMessage.getStatus() == 202) {
			return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	}

	/**
	 * Search for a movie based on its title.
	 * 
	 * @param title film
	 * @return Lista de filmes pesquisado
	 */
	@GetMapping(value = "/search/{title}", produces = "application/json")
	public ResponseEntity<ResponseMessage> findTitleFilm(@PathVariable String title) {

		responseMessage = filmService.findByTitleFilm(title);

		if (responseMessage.getStatus() == 202) {
			return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	}

	/**
	 * Update Film;
	 * 
	 * @param filmDto
	 * @return
	 */
	@PutMapping(value = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseMessage> updateFilm(@PathVariable Integer id, @RequestBody FilmDTO filmDto) {

		responseMessage = filmService.updateFilm(id, filmDto);

		if (responseMessage.getStatus() == 202) {
			return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	}

	/**
	 * Delete Film;
	 * 
	 * @param filmDto
	 * @return
	 */
	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public ResponseEntity<ResponseMessage> updatedFilm(@PathVariable Integer id) {

		responseMessage = filmService.deleteFilm(id);

		if (responseMessage.getStatus() == 202) {
			return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	}
}
