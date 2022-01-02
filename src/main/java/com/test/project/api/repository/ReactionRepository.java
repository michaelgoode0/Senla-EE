package com.test.project.api.repository;

import com.test.project.entity.Reaction;
import net.bytebuddy.asm.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction,Long> {
    @Query("select r from Reaction r")
    List<Reaction> findAll();
}
