package br.com.alurafood.pagamentos.controller;

import br.com.alurafood.pagamentos.dto.PagamentoDto;
import br.com.alurafood.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public Page<PagamentoDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return pagamentoService.obterTodos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> listarPorId(@NotNull @PathVariable Long id) {
        var pagamento = pagamentoService.obertPorId(id);
        return ResponseEntity.ok(pagamento);
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> criarPagamento(@Valid @RequestBody PagamentoDto pagamentoDto, UriComponentsBuilder uriComponentsBuilder) {
        var pagamento = pagamentoService.criarPagamento(pagamentoDto);
        var endereco = uriComponentsBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();
        return ResponseEntity.created(endereco).body(pagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDto> alterarPagamento(@Valid @RequestBody PagamentoDto pagamentoDto, @NotNull @PathVariable Long id) {
        var pagamentoAtualizado = pagamentoService.atualizarPagamento(pagamentoDto, id);
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoDto> excluirPagamento(@NotNull @PathVariable Long id) {
        pagamentoService.excluirPagamento(id);
        return ResponseEntity.noContent().build();
    }

}
