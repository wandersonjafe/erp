package com.empresa.erp.vendas.infrastructure;

import com.empresa.erp.vendas.domain.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface VendaJpaRepository extends JpaRepository<Venda, UUID> {
}