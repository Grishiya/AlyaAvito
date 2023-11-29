package ru.skypro.homework.mappers;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.Comment;
import ru.skypro.homework.models.UserEntity;

public class CommentMapper {

    public static Comment toComment(CommentDto commentDto, UserEntity author, Ad ad) {
        if (commentDto == null) {
            throw new NullPointerException("Tried to map null to Comment");
        }

        Comment comment = new Comment();

        comment.setAuthor(author);
        comment.setAuthorImage(commentDto.getAuthorImage());
        comment.setAuthorFirstName(commentDto.getAuthorFirstName());
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setPkId(commentDto.getPkId());
        comment.setText(commentDto.getText());
        comment.setAd(ad);
        return comment;
    }

    public static CommentDto fromComment(Comment comment) {
        if (comment == null) {
            throw new NullPointerException("Tried to map null to CommentDto");
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setPkId(comment.getPkId());
        commentDto.setAuthorId(comment.getAuthor().getId());
        commentDto.setAuthorImage(comment.getAuthorImage());
        commentDto.setAuthorFirstName(comment.getAuthorFirstName());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setText(comment.getText());

        return commentDto;
    }
}
