package test.decathlon.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import test.decathlon.data.TestData;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class CalculateControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void calcResultsCheckSuccess() throws Exception {
        String jsonBody = TestData.load("success.json");
        MockHttpServletRequestBuilder request = post("/calc/calc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].totalPoint", is(9126)))
                .andExpect(jsonPath("$[1].totalPoint", is(9045)))
        ;
    }

    @Test
    void calcResultsCheck400() throws Exception {
        check400(TestData.load("less_zero.json"));
        check400(TestData.load("nine_event.json"));
    }

    private void check400(String jsonBody) throws Exception {
        MockHttpServletRequestBuilder request = post("/calc/calc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
        ;
    }
}