package com.feliciano.demo.resources;

import com.feliciano.demo.dto.CidadeDTO;
import com.feliciano.demo.dto.EstadoDTO;
import com.feliciano.demo.resources.domain.Cidade;
import com.feliciano.demo.resources.domain.Estado;
import com.feliciano.demo.services.CidadeService;
import com.feliciano.demo.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

    @Autowired
    private EstadoService service;

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(method = RequestMethod.GET)
    private ResponseEntity<List<EstadoDTO>> findAll() {
        List<Estado> obj = service.findAll();
        List<EstadoDTO> listDto = obj.stream().map(EstadoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
    private ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
        List<Cidade> obj = cidadeService.findByEstado(estadoId);
        List<CidadeDTO> listDto = obj.stream().map(CidadeDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

}
