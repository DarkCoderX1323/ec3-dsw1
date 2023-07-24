package com.example.ec3dsw1;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/instrumentos")
public class InstrumentoController {

    @Autowired
    private InstrumentoRepository instrumentoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Instrumento> getAllInstrumentos() {
        return instrumentoRepository.findAll();
    }

    @PostMapping
    public Instrumento createInstrumento(@RequestBody Instrumento instrumento) {
        // Obtenemos la categoría del instrumento usando su ID
        Categoria categoria = categoriaRepository.findById(instrumento.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("No se encontró la categoría"));

        // Asociamos la categoría al instrumento
        instrumento.setCategoria(categoria);

        // Guardamos el instrumento en la base de datos
        return instrumentoRepository.save(instrumento);
    }

    @GetMapping("/{id}/categoria")
    public Categoria getCategoriaOfInstrumento(@PathVariable Long id) {
        Instrumento instrumento = instrumentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el instrumento"));
        return instrumento.getCategoria();
    }
}
