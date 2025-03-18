package me.dio;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // Usa o perfil de teste (configurações específicas para testes)
class SantanderDevWeek2023ApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
        // Verifica se o contexto da aplicação é carregado corretamente
    }

    @Test
    void testCreateUser() {
        // Cria um usuário de teste
        User user = new User();
        user.setName("John Doe");
        user.setBirthDate(LocalDate.of(1990, 1, 1));

        // Salva o usuário no banco de dados
        User savedUser = userService.create(user);

        // Verifica se o usuário foi salvo corretamente
        assertNotNull(savedUser.getId(), "O ID do usuário não deve ser nulo após a criação.");
        assertNotNull(userRepository.findById(savedUser.getId()).orElse(null), "O usuário deve ser encontrado no banco de dados.");
    }

    @Test
    void testFollowUser() {
        // Cria dois usuários
        User user1 = new User();
        user1.setName("Alice");
        user1.setBirthDate(LocalDate.of(1995, 5, 5));
        User savedUser1 = userService.create(user1);

        User user2 = new User();
        user2.setName("Bob");
        user2.setBirthDate(LocalDate.of(1992, 8, 8));
        User savedUser2 = userService.create(user2);

        // Usuário 1 segue o usuário 2
        userService.followUser(savedUser1.getId(), savedUser2.getId());

        // Verifica se o seguidor foi adicionado corretamente
        User updatedUser1 = userRepository.findById(savedUser1.getId()).orElseThrow();
        assertTrue(updatedUser1.getFollowing().stream()
                .anyMatch(u -> u.getId().equals(savedUser2.getId())), "O usuário 1 deve seguir o usuário 2.");
    }

    @Test
    void testCreatePost() {
        // Cria um usuário
        User user = new User();
        user.setName("Jane Doe");
        user.setBirthDate(LocalDate.of(1988, 3, 15));
        User savedUser = userService.create(user);

        // Cria um post associado ao usuário
        Post post = new Post();
        post.setContent("Meu primeiro post!");
        post.setUser(savedUser);

        // Salva o post (você precisará de um PostService para isso)
        Post savedPost = postService.create(post);

        // Verifica se o post foi salvo corretamente
        assertNotNull(savedPost.getId(), "O ID do post não deve ser nulo após a criação.");
        assertEquals(savedUser.getId(), savedPost.getUser().getId(), "O post deve estar associado ao usuário correto.");
    }
}