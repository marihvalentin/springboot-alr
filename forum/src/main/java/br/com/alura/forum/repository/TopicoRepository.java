package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long>
{
    //metodo seguindo o padrão jpa, tem a query gerada automaticamente
    Page<Topico> findByCursoNome(String nomeCurso, Pageable paginacao);

    //metodo com nome que não segue o padrão tem a necessidade de anotação
    //para apontar que o spring crie a query e anotação para setar os parametros
    /*
    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
    List<Topico> carregaPorNomeDoCurso(@Param("nomeCurso")String nomeCurso);
     */
}