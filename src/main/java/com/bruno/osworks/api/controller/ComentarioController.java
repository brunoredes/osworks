package com.bruno.osworks.api.controller;

import com.bruno.osworks.api.model.Comentario;
import com.bruno.osworks.api.model.ComentarioInput;
import com.bruno.osworks.api.model.ComentarioModel;
import com.bruno.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.bruno.osworks.domain.model.OrdemServico;
import com.bruno.osworks.domain.repository.ComentarioRepository;
import com.bruno.osworks.domain.repository.OrdemServicoRepository;
import com.bruno.osworks.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    @Autowired
    GestaoOrdemServicoService gestaoOrdemServico;

    @GetMapping
    public List<ComentarioModel> listar(@PathVariable Long ordemServicoId) {
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));

        return toCollectionModel(ordemServico.getComentarios());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioModel adicionar(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioInput comentarioInput) {
        Comentario comentario = gestaoOrdemServico.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());

        return toModel(comentario);
    }

    private ComentarioModel toModel(Comentario comentario) {
        return modelMapper.map(comentario, ComentarioModel.class);
    }

    private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
        return comentarios.stream()
                .map(comentario -> toModel(comentario))
                .collect(Collectors.toList());
    }
}
