package com.test.project.dao;

import com.test.project.api.repository.HashtagRepository;
import com.test.project.entity.Hashtag;
import com.test.project.entity.Post;
import com.test.project.util.AbstractDao;
import javassist.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HashtagRepositoryImpl extends AbstractDao<Hashtag> implements HashtagRepository {
    @Override
    public List<String> getAllHashtagValues(){
        return entityManager.createQuery("select h.value from Hashtag h",String.class).getResultList();
    }

    @Override
    public Hashtag getHashtag(String value){
        return  entityManager.createQuery("select h from Hashtag h where h.value=:value",Hashtag.class)
                .setParameter("value",value)
                .getSingleResult();
    }
}
