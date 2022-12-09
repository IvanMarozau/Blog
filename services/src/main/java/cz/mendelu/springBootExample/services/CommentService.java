package cz.mendelu.springBootExample.services;

import cz.mendelu.springBootExample.entities.CommentEntity;
import cz.mendelu.springBootExample.repositories.CommentRepository;
import cz.mendelu.springBootExample.services.dto.Comment;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CommentService {
    /**
     * Information about comment from repository.
     * @exception NullPointerException, if repository is null
     */
    @NonNull
    final private CommentRepository commentRepository;

    /**
     * method gets all comments
     * @return list of commments
     * @exception NullPointerException, if list is null
     */
    @NonNull
    public List<Comment> findAll() {
        return commentRepository.findAll().stream().map(CommentService::entityToDto).collect(Collectors.toList());
    }


    /**
     * method delete comment by id
     * @param id
     */
    public void remove(int id){
        commentRepository.deleteById(id);
    }

    /**
     * method save comment
     * @param comment
     * @exception NullPointerException, if comment is null
     */
    public void  save(@NonNull Comment comment){
        if(findAll().size()>500)
            throw new IllegalArgumentException("More then 500 comments");
        else
             commentRepository.save(dtoToEntity(comment));
    }

    /**
     * entita to dto
     * @param commentEntity
     * @return comment
     * @exception NullPointerException, if commentEntity is null
     */
    @NonNull
    private static Comment entityToDto(@NonNull CommentEntity commentEntity) {
        return Comment.builder()
                .id(commentEntity.getId())
                .text(commentEntity.getText())
                .build();
    }

    /**
     * Dto to entita
     * @param comment
     * @return commentEntity
     *@exception NullPointerException, if comment is null
     */
    @NonNull
    private static CommentEntity dtoToEntity(@NonNull Comment comment) {
        return CommentEntity.builder()
                .id(comment.getId())
                .text(comment.getText())
                .build();
    }
}
