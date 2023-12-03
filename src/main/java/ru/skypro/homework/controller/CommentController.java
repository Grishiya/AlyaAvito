package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/ads/{adId}/comments")
@CrossOrigin("http://localhost:3000")
public class CommentController {

    @GetMapping
    public CommentsDto getComments(@PathVariable("adId") Integer adId) {
        return new CommentsDto(1,List.of(new CommentDto(
                1, null, "test",
                Instant.now().toEpochMilli(),1, "textComment")));
    }

    @PostMapping
    public CommentDto createComment(
            @PathVariable Integer adId,
            @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return new CommentDto(
                1, null, "test",
                Instant.now().toEpochMilli(), 1, createOrUpdateCommentDto.getText());
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId) {
        return ResponseEntity.ok("OK");
    }

    @PatchMapping("/{commentId}")
    public CommentDto updateComment(
            @PathVariable Integer adId,
            @PathVariable Integer commentId,
            @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return new CommentDto(
                1, "test", "test",
                Instant.now().toEpochMilli(), 1, createOrUpdateCommentDto.getText());
    }
}