package com.test.project.repository;

import com.test.project.BaseRepositoryTest;
import com.test.project.api.repository.PostRepository;;
import com.test.project.dao.PostRepositoryImpl;
import com.test.project.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@ContextConfiguration(classes = PostRepositoryImpl.class)
public class PostRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    public void savePostShouldFinishOk() {
        Post post = new Post();
        final String text = "text";
        post.setText(text);
        Post post1=postRepository.create(post);
        assertNotNull(post1);
    }
    @Test
    @Transactional
    public void updatePostShouldFinishOk() {
        Post post = new Post();
        String text = "text";
        post.setText(text);
        Post post1=postRepository.create(post);
        assertNotNull(post1);
        text="text2";
        post1.setText(text);
        postRepository.update(post1);

        assertEquals(post,post1);
    }
    @Test
    @Transactional
    public void readPostShouldFinishOk() {
        Post post = new Post();
        String text = "text";
        post.setText(text);
        Post post1= postRepository.create(post);
        assertNotNull(postRepository.read(post1.getId()));
        assertEquals(postRepository.read(post1.getId()),post);
    }
    @Test
    @Transactional
    public void deletePostShouldFinishOk() {
        Post post = new Post();
        final String text = "text";
        post.setText(text);
        Post post1=postRepository.create(post);
        assertNotNull(post1);
        postRepository.delete(post1.getId());
        Post nullPost=postRepository.read(post1.getId());
        assertNull(nullPost);
    }
}
