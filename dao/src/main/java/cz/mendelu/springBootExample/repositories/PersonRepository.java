package cz.mendelu.springBootExample.repositories;


import cz.mendelu.springBootExample.entities.PersonEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    /**
     * method find personEntity by id
     * @param id
     * @return personEntity
     */
    @NonNull
    PersonEntity findById(int id);

    /**
     * method delete personEntity by id
     * @param id
     */
    void deleteById(int id);

    /**
     * method save personEntity
     * @param person
     * @exception NullPointerException, if personEntity is null
     */
    @NonNull
    PersonEntity save(@NonNull PersonEntity person);

}
