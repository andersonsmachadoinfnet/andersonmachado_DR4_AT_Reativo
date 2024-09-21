package br.anderson.infnet.dr4atreativo.service;

import br.anderson.infnet.dr4atreativo.model.Veiculo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VeiculoWebClient {
    private final WebClient webClient;

    public VeiculoWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Flux<Veiculo> getAllVeiculo() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(Veiculo.class);
    }
    public Mono<Veiculo> getVeiculoById(Long id){
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Veiculo.class);
    }

    public Mono<Veiculo> saveVeiculo(Veiculo veiculo) {
        return webClient.post()
                .bodyValue(veiculo)
                .retrieve()
                .bodyToMono(Veiculo.class);
    }

    public Mono<Void> deleteVeiculo(Long id){
        return webClient.delete()
                .uri("/{id}",id)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Veiculo> updateVeiculo(Long id, Veiculo item) {
        return webClient.put()
                .uri("/{id}", id)
                .bodyValue(item)
                .retrieve()
                .bodyToMono(Veiculo.class);
    }

}
