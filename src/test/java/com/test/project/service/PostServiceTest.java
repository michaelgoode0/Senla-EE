package com.test.project.service ;

import com.test.project.WithMockCustomUser;
import com.test.project.api.repository.PostRepository;
import com.test.project.api.service.PostService;
import com.test.project.dto.PostDto;
import com.test.project.dto.PostWithAllDto;
import com.test.project.entity.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@WithMockCustomUser
public class PostServiceTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Spy
    private ModelMapper mapper;

    @Mock
    private PostRepository postRepository;

    @Test
    @Transactional
    public void createPostShouldFinishOk(){
        Post post=new Post();
        post.setId(123L);
        final String text = "user #3";
        final String hashtag = "#3";
        post.setText(text);
        when(postRepository.save(any())).thenReturn(post);

        PostDto postDto = new PostDto();
        postDto.setText(text);
        PostDto postDto1 = postService.create(postDto);

        assertEquals(123L, postDto1.getId());
        assertEquals(text, postDto1.getText());
        assertTrue(post.getHashtags().stream().anyMatch(k->k.getValue().equals(hashtag)));

    }
    @Test
    @Transactional
    public void readPostShouldFinishOk(){

        Post post=new Post();
        post.setId(123L);
        final String text = "user";
        post.setText(text);

        when(postRepository.save(any())).thenReturn(post);
        when(postRepository.findById(any())).thenReturn(Optional.of(post));

        PostDto postDto = new PostDto();
        postDto.setText(text);


        postService.create(postDto);
        PostWithAllDto postDto1 = postService.read(123L);


        assertEquals(123L, postDto1.getId());
        assertEquals(text, postDto1.getText());
    }

    @Test
    public void updatePostShouldFinishOk(){
        Post post = new Post();
        post.setId(123L);
        String text = "user";
        post.setText(text);
        when(postRepository.save(any())).thenReturn(post);

        PostDto postDto = new PostDto();
        post.setText(text);
        PostDto savePost = postService.create(postDto);
        text = "user2";
        savePost.setText(text);
        postService.update(savePost);

        assertEquals(123L, savePost.getId());
        assertEquals(text, savePost.getText());

    }

    @Test
    public void deletePostShouldFinishOk(){
        Post post = new Post();
        post.setId(123L);
        final String text = "text";
        post.setText(text);
        when(postRepository.save(any())).thenReturn(post);

        PostDto postDto = new PostDto();
        postDto.setText(text);
        PostDto post1=postService.create(postDto);
        assertNotNull(post1);
        postService.delete(post1.getId());
        PostWithAllDto nullPost=postService.read(post1.getId());
        assertNull(nullPost);

    }

}
