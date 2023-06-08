package br.senac.tads.dsw.filmes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import br.senac.tads.dsw.filmes.model.entity.FilmEntity;

public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {

	List<FilmEntity> findByTitleContainingIgnoreCase(String title);
	
	@Modifying
	void deleteById(Integer id);
}
