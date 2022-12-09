package cz.mendelu.springBootExample.repositories;

import cz.mendelu.springBootExample.entities.ArticleEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    /**
     * method find articleEntity by id
     * @param id
     * @return  articleEntity
     * @exception NullPointerException, if articleEntity is null
     */
    @NonNull
    ArticleEntity findById(int id);

    /**
     * method delete articleEntity by id
     * @param id
     */
    void deleteById(int id);

    /**
     * method save  article
     * @param articleEntity
     * @exception NullPointerException, if articleEntity is null
     */
    @NonNull
    ArticleEntity save(@NonNull ArticleEntity articleEntity);

    /**
     * method gets all articleEntities
     * @return list of articleEntities
     * @exception NullPointerException, if list is null
     */
    @NonNull
    List<ArticleEntity> findAll();
}
