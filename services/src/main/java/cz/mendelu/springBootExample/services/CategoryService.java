package cz.mendelu.springBootExample.services;



import cz.mendelu.springBootExample.entities.CategoryEntity;
import cz.mendelu.springBootExample.repositories.CategoryRepository;

import cz.mendelu.springBootExample.services.dto.Category;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.stream.Collectors;


import java.util.List;


@AllArgsConstructor
public class CategoryService {
   /**
    * Information about category from repository.
    * @exception NullPointerException, if repository is null
    */
   @NonNull
   final private CategoryRepository categoryRepository;

   /**
    * method gets all categories
    * @return list of categories
    * @exception NullPointerException, if list is null
    */
   @NonNull
   public List<Category> findAll() {
      return categoryRepository.findAll().stream().map(CategoryService::entityToDto).collect(Collectors.toList());
   }

   /**
    * method gets category by id
    * @param id
    * @return category
    * @exception NullPointerException, if category is null
    */
   @NonNull
   public Category get(int id){
      return entityToDto(categoryRepository.findById(id));
   }

   /**
    * method delete category by id
    * @param id
    */
   public void remove(int id){
      categoryRepository.deleteById(id);
   }

   /**
    * method save category
    * @param category
    * @exception NullPointerException, if category is null
    */
   public void save(@NonNull Category category){
      categoryRepository.save(dtoToEntity(category));
   }
   /**
    * entita to dto
    * @param categoryEntity
    * @return category
    * @exception NullPointerException, if categoryEntity is null
    */
   @NonNull
   private static Category entityToDto(@NonNull CategoryEntity categoryEntity) {
      return Category.builder()
              .id(categoryEntity.getId())
              .name(categoryEntity.getName())
              .build();
   }

   /**
    * Dto to entita
    * @param category
    * @return categoryEntity
    * @exception NullPointerException, if category is null
    */
   @NonNull
   private static CategoryEntity dtoToEntity(@NonNull Category category) {
      return CategoryEntity.builder()
              .id(category.getId())
              .name(category.getName())
              .build();
   }
}
