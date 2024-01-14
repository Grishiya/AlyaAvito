package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.models.Comment;

public class CommentMapper {

    public static Comment createOrUpdateCommentDtoInComment(CreateOrUpdateCommentDto commentDto) {
        if (commentDto == null) {
            throw new NullPointerException("Нельзя добавлять пустой комментарий");
        }
        return new Comment();
    }

    public static CommentDto commentToCommentDto(Comment comment) {
        if (comment == null) {
            throw new NullPointerException("Tried to map null CommentDto");
        }
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthor(comment.getAuthor().getId());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setPk(comment.getId());
        commentDto.setText(comment.getText());
        return commentDto;
    }
}
