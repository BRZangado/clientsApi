package com.platformtest.service;

import com.platformtest.dto.client.ClientDto;
import com.platformtest.model.Client;
import java.util.List;

public interface ClientService {
    Client create(ClientDto clientDto);
    List<Client> findAll(int page, int size);
    Client find(Long id);
    void delete(Long id);
    Client update(ClientDto dto, Long id);
    Client patchName(String name, Long id);
}
