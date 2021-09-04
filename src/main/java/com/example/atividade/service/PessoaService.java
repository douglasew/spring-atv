package com.example.atividade.service;

import com.example.atividade.model.Pessoa;
import com.example.atividade.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    public List<Pessoa> listar() {
        return repository.findAll();
    }

    public Pessoa Cadastrar(Pessoa pessoa){
        Pessoa createPessoa =  repository.save(pessoa);

        return createPessoa;
    }

    public Pessoa atualizar(Pessoa pessoa) {
        Pessoa updatePessoa = repository.save(pessoa);

        return updatePessoa;
    }

    public Pessoa searchId(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("Pessoa não encontrada !"));
    }

    public void deletar(Long id) throws Exception {
        Pessoa deletePessoa = repository.findById(id)
                .orElseThrow(() -> new Exception("Pessoa não encontrada !"));

        return deletePessoa.deleteById(id);
    }
}
