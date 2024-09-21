package br.anderson.infnet.dr4atreativo.repository;

import br.anderson.infnet.dr4atreativo.model.Fornecedor;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface FornecedorRepository extends R2dbcRepository<Fornecedor, Integer> {
}
