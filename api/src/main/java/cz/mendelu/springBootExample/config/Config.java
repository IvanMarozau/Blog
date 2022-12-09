package cz.mendelu.springBootExample.config;

import cz.mendelu.springBootExample.controllers.*;
import cz.mendelu.springBootExample.repositories.*;
import cz.mendelu.springBootExample.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ArticleService articleService (ArticleRepository articleRepository){
        return new ArticleService(articleRepository);
    }
    @Bean
    public CategoryService categoryService (CategoryRepository categoryRepository){
        return new CategoryService(categoryRepository);
    }
    @Bean
    public CommentService commentService (CommentRepository commentRepository){
        return new CommentService(commentRepository);
    }
    @Bean
    public PersonService personService (PersonRepository personRepository){
        return new PersonService(personRepository);
    }
    @Bean
    public ArticleController articleController (ArticleService articleService){
        return new ArticleController(articleService);
    }
    @Bean
    public CategoryController categoryController (CategoryService categoryService){
        return new CategoryController(categoryService);
    }
   @Bean
   public CommentController commentController (CommentService commentService){
        return new CommentController(commentService);
    }

    @Bean
    public HomeController homeController() {
        return new HomeController();
    }


}
