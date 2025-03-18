package me.dio.controller;

import me.dio.domain.model.Post;
import me.dio.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) {
        Post createdPost = postService.create(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPost.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdPost);
    }

    @PostMapping("/{postId}/like/{userId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId, @PathVariable Long userId) {
        postService.likePost(postId, userId);
        return ResponseEntity.noContent().build();
    }
}