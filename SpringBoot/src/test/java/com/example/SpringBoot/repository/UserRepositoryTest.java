package com.example.SpringBoot.repository;

import com.example.SpringBoot.model.Role;
import com.example.SpringBoot.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void testFindByUsername() {
    userRepository.save(User.builder()
            .username("testUser")
            .password("password")
            .role(Role.ROLE_USER)
        .build());

    User foundUser = userRepository.findByUsername("testUser");

    assertThat(foundUser).isNotNull();
    assertThat(foundUser.getUsername()).isEqualTo("testUser");
  }
}
