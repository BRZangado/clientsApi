package com.platformtest.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.platformtest.dto.client.ClientDto;
import com.platformtest.error.exception.ClientNotFoundException;
import com.platformtest.error.exception.EmptyBaseException;
import com.platformtest.model.Client;
import com.platformtest.repository.ClientRepository;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientServiceTest {

  @InjectMocks
  private ClientServiceImpl clientService;

  @Mock
  private ClientRepository clientRepository;

  @Test(expected = EmptyBaseException.class)
  public void shouldShowDatabaseEmptyError(){
    Page<Client> clients = Page.empty();
    when(clientRepository.findAll(any(Pageable.class))).thenReturn(clients);
    clientService.findAll(0, 10);
  }

  @Test(expected = ClientNotFoundException.class)
  public void shouldShowClientNotFoundError(){
    when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());
    clientService.find((long)1);
  }

  @Test(expected = ClientNotFoundException.class)
  public void shouldShowClientNotFoundErrorOnDelete(){
    when(clientRepository.existsById(anyLong())).thenReturn(false);
    clientService.find((long)1);
  }

  @Test(expected = ClientNotFoundException.class)
  public void shouldShowClientNotFoundErrorOnUpdate(){
    when(clientRepository.existsById(anyLong())).thenReturn(false);
    clientService.update(new ClientDto(), (long)1);
  }
}
