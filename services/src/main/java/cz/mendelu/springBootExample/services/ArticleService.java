package cz.mendelu.springBootExample.services;

import cz.mendelu.springBootExample.entities.ArticleEntity;
import cz.mendelu.springBootExample.repositories.ArticleRepository;
import cz.mendelu.springBootExample.services.dto.Article;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
public class ArticleService {
   static final private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
   /**
    * Information about article from repository.
    * @exception NullPointerException, if repository is null
    */
   @NonNull
   final private ArticleRepository articleRepository;

   /**
    * method gets all articles
    * @return list of articles
    * @exception NullPointerException, if list is null
    */
   @NonNull
   public List<Article> findAll() {
      return articleRepository.findAll().stream().map(ArticleService::entityToDto).collect(Collectors.toList());
   }
   /**
    * method gets article by id
    * @param id
    * @return  article
    * @exception NullPointerException, if article is null
    */

   @NonNull
   public Article get(int id){
         return entityToDto(articleRepository.findById(id));
   }
   /**
    * method delete article by id
    * @param id
    */

   public void remove (int id){
      articleRepository.deleteById(id);
   }
   /**
    * method save  article
    * @param article
    * @return
    * @exception NullPointerException, if article is null
    */
   public void  save (@NonNull Article article){
      LocalDate theDate = LocalDate.parse(article.getDate() , formatter);
      if(theDate.compareTo(LocalDate.now())<0)
         throw new IllegalArgumentException("article date before current date");
      else
         articleRepository.save(dtoToEntity(article));
   }

   /**
    * entita to dto
    * @param articleEntity
    * @return article
    * @exception NullPointerException, if articleEntity is null
    */
   @NonNull
   private static Article entityToDto(@NonNull ArticleEntity articleEntity){
      return Article.builder()
              .id(articleEntity.getId())
              .name(articleEntity.getName())
              .text(articleEntity.getText())
              .date(articleEntity.getDate().toString())
              .build();
   }

   /**
    * Dto to entita
    * @param article
    * @return articleEntity
    * @exception NullPointerException, if article is null
    */
   @NonNull
   private static ArticleEntity dtoToEntity(@NonNull Article article) {
      return ArticleEntity.builder()
              .id(article.getId())
              .name(article.getName())
              .text(article.getText())
              .date(LocalDate.parse(article.getDate() , formatter))
              .build();
   }
}





