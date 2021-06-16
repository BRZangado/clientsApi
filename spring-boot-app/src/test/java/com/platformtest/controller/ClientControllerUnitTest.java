package com.platformtest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.platformtest.dto.client.ClientDto;
import com.platformtest.error.exception.EmptyBaseException;
import com.platformtest.model.Client;
import com.platformtest.service.ClientService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientControllerUnitTest extends AbstractControllerTest{

  @InjectMocks
  private ClientController clientController;

  @Mock
  private ClientService service;

  private ObjectMapper mapper;

  @Before
  public void setUp() {
    this.setUp(clientController);
    mapper = new ObjectMapper();
  }

  @Test
  public void shouldSuccessfullyGetClients() throws Exception {

    List<Client> clientList = new ArrayList<>();
    clientList.add(generateDefaultClient());

    when(service.findAll(anyInt(), anyInt())).thenReturn(clientList);

    mockMvc.perform(get("/client"))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldGetEmptyDatabaseException() throws Exception {
    when(service.findAll(anyInt(), anyInt())).thenThrow(EmptyBaseException.class);

    mockMvc.perform(get("/client"))
        .andExpect(status().is(422))
        .andReturn();
  }

  @Test
  public void shouldCreateNewClient() throws Exception{
    ClientDto dto = generateDefaultClientDTO();
    Client client = generateDefaultClient();
    when(service.create(any())).thenReturn(client);

    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson=ow.writeValueAsString(dto);

    mockMvc.perform(post("/client")
        .contentType("application/json")
        .content(requestJson))
        .andExpect(status().isCreated());
  }

  @Test
  public void shouldGetIncorrectDataException() throws Exception{
    ClientDto dto = new ClientDto();

    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson=ow.writeValueAsString(dto);

    mockMvc.perform(post("/client")
        .contentType("application/json")
        .content(requestJson))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void shouldSuccessfullyGetClientById() throws Exception {

    Client client = generateDefaultClient();

    when(service.find(anyLong())).thenReturn(client);

    mockMvc.perform(get("/client/1"))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  public void shouldCreateUpdateClient() throws Exception{
    ClientDto dto = generateDefaultClientDTO();
    Client client = generateDefaultClient();
    when(service.update(any(), anyLong())).thenReturn(client);

    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    String requestJson=ow.writeValueAsString(dto);

    mockMvc.perform(put("/client/1")
        .contentType("application/json")
        .content(requestJson))
        .andExpect(status().isOk());
  }


  @Test
  public void shouldDeleteClientById() throws Exception {
    mockMvc.perform(delete("/client/1"))
        .andExpect(status().isOk())
        .andReturn();
  }

  private ClientDto generateDefaultClientDTO(){
    return ClientDto.builder()
        .age(10)
        .name("Bruno")
        .build();
  }

  private Client generateDefaultClient(){
    return Client.builder()
        .age(10)
        .name("Bruno")
        .id((long)1)
        .build();
  }
}
