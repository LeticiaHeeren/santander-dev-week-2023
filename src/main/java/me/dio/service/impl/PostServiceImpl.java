package me.dio.service.impl;

import me.dio.domain.model.*;
import me.dio.domain.repository.PostRepository;
import me.dio.domain.repository.UserRepository;
import me.dio.service.PostService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void likePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(NoSuchElementException::new);
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        Like like = new Like();
        like.setPost(post);
        like.setUser(user);
        post.getLikes().add(like);
        postRepository.save(post);
    }
}