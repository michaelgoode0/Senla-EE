package com.test.project.service ;

import com.test.project.WebAppTest;
import com.test.project.WithMockCustomUser;
import com.test.project.api.repository.PostRepository;
import com.test.project.dto.PostDto;
import com.test.project.dto.PostWithAllDto;
import com.test.project.entity.Post;
import com.test.project.security.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class,SpringExtension.class})
@ContextConfiguration(classes = PostServiceImpl.class)
@WithMockCustomUser
public class PostServiceTest extends WebAppTest {

    @InjectMocks
    private PostServiceImpl postService;

    @Spy
    private ModelMapper modelMapper;

    @Spy
    private PostRepository postRepository;

    @Mock
    private HashtagServiceImpl hashtagService;

    @Mock
    private UserRepository userRepository;


    @Test
    @Transactional
    public void createPostShouldFinishOk(){
        Post post=new Post();
        post.setId(123L);
        final String text = "user";
        post.setText(text);
        when(postRepository.save(any())).thenReturn(post);

        PostDto postDto = new PostDto();
        postDto.setText(text);
        PostDto postDto1 = postService.create(postDto);

        assertEquals(123L, postDto1.getId());
        assertEquals(text, postDto1.getText());

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
        PostDto postDto1 = new PostDto();
        postDto1.setId(123L);
        postDto1.setText("user1");
        postService.create(postDto1);

        PostDto postDto = new PostDto();
        postDto.setId(123L);
        postDto.setText("user2");
        postService.update(postDto);

        assertEquals(123L, postDto.getId());
        assertEquals("user2", postRepository.findById(123L).orElse(new Post()).getText());

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
