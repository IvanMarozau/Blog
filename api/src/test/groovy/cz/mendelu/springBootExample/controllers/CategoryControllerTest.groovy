package cz.mendelu.springBootExample.controllers

import cz.mendelu.springBootExample.services.dto.Category
import cz.mendelu.springBootExample.services.CategoryService
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
class CategoryControllerTest extends Specification {
    @Autowired
    private MockMvc mockMvc
    private CategoryController categoryController
    private CategoryService categoryService

    def setup() {
        categoryService = Mock()
        categoryController = new CategoryController(categoryService)
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build()
    }

    def "View returned for /category/info"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build()
        categoryService.findAll() >> new ArrayList<>()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/category/info"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("category", new ArrayList<>()))
                .andExpect(MockMvcResultMatchers.view().name("category/all-category"))
    }

    def "Method findAll is used in /category/info"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/category/info"))
        then:
        1 * categoryService.findAll()
    }

    def "View returned for /category/info/add"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/category/info/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("category", Category.builder().build()))
                .andExpect(MockMvcResultMatchers.view().name("category/add-category"))
    }

    def "Method save is used in /category/info/add"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/category/info/add"))
        then:
        1 * categoryService.save(*_)
    }
    def "categoryService"() {
        when:
        categoryController = new CategoryController(null)
        then:
        thrown NullPointerException
    }

}
