package cz.mendelu.springBootExample.controllers

import cz.mendelu.springBootExample.services.dto.Article
import cz.mendelu.springBootExample.services.ArticleService
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
class ArticleControllerTest extends Specification {
    @Autowired
    private MockMvc mockMvc
    private ArticleService articleService
    private ArticleController articleController

    def setup() {
        articleService = Mock()
        articleController = new ArticleController(articleService)
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build()
    }

    def "View returned for /article/{categoryId}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build()
        articleService.findAll() >> new ArrayList<>()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/article/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("article", new ArrayList<>()))
                .andExpect(MockMvcResultMatchers.view().name("category/articles-category"))
    }

    def "Method findAll is used in /article/{categoryId}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/article/1"))
        then:
        1 * articleService.findAll()
    }

    def "View returned for /article/{categoryId}/add"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/article/1/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("article", Article.builder().build()))
                .andExpect(MockMvcResultMatchers.view().name("article/add-article"))
    }

   def "Method save is used in /article/{categoryId}/add"() {
       when:
       mockMvc.perform(MockMvcRequestBuilders.post("/article/1/add"))
       then:
       1 * articleService.save(*_)
   }
    def "articleService"() {
        when:
        articleController = new ArticleController(null)
        then:
        thrown NullPointerException
    }
}
