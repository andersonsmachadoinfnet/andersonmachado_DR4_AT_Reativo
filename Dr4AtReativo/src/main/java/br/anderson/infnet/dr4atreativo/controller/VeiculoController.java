package br.anderson.infnet.dr4atreativo.controller;

import br.anderson.infnet.dr4atreativo.model.Veiculo;
import br.anderson.infnet.dr4atreativo.service.VeiculoWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoWebClient _wc;

    public VeiculoController(VeiculoWebClient wc) {
        this._wc = wc;
    }

    @GetMapping
    public Flux<Veiculo> findAll() {
        return _wc.getAllVeiculo();
    }

    @GetMapping("/{id}")
    public Mono<Veiculo> findById(@PathVariable Long id) {
        return _wc.getVeiculoById(id);
    }

    @PostMapping
    public Mono<Veiculo> save(@RequestBody Veiculo item) {
        return _wc.saveVeiculo(item);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id) {
        return _wc.deleteVeiculo(id);
    }
}
