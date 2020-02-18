package br.com.alura.forum.controller.form;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class TopicoForm
{
    @NotNull @NotEmpty @Length(min = 5)
    private String titulo;

    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;

    @NotNull @NotEmpty
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursorepository)
    {
        Curso curso = CursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
