package com.empresa.erp.produtos.infrastructure;

import com.empresa.erp.produtos.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoJpaRepository extends JpaRepository<Produto, UUID> {

}
