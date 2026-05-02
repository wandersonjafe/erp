package com.empresa.erp.clientes.application;

import com.empresa.erp.clientes.application.dto.ClienteRequest;
import com.empresa.erp.clientes.application.dto.ClienteResponse;
import com.empresa.erp.clientes.domain.model.Cliente;
import com.empresa.erp.clientes.domain.repository.ClienteRepository;
import com.empresa.erp.clientes.domain.valueobject.CPF;
import com.empresa.erp.clientes.domain.valueobject.Endereco;
import com.empresa.erp.shared.exception.ErpException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteResponse cadastrar(ClienteRequest request) {
        Cliente cliente = new Cliente(
                request.nome(),
                request.email(),
                new CPF(request.cpf()),
                new Endereco(request.logradouro(), request.numero(), request.cep(), request.cidade(), request.estado())
        );
        clienteRepository.salvar(cliente);
        return toResponse(cliente);
    }
    public ClienteResponse buscarPorId(UUID id) {
        Cliente cliente = clienteRepository.buscarPorId(id)
                .orElseThrow(() -> new ErpException("Cliente não encontrado"));
        return toResponse(cliente);
    }
    public List<ClienteResponse> listarTodos() {
        return clienteRepository.listarTodos()
                .stream()
                .map(this::toResponse)
                .toList();
    }
    public ClienteResponse atualizar(UUID id, ClienteRequest request) {
        Cliente cliente = clienteRepository.buscarPorId(id)
                .orElseThrow(() -> new ErpException("Cliente não encontrado!"));
        cliente.atualizarNome(request.nome());
        cliente.atualizarEmail(request.email());
        cliente.atualizarEndereco(
                new Endereco(request.logradouro(), request.numero(), request.cep(), request.cidade(), request.estado())
        );
        clienteRepository.salvar(cliente);
        return toResponse(cliente);
    }

    public void deletar(UUID id) {
        clienteRepository.buscarPorId(id)
                .orElseThrow(() -> new ErpException("Cliente não encontrado!"));
        clienteRepository.deletar(id);
    }
    private ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getEndereco().getLogradouro(),
                cliente.getEndereco().getNumero(),
                cliente.getEndereco().getCep(),
                cliente.getEndereco().getCidade(),
                cliente.getEndereco().getEstado()
        );
    }
}
