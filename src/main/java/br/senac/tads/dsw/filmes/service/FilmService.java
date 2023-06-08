package br.senac.tads.dsw.filmes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senac.tads.dsw.filmes.model.ResponseMessage;
import br.senac.tads.dsw.filmes.model.dto.FilmDTO;
import br.senac.tads.dsw.filmes.model.entity.FilmEntity;
import br.senac.tads.dsw.filmes.repository.FilmRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FilmService {

	@Autowired
	private FilmRepository filmRepository;

	/**
	 * Register new film.
	 * 
	 * @param filmDto
	 * @return id film registered
	 */
	public ResponseMessage addNewFilm(FilmDTO filmDto) {
		FilmEntity filmEntity = convertToEntity(filmDto);
		filmEntity = filmRepository.saveAndFlush(filmEntity);

		if (filmEntity.getId() != null) {
			log.info("[INFO] Films register Sucess");
			return new ResponseMessage(202, "Sucesso ao registra novo filme", filmEntity.getId());
		}

		log.error("[ERROR] Error in register film");
		return new ResponseMessage(404, "Error ao registrar film", null);
	}

	/**
	 * Request get all films database
	 * 
	 * @return list films in database
	 */
	public ResponseMessage findByAllFilms() {
		List<FilmEntity> listFilms = filmRepository.findAll();

		if (!listFilms.isEmpty()) {
			log.info("[INFO] Films list Sucess");
			return new ResponseMessage(202, "Sucesso ao listar os filmes", listFilms);
		} else if (listFilms.isEmpty()) {
			log.info("[INFO] Films list Sucess, list is empty");
			return new ResponseMessage(202, "Lista esta vazia, adicione um filme", listFilms);
		}
		log.error("[ERROR] Erro in list films");
		return new ResponseMessage(404, "Erro ao Listar filmes", null);
	}

	/**
	 * Request get film database based id.
	 * 
	 * @return list films find in database
	 */
	public ResponseMessage findByIdFilm(Integer id) {
		Boolean isExists = filmRepository.existsById(id);
	
		if (isExists == true && isExists != null) {
			Optional<FilmEntity> filmFindId = filmRepository.findById(id);
			log.info("[INFO] Success in searching movie");
			return new ResponseMessage(202, "Sucesso ao buscar filme por ID ", filmFindId.get());

		} else if (isExists == false && isExists != null) {
			log.warn("[WARN] Could not find any movies");
			return new ResponseMessage(202, "Não foi possivel encontrar nenhum filme com ID: " + id, false);
		}

		log.error("[ERROR] Error in register film");
		return new ResponseMessage(404, "Erro ao pesquisar o filme: " + id, null);
	}

	/**
	 * Request get find title films database.
	 * 
	 * @return list films find in database
	 */
	public ResponseMessage findByTitleFilm(String title) {
		List<FilmEntity> listFindFilms = filmRepository.findByTitleContainingIgnoreCase(title);

		if (listFindFilms != null && !listFindFilms.isEmpty()) {
			log.info("[INFO] Sucess List Films Search");
			return new ResponseMessage(202, "Sucesso Listar filme relacionado ao titulo: " + title, listFindFilms);

		} else if (listFindFilms != null && listFindFilms.isEmpty()) {
			log.warn("[WARN] Could not find any movies");
			return new ResponseMessage(202, "Não foi possivel encontrar nenhum filme com o titulo: " + title,
					listFindFilms);
		}

		log.error("[ERROR] Error in register film");
		return new ResponseMessage(404, "Erro ao pesquisar o filme: " + title, null);
	}

	/**
	 * Update movie data
	 * 
	 * @param id      film
	 * @param filmDto
	 * @return
	 */
	public ResponseMessage updateFilm(Integer id, FilmDTO filmDto) {
		Boolean isExists = filmRepository.existsById(id);

		if (isExists == true && isExists != null) {
			FilmEntity modifiedFilm = convertToEntity(filmDto);
			modifiedFilm.setId(id);

			FilmEntity filmUpdated = filmRepository.save(modifiedFilm);

			if (filmUpdated != null) {
				return new ResponseMessage(202, "Filme atualizado com Sucesso", true);
			}
		} else if (isExists == false && isExists != null) {
			log.info("[INFO] Couldn't find the movie");
			return new ResponseMessage(502, "Não foi possivel encontrar o filme", false);
		}

		return new ResponseMessage(404, "Não foi possivel atualizar o filme", null);
	}

	/**
	 * Delete film.
	 * 
	 * @param filmDto
	 * @return id film registered
	 */
	public ResponseMessage deleteFilm(Integer id) {
		Boolean isExists = filmRepository.existsById(id);

		if (isExists == true) {
			log.info("[INFO] Successfully deleted movie");
			filmRepository.deleteById(id);
			return new ResponseMessage(202, "Sucesso ao deletar filme", true);

		} else if (isExists == false) {
			log.info("[INFO] Could not find the movie");
			return new ResponseMessage(404, "Não foi possivel encontrar o filme", false);
		}

		log.error("[ERROR] Error in register film");
		return new ResponseMessage(404, "Error ao deletar filme", null);
	}

	/**
	 * Convert FilmDTO in Entity
	 * 
	 * @param filmDto
	 * @return
	 */
	private static FilmEntity convertToEntity(FilmDTO filmDto) {
		FilmEntity filmEntity = new FilmEntity(filmDto.getTitle(), filmDto.getCategory(), filmDto.getYear());
		return filmEntity;
	}

}
