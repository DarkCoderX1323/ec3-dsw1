package com.example.ec3dsw1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentoRepository extends JpaRepository<Instrumento, Long> {
}
