package cz.mendelu.springBootExample.controllers;

import cz.mendelu.springBootExample.entities.ArticleEntity;
import cz.mendelu.springBootExample.services.dto.Article;
import cz.mendelu.springBootExample.services.ArticleService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {
   @NonNull
   final private ArticleService articleService;


   /**
    * Getting list of articles
    * @return web page with list of articles
    */
   @GetMapping("/{categoryId}")
   public String allArticle(Model model) {
      model.addAttribute("article",articleService.findAll());
      return "category/articles-category";
   }

   /**
    * page for create new article
    * @return web page with a form to create new article
    */
   @GetMapping("/{categoryId}/add")
   public String addArticle(Model model){
      model.addAttribute("article", Article.builder().build());
      return "article/add-article";
   }

   /**
    * save article
    * @return after save return web page with list of articles
    */
   @PostMapping("/{categoryId}/add")
   public String add (Article article){
      articleService.save(article);
      return "redirect:/article/{categoryId}";
   }

}
