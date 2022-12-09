import cz.mendelu.springBootExample.entities.ArticleEntity
import cz.mendelu.springBootExample.entities.CategoryEntity
import cz.mendelu.springBootExample.repositories.CategoryRepository
import cz.mendelu.springBootExample.services.ArticleService
import cz.mendelu.springBootExample.services.dto.Article
import cz.mendelu.springBootExample.services.dto.Category
import cz.mendelu.springBootExample.services.CategoryService
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDate

@EnableAutoConfiguration
@SpringBootTest
class CategoryServiceTest extends Specification {

    private CategoryService categoryService
    private CategoryRepository categoryRepository


    private static Category createCategory(int id, String name){
        return Category.builder().id(id).name(name).build()
    }
    private static CategoryEntity createCategoryEntity(int id, String name){
        return CategoryEntity.builder().id(id).name(name).build()
    }
    def setup() {
        categoryRepository = Mock()
        categoryService = new CategoryService(categoryRepository)
    }
    def "FindAll"() {
        given:
        List<CategoryEntity> listCategoryEntity = new ArrayList<>()
        listCategoryEntity.add(createCategoryEntity(1,"Sport"))
        when:
        categoryRepository.findAll() >> listCategoryEntity
        List<Category> listCategory = categoryService.findAll()
        then:
        listCategoryEntity.size() == listCategory.size()
    }

    def "FindByCategoryId"() {
        given:
        categoryRepository.findById(1) >>createCategoryEntity(1,"Sport")
        when:
        Category categoryTest = categoryService.get(1)
        then:
        categoryTest == createCategory(1,"Sport")
    }

    def "DeleteCategoryById"() {
        when:
        categoryService.remove(1)
        then:
        1 * categoryRepository.deleteById(1)
    }

    def "SaveCategory"() {
        when:
        categoryService.save(createCategory(1,"Sport"))
        then:
        1 * categoryRepository.save(createCategoryEntity(1,"Sport"))
        when:
        categoryService.save(null)
        then:
        thrown NullPointerException
    }
    def "entityToDto"() {
        when:
        CategoryService.entityToDto(null)
        then:
        thrown NullPointerException
    }

    def "dtoToEntity"() {
        when:
        CategoryService.dtoToEntity(null)
        then:
        thrown NullPointerException
    }
    def "categoryRepository"() {
        when:
        categoryService = new CategoryService(null)
        then:
        thrown NullPointerException
    }
}
