package br.senac.tads.dsw.filmes.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_FILM")
public class FilmEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Preencha o titulo do filme")
	@Size(max = 125)
	@Column(name = "FILM_TITLE")
	private String title;

	@NotBlank(message = "Preencha o campo genero")
	@Size(max = 125)
	@Column(name = "FILM_CATEGORY")
	private String category;

	@NotNull(message = "Preencha o campo ano")
	@Digits(integer = 4, message = "É permitido no máximo 4 digitos", fraction = 0)
	@Column(name = "FILM_YEAR")
	private Integer year;

	public FilmEntity() {
		super();
	}

	public FilmEntity(String title, String category, Integer year) {
		super();
		this.title = title;
		this.category = category;
		this.year = year;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
