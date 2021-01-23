package com.recipesbook.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.recipesbook.Domain.Comment;
import com.recipesbook.Domain.Recipe;
import com.recipesbook.Domain.User;
import com.recipesbook.Dto.CommentsDto;


@Mapper(componentModel = "spring")
public interface CommentMapper {
	
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target ="recipe", source = "recipe" )
    @Mapping(target = "user", source = "user")
	Comment mapCommentDtoToCommentEntity(CommentsDto commentsDto, Recipe recipe, User user);

    
    
    @Mapping(target = "recipeId", expression  = "java(comment.getRecipe().getId())")
    @Mapping(target = "userName", expression  = "java(comment.getUser().getUsername())" )
	CommentsDto mapCommentEntityToCommentDto(Comment comment);
}
