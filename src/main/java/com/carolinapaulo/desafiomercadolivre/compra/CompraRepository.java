package com.carolinapaulo.desafiomercadolivre.compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<CompraModel, Long> {

}
