package com.test.project.api.repository;

import com.test.project.entity.Hashtag;
import com.test.project.util.GenericDao;

import java.util.List;

public interface HashtagRepository  extends GenericDao<Hashtag> {
    List<String> getAllHashtagValues();
    Hashtag getHashtag(String value);
}
