package com.baeldung.springbootreact.controller;

import com.baeldung.springbootreact.domain.Client;
import com.baeldung.springbootreact.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClientsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientsController clientsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientsController).build();
    }

    @Test
    public void testGetAllClients() throws Exception {
        when(clientRepository.findAll()).thenReturn(Arrays.asList(new Client(), new Client()));

        mockMvc.perform(get("/clients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists());
    }

    @Test
    public void testGetClientById() throws Exception {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(new Client()));

        mockMvc.perform(get("/clients/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testCreateClient() throws Exception {
        when(clientRepository.save(any(Client.class))).thenReturn(new Client());

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateClient() throws Exception {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(new Client()));
        when(clientRepository.save(any(Client.class))).thenReturn(new Client());

        mockMvc.perform(put("/clients/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John Doe\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteClient() throws Exception {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(new Client()));

        mockMvc.perform(delete("/clients/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
