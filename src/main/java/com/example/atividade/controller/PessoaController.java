package com.example.atividade.controller;

import com.example.atividade.model.Pessoa;
import com.example.atividade.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")

public class PessoaController {

    @Autowired
    PessoaRepository repository;

    @GetMapping
    public List<Pessoa> Listar() {
        return this.repository.findAll();
    }

    @PostMapping
    public Pessoa Cadastrar(@RequestBody Pessoa pessoa){
        this.repository.save(pessoa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pessoa.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public void Alterar(@RequestBody Pessoa pessoa){
        this.repository.save(pessoa);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Pessoa> Buscar(@PathVariable("id") Long id){
        return this.repository.findById(id).map(pessoa ->
                ResponseEntity.ok(pessoa))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public void Deletar(@PathVariable Long id){
        this.repository.deleteById(id);
    }

}
