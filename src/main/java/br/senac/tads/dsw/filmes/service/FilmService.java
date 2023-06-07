package br.senac.tads.dsw.filmes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.senac.tads.dsw.filmes.model.dto.FilmDTO;
import br.senac.tads.dsw.filmes.model.entity.FilmEntity;
import br.senac.tads.dsw.filmes.repository.FilmRepository;

@Service
public class FilmService {

	@Autowired
	private FilmRepository filmRepository;
	
	/**
	 * Request get all films database
	 * 
	 * @return list films in database
	 */
	public List<FilmEntity> getAllFilms(){
		List<FilmEntity> listFilms = filmRepository.findAll();
		return listFilms;
	}
	
	/**
	 * 
	 * @param filmDto
	 * @return id film registered
	 */
	@Transactional
	public Integer addNewFilm(FilmDTO filmDto){
		FilmEntity filmEntity = convertToEntity(filmDto);
		filmRepository.save(filmEntity);
		
		if(filmEntity.getId() != null) {
			return filmEntity.getId();
		}
		return null;
	}
	
	/**
	 * Convert FilmDTO in Entity
	 * 
	 * @param filmDto
	 * @return
	 */
	private FilmEntity convertToEntity(FilmDTO filmDto) {
		FilmEntity filmEntity = new FilmEntity(filmDto.getTitle(), 
											   filmDto.getCategory(),
											   filmDto.getYear());
		return filmEntity;
	}
	
}
