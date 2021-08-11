package com.carolinapaulo.desafiomercadolivre.produto.opinioes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpiniaoRepository extends JpaRepository<OpiniaoModel, Long> {


}
