package cz.mendelu.springBootExample.controllers;

import cz.mendelu.springBootExample.entities.CategoryEntity;
import cz.mendelu.springBootExample.services.dto.Category;
import cz.mendelu.springBootExample.services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {
  @NonNull
  final private CategoryService categoryService;

  /**
   * Getting list of categories
   * @return web page with list of categories
   */
  @GetMapping("/info")
  public String allCategory(Model model) {
    model.addAttribute("category",categoryService.findAll());
    return "category/all-category";
  }

  /**
   * page for create new category
   * @return web page with a form to create new category
   */
  @GetMapping("/info/add")
  public String addCategory(Model model){
    model.addAttribute("category", Category.builder().build());
    return "category/add-category";
  }

  /**
   * save category
   * @return after save return web page with list of categories
   */
  @PostMapping("/info/add")
  public String add (Category category){
    categoryService.save(category);
    return "redirect:/category/info";
  }



}
