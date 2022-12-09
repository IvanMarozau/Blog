package cz.mendelu.springBootExample.services;

import cz.mendelu.springBootExample.entities.PersonEntity;
import cz.mendelu.springBootExample.repositories.PersonRepository;
import cz.mendelu.springBootExample.services.dto.Person;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class PersonService {
   /**
    * Information about person from repository.
    * @exception NullPointerException, if repository is null
    */
   @NonNull
   final private PersonRepository personRepository;

   /**
    * method gets person by id
    * @param id
    * @return person
    */
   @NonNull
   public Person get (int id){
      return entityToDto(personRepository.findById(id));
   }

   /**
    * method delete person by id
    * @param id
    */
   public void remove(int id){
      personRepository.deleteById(id);
   }

   /**
    * method save person
    * @param person
    * @exception NullPointerException, if personEntity is null
    */
   public void save(@NonNull Person person){
     personRepository.save(dtoToEntity(person));
   }

   /**
    * entita to dto
    * @param personEntity
    * @return person
    * @exception NullPointerException, if personEntity is null
    */
   @NonNull
   private static Person entityToDto(@NonNull PersonEntity personEntity) {
      return Person.builder()
              .id(personEntity.getId())
              .name(personEntity.getName())
              .surname(personEntity.getSurname())
              .build();
   }
   /**
    * Dto to entita
    * @param person
    * @return personEntity
    * @exception NullPointerException, if person is null
    */
   @NonNull
   private static PersonEntity dtoToEntity(@NonNull Person person) {
      return PersonEntity.builder()
              .id(person.getId())
              .name(person.getName())
              .surname(person.getSurname())
              .build();
   }
}
