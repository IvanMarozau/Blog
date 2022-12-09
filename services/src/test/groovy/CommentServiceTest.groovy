import cz.mendelu.springBootExample.entities.CategoryEntity
import cz.mendelu.springBootExample.entities.CommentEntity
import cz.mendelu.springBootExample.repositories.CommentRepository
import cz.mendelu.springBootExample.services.CategoryService
import cz.mendelu.springBootExample.services.dto.Category
import cz.mendelu.springBootExample.services.dto.Comment
import cz.mendelu.springBootExample.services.CommentService
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification


@EnableAutoConfiguration
@SpringBootTest
class CommentServiceTest extends Specification {

    private CommentService commentService
    private CommentRepository commentRepository


    private static Comment createComment(int id, String text){
        return Comment.builder().id(id).text(text).build()
    }
    private static CommentEntity createCommentEntity(int id, String text){
        return CommentEntity.builder().id(id).text(text).build()
    }

    def setup() {
        commentRepository = Mock()
        commentService = new CommentService(commentRepository)
    }
    def "FindAll"() {
        given:
        List<CommentEntity> listCommentEntity = new ArrayList<>()
        listCommentEntity.add(createCommentEntity(1,"Good"))
        when:
        commentRepository.findAll() >> listCommentEntity
        List<Comment> listComment = commentService.findAll()
        then:
        listCommentEntity.size() == listComment.size()
    }


    def "DeleteCommentById"() {
        when:
        commentService.remove(1)
        then:
        1 * commentRepository.deleteById(1)
    }

    def "SaveComment"() {
        when:
        commentService.save(createComment(1,"Good"))
        then:
        1 * commentRepository.save(createCommentEntity(1,"Good"))
        when:
        commentService.save(null)
        then:
        thrown NullPointerException
        when:
        commentService.findAll().size() > 500
        then:
        thrown IllegalArgumentException
    }
    def "entityToDto"() {
        when:
        CommentService.entityToDto(null)
        then:
        thrown NullPointerException
    }

    def "dtoToEntity"() {
        when:
        CommentService.dtoToEntity(null)
        then:
        thrown NullPointerException
    }
    def "commitRepository"() {
        when:
        commentService = new CommentService(null)
        then:
        thrown NullPointerException
    }
}

