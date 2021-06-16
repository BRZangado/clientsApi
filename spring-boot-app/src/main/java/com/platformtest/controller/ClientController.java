package com.platformtest.controller;

import com.platformtest.dto.client.ClientDto;
import com.platformtest.model.Client;
import com.platformtest.service.ClientService;
import com.platformtest.service.ClientServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/client")
@Validated
@Api(value = "Clients API", tags = {"clients"})
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @ApiOperation(
        value = "Create clients",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Client Created"),
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Client> createClient(@Valid @RequestBody @NotNull final ClientDto client) {
        log.info("Received client creation request");
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(client));
    }

    @ApiOperation(
        value = "GET clients",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retrieved Clients"),
    })
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<List<Client>> getClients(
        @RequestParam(defaultValue = "0") final Integer page,
        @RequestParam(defaultValue = "10") final Integer size){
        log.info("Received client get request");
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(page, size));
    }

    @ApiOperation(
        value = "GET client",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retrieved Client"),
    })
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Client> getClientById(@PathVariable("id") final Long id){
        log.info("Received client get by id request");
        return ResponseEntity.status(HttpStatus.OK).body(clientService.find(id));
    }

    @ApiOperation(
        value = "Update client",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Client Updated"),
    })
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Client> update(
        @Valid @RequestBody @NotNull final ClientDto client,
        @PathVariable("id") final Long id){
        log.info("Received client update by id request");
        return ResponseEntity.status(HttpStatus.OK).body(clientService.update(client, id));
    }

    @ApiOperation(
        value = "Update client",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Client Deleted"),
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity delete(@PathVariable("id") final Long id){
        log.info("Received client delete by id request");
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
