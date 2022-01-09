package com.test.project.api.repository;

import com.test.project.entity.Reaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction,Long> {
    @Query("select r from Reaction r")
    List<Reaction> findAll();
    @Query("select r from Reaction r where r.post.id=:id")
    Page<Reaction> findAllByPost(Pageable pageable, Long id);
}
