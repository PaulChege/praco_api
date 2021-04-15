package io.chege.praco_api.instrument;

import io.chege.praco_api.PracoApiApplication;
import io.chege.praco_api.instrument.Instrument;
import io.chege.praco_api.instrument.InstrumentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PracoApiApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
class InstrumentControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private InstrumentRepository instrumentRepository;

    @Test
    public void givesListOfInstruments() throws Exception {
        instrumentRepository.save(new Instrument("Clarinet", true));
        mvc.perform(get("/api/v1/instruments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
