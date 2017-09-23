package br.edu.unicesumar.model;

/**
 * Created by UniCesumar on 02/09/2017.
 */
public class Livro {
    private Long id;
    private String nomeLivro;
    private String autor;

    public Long getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }
}
