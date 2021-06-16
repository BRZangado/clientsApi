package com.platformtest.service;

import com.platformtest.dto.client.ClientDto;
import com.platformtest.error.exception.ClientNotFoundException;
import com.platformtest.error.exception.EmptyBaseException;
import com.platformtest.model.Client;
import com.platformtest.repository.ClientRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client create(ClientDto clientDto) {
        Client client = ClientDto.toEntity(clientDto);
        return clientRepository.save(client);
    }

    @Override
    public List<Client> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Client> clients = clientRepository.findAll(pageable).getContent();
        if (clients.isEmpty()) throw new EmptyBaseException();
        return clients;
    }

    @Override
    public Client find(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(!optionalClient.isPresent()) throw new ClientNotFoundException();
        return optionalClient.get();
    }

    @Override
    public void delete(Long id) {
        if(!clientRepository.existsById(id)) throw new ClientNotFoundException();
        clientRepository.deleteById(id);
    }

    @Override
    public Client update(ClientDto dto, Long id) {
        if(!clientRepository.existsById(id)) throw new ClientNotFoundException();
        Client client = ClientDto.toEntity(dto);
        client.setId(id);
        return clientRepository.save(client);
    }

    @Override
    public Client patchName(String name, Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(!optionalClient.isPresent()) throw new ClientNotFoundException();
        Client client = optionalClient.get();
        client.setName(name);
        return clientRepository.save(client);
    }
}
