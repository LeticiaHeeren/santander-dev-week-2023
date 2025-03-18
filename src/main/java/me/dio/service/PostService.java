package me.dio.service;

import me.dio.domain.model.Post;

public interface PostService {
    Post create(Post post);
    void likePost(Long postId, Long userId);
}