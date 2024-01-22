package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.CommentService;


@RestController
@RequestMapping("/ads/{adId}/comments")
@CrossOrigin("http://localhost:3000")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public CommentsDto getComments(@PathVariable("adId") int adId) {
        return commentService.getCommentsForAd(adId);
    }

    @PostMapping
    public CommentDto createComment(
            @PathVariable("adId") int adId,
            @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return commentService.createOrUpdateCommentDto(createOrUpdateCommentDto, 0, adId);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId) {
        commentService.delete(commentId,adId);
        return ResponseEntity.ok("OK");
    }

    @PatchMapping("/{commentId}")
    public CommentDto updateComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId,
            @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return commentService.createOrUpdateCommentDto(createOrUpdateCommentDto,commentId,adId);
    }
}