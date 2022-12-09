package cz.mendelu.springBootExample.controllers

import cz.mendelu.springBootExample.services.dto.Comment
import cz.mendelu.springBootExample.services.CommentService
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
class CommentControllerTest extends Specification {
    @Autowired
    private MockMvc mockMvc
    private CommentController commentController
    private CommentService commentService

    def setup() {
        commentService = Mock()
        commentController = new CommentController(commentService)
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build()
    }


    def "View returned for /comment/{articleId}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build()
        commentService.findAll() >> new ArrayList<>()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/comment/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("comment", new ArrayList<>()))
                .andExpect(MockMvcResultMatchers.view().name("comment/all-comment"))
    }

    def "Method findAll is used in /comment/{articleId}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/comment/1"))
        then:
        1 * commentService.findAll()
    }

    def "View returned for /comment/{articleId}/add"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/comment/1/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("comment", Comment.builder().build()))
                .andExpect(MockMvcResultMatchers.view().name("comment/add-comment"))
    }

   def "Method save is used in /comment/{articleId}/add"() {
       when:
       mockMvc.perform(MockMvcRequestBuilders.post("/comment/1/add"))
       then:
       1 * commentService.save(*_)
   }
    def "articleService"() {
        when:
        commentController = new CommentController(null)
        then:
        thrown NullPointerException
    }

}
