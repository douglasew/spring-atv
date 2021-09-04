package com.example.atividade.controller;

import com.example.atividade.model.Pessoa;
import com.example.atividade.repository.PessoaRepository;
import com.example.atividade.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return PessoaService.listar();
    }

    @PostMapping
    public Pessoa Cadastrar(@RequestBody Pessoa pessoa){
        PessoaService.Cadastrar(pessoa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pessoa.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public void Alterar(@RequestBody Pessoa pessoa){
        PessoaService.atualizar(pessoa);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Pessoa> Buscar(@PathVariable("id") Long id){
        return PessoaService.searchId(id).map(pessoa ->
                ResponseEntity.ok(pessoa))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public void Deletar(@PathVariable Long id){
        PessoaService.deletar(id);
    }

}
