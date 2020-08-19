package cbpapiers.app.cbpapiers;


import cbpapiers.app.cbpapiers.model.Customer;
import cbpapiers.app.cbpapiers.model.Order;
import cbpapiers.app.cbpapiers.model.TopArticleCustomer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class CbpapiersApplicationTests {

//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    private ObjectMapper mapper;
//
//    private MockMvc mvc;
//
//    private static Customer customer;
//    private static String token;
//
//    @BeforeEach
//    void setup() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }
//
//    /****************
//     * Partie Paddy *
//     ****************/
//
//    @WithMockUser(username = "adrano", roles = {"1"})
//    @Test
//    void appelGetCustomer_doitRetournerStatut200() throws Exception {
//        mvc.perform(get("/customers/adrano")).andExpect(status().isOk());
//
//    }
//
//    @WithMockUser(username = "adrano", roles = {"1"})
//    @Test
//    void appelGetCustomer_emailDoitEtreEgalA() throws Exception {
//        mvc.perform(get("/customers/adrano")).andExpect(jsonPath("$.email").value("contact@adranopizz.fr"));
//    }
//
//    @WithMockUser(username = "adrano", roles = {"1"})
//    @Test
//    void appelGetAllCustomers_tailleDoitEtre748() throws Exception {
//        mvc.perform(get("/customers")).andExpect(jsonPath("$", hasSize(748)));
//    }
//
//    /*****************
//     * Partie Adrien *
//     *****************/
//
//    //on set notre customer valide
//    @BeforeAll
//    public static void setCustomer() {
//        customer = new Customer();
//        customer.setId("ADRANO");
//        customer.setPassword("test");
//    }
//
//    @Test
//    public void A_Auth_mustReturnJWT_and_testRequestWithJWT_mustSucceed() throws Exception {
//        // on convertit notre customer en JSON
//        String body = mapper.writeValueAsString(customer);
//
//        // on dit au MockMvC de faire une requête post
//        token = mvc.perform(
//                post("/customers/authentification")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(body)
//        )
//                // on attend en réponse un status 200 et un JWT en retour
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        System.out.println("Le JWT : " + token);
//
//        // testons le JWT
//        int dotCount = 0;
//        for (char c : token.toCharArray())
//            if (c == '.')
//                dotCount++;
//
//        String[] tokenParts = token.split("\\.");
//
//        // si c'est un JWT il devrait contenir 3 parties donc 2 points séparateur.
//        System.out.println("nombre de points séparateur :" + dotCount);
//        assertEquals(2, dotCount);
//        System.out.println("nombre de parties : " + tokenParts.length);
//        assertEquals(3, tokenParts.length);
//
//        // on pourrait tester plus intensément le JWT en définissant notre header et notre payload
//        // car ils sont connus puis en les encodant en base64
//        // et en refaisant la signature afin de comparer directement le retour de la version attendue.
//
//        // on dit au MockMvc de faire un requête get pour choper les informations du customer
//        // avec authentification et autorisation via JWT
//        mvc.perform(
//                get("/customers/" + customer.getId())
//                        .header("authorization", "Bearer " + token) // si on commente cette ligne, la requête ne réussit plus
//                //en effet on ne met plus le token, il est nécessaire
//        )
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void B_testGetTopArticleList_mustSucceed_and_mustGetListOf62Articles() throws Exception {
//        String body = mapper.writeValueAsString(customer);
//        String jsonResponseAsString = mvc.perform(
//                get("/toparticles/customer/" + customer.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(body)
//                        .header("authorization", "Bearer " + token)
//        )
//                .andExpect(status().isOk())
//                //on récup la réponse sous forme de string
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        System.out.println("Mon JSON en String \n" + jsonResponseAsString);
//
//        // on utilise la librairie Gson (faut la dépendance dans le POM) car c'est plus simple d'utilisation
//        Gson gson = new Gson();
//
//        // 2 façons de récupérer une liste d'objets
//        // gson.fromJson(JsonAsString, MyModel[].class)
//        TopArticleCustomer[] topArticleListFacile = gson.fromJson(jsonResponseAsString, TopArticleCustomer[].class);
//        System.out.println("nombre d'articles : " + topArticleListFacile.length);
//        assertEquals(62, topArticleListFacile.length);
//
//        // elle est plus compliquée mais apparemment est meilleure
//        // on définit d'abord un type de collection
//        // qu'on applique en tant que modèle dans gson.fromJson(JsonAsString, collectionType)
//        // et on peut récupérer des List au lieu de Array[] par exemple.
//        Type collectionType = new TypeToken<Collection<TopArticleCustomer>>(){}.getType();
//        List<TopArticleCustomer> topArticleList = gson.fromJson(jsonResponseAsString, collectionType);
//        System.out.println("nombre d'articles : " + topArticleList.size());
//        assertEquals(62, topArticleList.size());
//
//    }
//
//    @Test
//    public void C_testRequestTypePost_mustFail() throws Exception {
//        String body = mapper.writeValueAsString(customer);
//
//        // on fait un post sur une requête qui n'accepte que du get
//        mvc.perform(
//                post("/toparticles/customer/" + customer.getId())
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(body)
//                        .header("authorization", "Bearer " + token)
//        )
//                // on attend un status 200 mais ça doit échouer
//                .andExpect(status().isOk());
//        // le test s'arrêtera ici, on ne pourra pas récupérer la réponse
//    }
//
//
//    @Test
//    public void D_testRequestType_mustSucceed_and_mustReturnStatus405() throws Exception {
//        String body = mapper.writeValueAsString(customer);
//
//        // on fait un post sur une requête qui n'accepte que du get
//        int statusResponse = mvc.perform(
//                post("/toparticles/customer/" + customer.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(body)
//                        .header("authorization", "Bearer " + token)
//        )
//                .andReturn()
//                .getResponse()
//                .getStatus();
//
//        System.out.println("Status error: " + statusResponse);
//        assertEquals(405, statusResponse);
//    }
//
//    @Test
//    public void E_testGetOneOrder_mustSucceed() throws Exception {
//        String body = mapper.writeValueAsString(customer);
//
//        String jsonResponse = mvc.perform(
//                get("/orders/MOBI8724")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(body)
//                        .header("authorization", "Bearer " + token)
//        )
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
////        System.out.println(jsonResponse);
////        Gson gson = new Gson();
////        Order order = gson.fromJson(jsonResponse, Order.class);
////        System.out.println(order.toString());
////        System.out.println(order.getOrderLines().size());
////        System.out.println(order.getOrderLines().toString());
//    }
//
//    void contextLoads() throws Exception {
//        //attention à chaque nouveau test : changer le login de l'utilisateur sinon ca marche pas : utiliser math.random
//        User user = new User();
//        user.setLogin("testauthentification4");
//        user.setPassword("testmdp");
//        String json = mapper.writeValueAsString(user);


//		//vérifier si ca accepte et si ca contient un JSON
//		mvc.perform(put("/users")
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)
//				.content(json))
//				//on vérifie que ca contient du JSON
////				.contentType(MediaType.APPLICATION_JSON))
//				//on vérifie le bon statut de la requete :200
//				.andExpect(status().isOk());

        //on teste le contenu du json  : on veut vérifier qu'on récupère bien le bon utilisateur : avec le bon login
//		mvc.perform(get("/users"))
//				.andExpect(MockMvcResultMatchers
//				.jsonPath("$[0].login")
//				.value("ju")
//				);

        //on vérifie le contenu d'un json pour un utilisateur en particulier
//		mvc.perform(get("/users/3"))
//				.andExpect(MockMvcResultMatchers
//						.jsonPath("$.login")
//						.value("ju")
//				);

        //partie Justine


            @Autowired
            private WebApplicationContext context;

            @Autowired
            private ObjectMapper mapper;

            private MockMvc mvc;

            //on met pas en static car sinon il n'arrive pas à faire l'autowired donc pas de beforeAll
            @BeforeEach
            public void beforeEach(){
                mvc= MockMvcBuilders
                        //récupération du contexte de notre application : les dao...
                        .webAppContextSetup(context)
                        .apply(springSecurity())
                        .build();
            }

            @Test
            void contextLoads() throws Exception {
                Customer customer = new Customer();
                customer.setId("ADRANO");
                customer.setPassword("test");
                String json = mapper.writeValueAsString(customer);

        String token = mvc.perform(
                post("/customers/authentification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(token);

        mvc.perform(get("/articles")
//                .header("authorization","Bearer "+ token)
        )
                .andExpect(status().isOk());
    }

}

//}
