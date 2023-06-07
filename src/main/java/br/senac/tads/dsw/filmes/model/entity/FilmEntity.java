package br.senac.tads.dsw.filmes.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	@Column(name = "TITLE")
	private String title;

	@NotBlank(message = "Preencha o campo genero")
	@Size(max = 125)
	@Column(name = "CATEGORY")
	private String category;

	@NotNull(message = "Preencha o campo ano")
	@Size(max = 4)
	@Column(name = "YEAR")
	private String year;

	public FilmEntity() {
		super();
	}

	public FilmEntity(String title, String category, String year) {
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
