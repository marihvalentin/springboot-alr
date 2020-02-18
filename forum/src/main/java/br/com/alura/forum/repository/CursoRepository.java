package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long>
{
    static Curso findByNome(String nome) {
        return null;
    }
}
