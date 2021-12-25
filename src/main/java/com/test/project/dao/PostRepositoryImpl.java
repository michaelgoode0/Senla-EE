package com.test.project.dao;

import com.test.project.util.AbstractDao;
import com.test.project.api.repository.PostRepository;
import com.test.project.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PostRepositoryImpl extends AbstractDao<Post> implements PostRepository {

    @Override
    public Post getPostJpql(Long id){
       return  entityManager.createQuery("select p from Post p where p.id=:id",Post.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    public Post getPostCriteria(Long  id){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(Post.class);
        Root o = q.from(Post.class);
        o.fetch("profile", JoinType.INNER);
        q.select(o);
        q.where(cb.equal(o.get("id"), id));

        Post post = (Post)entityManager.createQuery(q).getSingleResult();
        return post;
    }

    @Override
    public Post getPostGraph(Long id){
        EntityGraph graph = entityManager.getEntityGraph("graph.Post.profile");

        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graph);

        Post post = entityManager.find(Post.class, id, hints);
        return post;
    }
}
