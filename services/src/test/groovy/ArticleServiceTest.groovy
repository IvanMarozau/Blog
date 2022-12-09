


import cz.mendelu.springBootExample.entities.ArticleEntity
import cz.mendelu.springBootExample.repositories.ArticleRepository
import cz.mendelu.springBootExample.services.dto.Article
import cz.mendelu.springBootExample.services.ArticleService
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDate


@EnableAutoConfiguration
@SpringBootTest
class ArticleServiceTest extends Specification {

    private ArticleService articleService
    private ArticleRepository articleRepository

    private static Article createArticle(int id, String name, String text, String date){
        return Article.builder().id(id).name(name).text(text).date(date).build()
    }
    private static ArticleEntity createArticleEntity(int id, String name, String text, LocalDate date){
        return ArticleEntity.builder().id(id).name(name).text(text).date(date).build()
    }

    def setup() {
        articleRepository = Mock()
        articleService = new ArticleService(articleRepository)
    }

    def "FindAll"() {
        given:
        List<ArticleEntity> listArticleEntity = new ArrayList<>()
        listArticleEntity.add(createArticleEntity(1,"Football", "3:0",LocalDate.of(2044,9,11)))
        when:
        articleRepository.findAll() >> listArticleEntity
        List<Article> listArticle = articleService.findAll()
        then:
        listArticleEntity.size() == listArticle.size()
    }

    def "FindByIdArticle"() {
        given:
        articleRepository.findById(1) >> createArticleEntity(1,"Football", "3:0",LocalDate.of(2044,9,11))
        when:
        Article articleTest = articleService.get(1)
        then:
        articleTest == createArticle(1,"Football", "3:0", "2044-09-11")
    }

    def "DeleteByIdArticle"() {
        given:
        ArticleRepository articleRepository = Mock()
        ArticleService articleService = new ArticleService(articleRepository)
        when:
        articleService.remove(1)
        then:
        1 * articleRepository.deleteById(1)
    }

    def "SaveArticle"() {
        when:
        articleService.save(createArticle(1,"Football", "3:0", "2044-09-11"))
        then:
        1 * articleRepository.save(createArticleEntity(1,"Football", "3:0",LocalDate.of(2044,9,11)))
        when:
        articleService.save(null)
        then:
        thrown NullPointerException
        when:
        articleService.save(createArticle(1,"Football", "3:0", "2019-09-11"))
        then:
        thrown IllegalArgumentException
    }

    def "entityToDto"() {
        when:
        ArticleService.entityToDto(null)
        then:
        thrown NullPointerException
    }

    def "dtoToEntity"() {
        when:
        ArticleService.dtoToEntity(null)
        then:
        thrown NullPointerException
    }
    def "articleRepository"() {
        when:
        articleService = new ArticleService(null)
        then:
        thrown NullPointerException
    }
}
