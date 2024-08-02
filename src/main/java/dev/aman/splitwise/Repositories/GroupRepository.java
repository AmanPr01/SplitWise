package dev.aman.splitwise.Repositories;

import dev.aman.splitwise.Models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Override
    Optional<Group> findById(Long groupId);
}
