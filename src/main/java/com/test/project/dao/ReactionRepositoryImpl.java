package com.test.project.dao;

import com.test.project.api.repository.ReactionRepository;
import com.test.project.entity.Reaction;
import com.test.project.util.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class ReactionRepositoryImpl extends AbstractDao<Reaction> implements ReactionRepository {
}
