package com.empresa.erp.vendas.domain.repository;

import com.empresa.erp.vendas.domain.model.Venda;
import java.util.Optional;
import java.util.UUID;

public interface VendaRepository {
    void salvar (Venda venda);
    Optional<Venda> buscarPorId(UUID id);

}
