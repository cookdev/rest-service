package org.anyframe.cloud.restservice.repository.jpa;


import org.anyframe.cloud.restservice.domain.User;
import org.anyframe.cloud.restservice.repository.RegisteredUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredUserJpaRepository extends RegisteredUserRepository, JpaRepository<User, String> {
}
