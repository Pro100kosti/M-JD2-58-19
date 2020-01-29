package by.pvt;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.ScenarioState;
import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ContextConfiguration(classes = TestHelloWebMvcConfiguration.class)
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class SearchControllerTest extends
        ScenarioTest<SearchControllerTest.Given,
                SearchControllerTest.When, SearchControllerTest.Then> {

    @Autowired
    WebApplicationContext webApplicationContext;

    static class Given extends Stage<Given> {

        @ScenarioState
        WebApplicationContext webApplicationContext;

        @ScenarioState
        MockMvc mockMvc;

        @ProvidedScenarioState
        String search;

        @As("web application")
        Given runWith(WebApplicationContext wac) {
            webApplicationContext = wac;
            return self();
        }

        Given userEnterEmptySearchString(String searchStr) {
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            search = searchStr;
            return self();
        }

        Given userEnterCorrectSearchString(String searchStr) {
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            search = searchStr;
            return self();
        }
    }

    static class When extends Stage<When> {
        @ScenarioState
        MockMvc mockMvc;

        @ExpectedScenarioState
        String search;

        @ProvidedScenarioState
        ModelAndView modelAndView;

        When executeSearch() throws Exception {
            modelAndView = mockMvc
                    .perform(get("/search").param("search-str", search))
                    .andReturn().getModelAndView();
            return when();
        }
    }

    static class Then extends Stage<Then> {
        @ExpectedScenarioState
        ModelAndView modelAndView;

        Then resultSizeIs(int size) {
            assertNotNull(modelAndView);
            assertEquals("searchResult", modelAndView.getViewName());
            assertTrue(modelAndView.getModel().containsKey("result"));
            List list = (List) modelAndView.getModel().get("result");
            assertEquals(size, list.size());
            return self();
        }
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void search() throws Exception {
        given().runWith(webApplicationContext).userEnterEmptySearchString("");
        when().executeSearch();
        then().resultSizeIs(0);
    }

    @Test
    @Sql(value = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/test-data-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void searchWithParam() throws Exception {
        given().runWith(webApplicationContext).userEnterCorrectSearchString("test");
        when().executeSearch();
        then().resultSizeIs(2);
    }
}