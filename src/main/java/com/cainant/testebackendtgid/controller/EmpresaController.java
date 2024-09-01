package com.cainant.testebackendtgid.controller;

import com.cainant.testebackendtgid.domain.Empresa;
import com.cainant.testebackendtgid.dto.empresa.EmpresaCadastroDados;
import com.cainant.testebackendtgid.dto.empresa.EmpresaCriadaDados;
import com.cainant.testebackendtgid.dto.empresa.EmpresaListarDados;
import com.cainant.testebackendtgid.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid EmpresaCadastroDados cadastroDados, UriComponentsBuilder uriBuilder) {
        var empresa = new Empresa(cadastroDados);
        repository.save(empresa);

        var uri = uriBuilder.path("empresas/{id}").buildAndExpand(empresa.getId()).toUri();

        return ResponseEntity.created(uri).body(new EmpresaCriadaDados(empresa));
    }

    @GetMapping
    public ResponseEntity<Page<EmpresaListarDados>> listar(Pageable pagination) {
        var page = repository.findAll(pagination)
                .map(EmpresaListarDados::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var empresa = repository.getReferenceById(id);

        return ResponseEntity.ok(new EmpresaListarDados(empresa));
    }
}
