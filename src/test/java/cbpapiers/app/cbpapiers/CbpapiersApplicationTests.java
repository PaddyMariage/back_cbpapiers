package cbpapiers.app.cbpapiers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CbpapiersApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(username = "adrano", roles = {"1"})
    @Test
    void appelGetCustomer_doitRetournerStatut200() throws Exception {
        mvc.perform(get("/customers/adrano")).andExpect(status().isOk());

    }

	@WithMockUser(username = "adrano", roles = {"1"})
	@Test
	void appelGetCustomer_emailDoitEtreEgalA() throws Exception {
		mvc.perform(get("/customers/adrano")).andExpect(jsonPath("$.email").value("contact@adranopizz.fr"));
	}

	@WithMockUser(username = "adrano", roles = {"1"})
	@Test
	void appelGetAllCustomers_tailleDoitEtre748() throws Exception {
		mvc.perform(get("/customers")).andExpect(jsonPath("$", hasSize(748)));
	}

}
