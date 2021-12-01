package com.test.project.api.repository;

import com.test.project.util.GenericDao;
import com.test.project.entity.Post;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends GenericDao<Post> {
    Post getPostGraph(Long id);
    Post getPostJpql(Long id);
    Post getPostCriteria(Long id);
}
