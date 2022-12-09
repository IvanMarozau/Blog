package cz.mendelu.springBootExample.repositories;

import cz.mendelu.springBootExample.entities.CategoryEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    /**
     * method gets all categoryEntities
     * @return list of categoryEntities
     * @exception NullPointerException, if list is null
     */
    @NonNull
    List<CategoryEntity> findAll();

    /**
     * method find categoryEntity by id
     * @param id
     * @return categoryEntity
     * @exception NullPointerException, if categoryEntity is null
     */
    @NonNull
    CategoryEntity findById(int id);

    /**
     * method delete categoryEntity by id
     * @param id
     */
    void deleteById(int id);

    /**
     * method save categoryEntity
     * @param categoryEntity
     * @exception NullPointerException, if categoryEntity is null
     */
    @NonNull
    CategoryEntity save(@NonNull CategoryEntity categoryEntity);
}
