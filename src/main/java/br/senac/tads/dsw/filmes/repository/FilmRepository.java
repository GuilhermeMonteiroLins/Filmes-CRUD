package br.senac.tads.dsw.filmes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.tads.dsw.filmes.model.entity.FilmEntity;

public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {

}
