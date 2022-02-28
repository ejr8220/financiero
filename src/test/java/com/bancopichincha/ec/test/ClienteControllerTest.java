package com.bancopichincha.ec.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;

import com.bancopichincha.ec.entities.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;

	@Test
	@DisplayName("GET /api/clientes success")
	void testGetAll() throws Exception {
		mockMvc.perform(get("/api/clientes"))
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		       .andExpect(jsonPath("$[0].nombre", is("Edgar Pin")));
	}
	@Test
	@DisplayName("POST /api/clientes cliente ya existe")
	void testNewClienteIsExists() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setNombre("Prueba Unitaria");
		cliente.setGenero("Masculino");
		cliente.setDireccion("dirccio virtual");
		cliente.setEstado("Activo");
		cliente.setFeNacimiento(Date.valueOf("2022-02-26"));
		cliente.setFeCreacion(Date.valueOf("2022-02-26"));
		cliente.setClave("zzz");
		cliente.setIdentificacion("0011223344");
		cliente.setTelefono("0922443311");
		cliente.setIpCreacion("127.0.0.1");
		cliente.setUsuarioCreacion("epin");
		
		String clienteJson = mapper.writeValueAsString(cliente);
		
        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clienteJson))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is("1")))
                .andExpect(jsonPath("$.message", is("Cliente ya Existe")));
	}
}
