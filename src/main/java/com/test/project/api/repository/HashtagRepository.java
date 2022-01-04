package com.test.project.api.repository;

import com.test.project.entity.Hashtag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository  extends JpaRepository<Hashtag,Long> {
    Hashtag findHashtagByValue(String value);
    @Query("select h.value from Hashtag h")
    List<String> findHashtagValues();
    @Query("select h from Hashtag h order by h.posts.size desc")
    Page<Hashtag> getAllTop(Pageable pageable);

}
