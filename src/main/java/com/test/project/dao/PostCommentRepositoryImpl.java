package com.test.project.dao;

import com.test.project.api.repository.PostCommentRepository;
import com.test.project.entity.PostComment;
import com.test.project.util.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class PostCommentRepositoryImpl extends AbstractDao<PostComment> implements PostCommentRepository {
}
