package cz.mendelu.springBootExample.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification


@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration
class HomeControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc
    private HomeController homeController = new HomeController()

    def "View returned for /"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("person/person"))
    }
}
