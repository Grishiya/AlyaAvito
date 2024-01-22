package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.exception.ActionForbiddenException;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.models.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.security.PermissionChecker;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final AdService adService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, AdService adService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.adService = adService;
    }

    @Override
    public CommentDto createOrUpdateCommentDto(CreateOrUpdateCommentDto comment,
                                               Integer commentId, Integer adId) {
        var ad = adService.getAdEntity(adId);
        Comment commentEntity;
        if (commentId != null && commentId != 0) {
            commentEntity = commentRepository.findById(commentId).orElseThrow(
                    () -> new NoSuchElementException("Comment bot found")
            );
            var user = userService.getUserEntity();
            if (!PermissionChecker.isActionAllowed(user, ad)) throw new ActionForbiddenException();
            commentEntity.setText(comment.getText());
        } else {
            commentEntity = new Comment();
            commentEntity.setCreatedAt(Instant.now());
            commentEntity.setText(comment.getText());
            commentEntity.setAuthor(userService.getUserEntity());
        }
        commentEntity.setAd(ad);
        var save = commentRepository.save(commentEntity);
        return CommentMapper.commentToCommentDto(save);
    }

    @Override
    public CommentsDto getCommentsForAd(Integer idAd) {
        var comment = commentRepository.findByAdId(idAd).stream().map(
                CommentMapper::commentToCommentDto).collect(Collectors.toList());
        return new CommentsDto(comment.size(), comment);
    }

    @Override
    public void delete(Integer commentId, Integer adId) {
        var comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NoSuchElementException("Comment not found")
        );
        var user = userService.getUserEntity();
        if (!PermissionChecker.isActionAllowed(user,comment)) throw new ActionForbiddenException();
        commentRepository.delete(comment);
    }
}
