package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.mappers.CommentMapper;
import ru.skypro.homework.models.Ad;
import ru.skypro.homework.models.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final AdService adService;
    private final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);


    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, AdService adService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.adService = adService;
    }
    /**
     * The method create or update Comment and save it in the database
     *
     * @param comment, commentId, adId
     * @return commentEntity
     * @throws NoSuchElementException Comment bot found
     */
    @Override
    public CommentDto createOrUpdateCommentDto(CreateOrUpdateCommentDto comment,
                                               Integer commentId, Integer adId) {
        logger.info("The createOrUpdateCommentDto method was called with data" + comment + "," + commentId + "and" + adId);

        var ad = adService.getAdEntity(adId);
        Comment commentEntity = null;
        if (commentId != null && commentId != 0) {
            commentEntity = commentRepository.findById(commentId).orElseThrow(
                    () -> new NoSuchElementException("Comment bot found")
            );
        }
            if (commentEntity ==  null) {
                commentEntity= new Comment();
                commentEntity.setCreatedAt(LocalDateTime.now().
                        toEpochSecond(ZoneId.systemDefault().getRules().getOffset(LocalDateTime.
                                now()))*1000);
            }
            commentEntity.setAd(ad);
            commentEntity.setAuthor(userService.getUserEntity(1));
            commentEntity = CommentMapper.createOrUpdateCommentDtoInComment(comment, commentEntity);
            commentEntity.setPkId(adId);
            var save = commentRepository.save(commentEntity);
            return CommentMapper.commentToCommentDto(save);

    }
    /**
     * The method get comment for Ad and collect to list
     *
     * @param idAd
     * @return commentsDto
     */
    @Override
    public CommentsDto getCommentsForAd(Integer idAd) {
        logger.info("The getCommentsForAd method was called with data" + idAd);

        var comment = commentRepository.findByAdId(idAd).stream().map(
                CommentMapper::commentToCommentDto).collect(Collectors.toList());
        return new CommentsDto(comment.size(), comment);
    }
    /**
     * The method update comment and save to database
     *
     * @param comment
     * @return comment
     * @throws NoSuchElementException This comment not found
     */
    public Comment update(Comment comment) {
        Optional<Comment> check = commentRepository.findById(comment.getAuthor().getId());
        if (check.isEmpty()) {
            throw new NoSuchElementException("This comment not found");
        }

        return commentRepository.save(comment);
    }
    /**
     * The method delete comment
     *
     * @param commentId, adId
     * @throws NoSuchElementException Comment not found
     */
    @Override
    public void delete(Integer commentId, Integer adId) {
        logger.info("The delete method was called with data" + commentId + "and" + adId);

        var comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NoSuchElementException("Comment not found")
        );

        commentRepository.delete(comment);
    }
}
