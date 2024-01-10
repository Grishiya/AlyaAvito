package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.Comment;
import ru.skypro.homework.models.UserEntity;

public class CommentMapper {

    public static Comment createOrUpdateCommentDtoInComment(CreateOrUpdateCommentDto commentDto,
                                                            Comment comment) {
        if (commentDto == null) {
            throw new NullPointerException("Tried to map null to Comment");
        }
        comment.setText(commentDto.getText());
        return comment;
    }

    public static CommentDto commentToCommentDto(Comment comment) {
        if (comment == null) {
            throw new NullPointerException("Tried to map null CommentDto");
        }
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthor(comment.getAuthor().getId());
        commentDto.setAuthorFirstName(comment.getAuthorFirstName());
        commentDto.setAuthorImage(comment.getAuthorImage());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setPk(comment.getPkId());
        commentDto.setText(comment.getText());
        return commentDto;
    }
}
