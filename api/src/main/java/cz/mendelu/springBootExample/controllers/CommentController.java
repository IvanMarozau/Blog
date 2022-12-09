package cz.mendelu.springBootExample.controllers;

import cz.mendelu.springBootExample.entities.CommentEntity;
import cz.mendelu.springBootExample.services.dto.Comment;
import cz.mendelu.springBootExample.services.CommentService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    @NonNull
    final private CommentService commentService;

    /**
     * Getting list of comments
     * @return web page with list of comments
     */
    @GetMapping("/{articleId}")
    public String allComment(Model model) {
        model.addAttribute("comment",commentService.findAll());
        return "comment/all-comment";
    }

    /**
     * page for create new comment
     * @return web page with a form to create new comment
     */
    @GetMapping("/{articleId}/add")
    public String addComment(Model model){
        model.addAttribute("comment", Comment.builder().build());
        return "comment/add-comment";
    }

    /**
     * save comment
     * @return after save return web page with list of comments
     */
    @PostMapping("/{articleId}/add")
    public String add (Comment comment){
        commentService.save(comment);
        return "redirect:/comment/{articleId}";
    }
}
