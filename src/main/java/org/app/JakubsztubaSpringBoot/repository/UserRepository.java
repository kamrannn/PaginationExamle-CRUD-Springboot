package org.app.JakubsztubaSpringBoot.repository;

import org.app.JakubsztubaSpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
