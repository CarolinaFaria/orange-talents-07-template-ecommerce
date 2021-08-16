package com.carolinapaulo.desafiomercadolivre.produto.pergunta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<PerguntaModel, Long> {

}
