package br.anderson.infnet.dr4atreativo;

import br.anderson.infnet.dr4atreativo.controller.VeiculoController;
import br.anderson.infnet.dr4atreativo.model.Veiculo;
import br.anderson.infnet.dr4atreativo.service.VeiculoWebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.*;

@WebFluxTest(VeiculoController.class)
public class VeiculoControllerTest {
    @Autowired
    private WebTestClient webClient;

    @MockBean
    private VeiculoWebClient veiculoService;

    private Veiculo veiculo;
    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        veiculo = new Veiculo(1,"VW","GOLF","Branco","KKK-0001");
    }

    @Test
    public void testFindAll(){
        doReturn(Flux.just(veiculo)).when(veiculoService).getAllVeiculo();

        webClient.get().uri("/api/veiculos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Veiculo.class)
                .hasSize(1)
                .contains(veiculo);

        verify(veiculoService, times(1)).getAllVeiculo();
    }

    @Test
    public void testFindById(){
        doReturn(Mono.just(veiculo)).when(veiculoService).getVeiculoById(1L);
        webClient.get().uri("/api/veiculos/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Veiculo.class)
                .isEqualTo(veiculo);

        verify(veiculoService, times(1)).getVeiculoById(1L);
    }

    @Test
    public void testDelete(){
        doReturn(Mono.empty()).when(veiculoService).deleteVeiculo(1L);
        webTestClient.delete().uri("/api/veiculos/1")
                .exchange()
                .expectStatus().isOk();

        verify(veiculoService, times(1)).deleteVeiculo(1L);
    }
}
