import cz.mendelu.springBootExample.entities.CommentEntity
import cz.mendelu.springBootExample.entities.PersonEntity
import cz.mendelu.springBootExample.repositories.PersonRepository
import cz.mendelu.springBootExample.services.CommentService
import cz.mendelu.springBootExample.services.dto.Comment
import cz.mendelu.springBootExample.services.dto.Person
import cz.mendelu.springBootExample.services.PersonService
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification


@EnableAutoConfiguration
@SpringBootTest
class PersonServiceTest extends Specification {
    private PersonService personService
    private PersonRepository personRepository


    private static Person createPerson(int id, String name, String surname){
        return Person.builder().id(id).name(name).surname(surname).build()
    }
    private static PersonEntity createPersonEntity(int id, String name, String surname){
        return PersonEntity.builder().id(id).name(name).surname(surname).build()
    }
    def setup() {
        personRepository = Mock()
        personService = new PersonService(personRepository)
    }

    def "FindByPersonId"() {
        given:
        personRepository.findById(1) >> createPersonEntity(1,"Ivan", "Marozau")
        when:
        Person personTest = personService.get(1)
        then:
        personTest ==  createPerson(1,"Ivan", "Marozau")
    }

    def "DeletePersonById"() {
        when:
        personService.remove(1)
        then:
        1 * personRepository.deleteById(1)
    }

    def "SavePerson"() {
        when:
        personService.save( createPerson(1,"Ivan", "Marozau"))
        then:
        1 * personRepository.save( createPersonEntity(1,"Ivan", "Marozau"))
        when:
        personService.save(null)
        then:
        thrown NullPointerException
    }

    def "entityToDto"() {
        when:
        PersonService.entityToDto(null)
        then:
        thrown NullPointerException
    }

    def "dtoToEntity"() {
        when:
        PersonService.dtoToEntity(null)
        then:
        thrown NullPointerException
    }
    def "personRepository"() {
        when:
        personService = new PersonService(null)
        then:
        thrown NullPointerException
    }
}
