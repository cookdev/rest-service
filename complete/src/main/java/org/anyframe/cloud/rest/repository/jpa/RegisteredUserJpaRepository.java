package org.anyframe.cloud.rest.repository.jpa;


import org.anyframe.cloud.rest.domain.User;
import org.anyframe.cloud.rest.repository.RegisteredUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredUserJpaRepository extends RegisteredUserRepository, JpaRepository<User, String> {
}
