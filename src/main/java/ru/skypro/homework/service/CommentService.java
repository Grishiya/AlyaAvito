package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.models.Comment;

public interface CommentService {
    CommentsDto getCommentsForAd(Integer adId);

    CommentDto createOrUpdateCommentDto(CreateOrUpdateCommentDto comment,
                                        Integer commentId, Integer adId);

    void delete(Integer commentId, Integer adId);
}
