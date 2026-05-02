package com.empresa.erp.vendas.infrastructure;

import com.empresa.erp.vendas.domain.model.Venda;
import com.empresa.erp.vendas.domain.repository.VendaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public class VendaRepositoryJpa implements VendaRepository {

    private final VendaJpaRepository jpaRepository;

    public VendaRepositoryJpa(VendaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void salvar(Venda venda) {
        jpaRepository.save(venda);
    }

    @Override
    public Optional<Venda> buscarPorId(UUID id) {
        return jpaRepository.findById(id);
    }
}