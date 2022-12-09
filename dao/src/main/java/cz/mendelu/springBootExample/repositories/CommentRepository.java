package cz.mendelu.springBootExample.repositories;

import cz.mendelu.springBootExample.entities.CommentEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    /**
     * method gets all commentEntities
     * @return list of commentEntities
     * @exception NullPointerException, if list is null
     */
    @NonNull
    List<CommentEntity> findAll();


    /**
     * method delete commentEntity by id
     * @param id
     */
    void deleteById(int id);

    /**
     * method save commentEntity
     * @param commentEntity
     * @exception NullPointerException, if commentEntity is null
     */
    @NonNull
    CommentEntity save(@NonNull CommentEntity commentEntity);
}
