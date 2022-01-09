package com.test.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.project.WebAppTest;
import com.test.project.WithMockCustomUser;
import com.test.project.api.service.PostService;
import com.test.project.dto.PostDto;
import com.test.project.dto.PostWithAllDto;
import com.test.project.security.api.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockCustomUser()
@ContextConfiguration(classes = PostController.class)
public class PostControllerTest extends WebAppTest {

    @Autowired
    private PostService postService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void deleteUser(){
        userRepository.deleteAll();
    }

    @Test
    public void getPostShouldFinishOk() throws Exception {
        PostDto postDto = new PostDto();
        postDto.setText("text");
        PostDto postDto1 = createPostDto(postDto);
        Long id = postDto1.getId();

        mockMvc.perform(get("/posts/{id}", id))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.text").value("text"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void savePostShouldFinishOk() throws Exception {
        PostWithAllDto post = new PostWithAllDto();
        post.setText("hello");

        mockMvc.perform(post("/posts/")
                .content(objectMapper.writeValueAsString(post))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deletePostShouldFinishOk() throws Exception {
        PostDto postDto = new PostDto();
        postDto.setText("Bye Bye");
        PostDto postDto1 = createPostDto(postDto);
        Long id = postDto1.getId();
        mockMvc.perform(delete("/posts/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void likePostShouldFinishOk() throws Exception {
        PostDto postDto = new PostDto();
        postDto.setText("text");
        postDto.setId(123L);
        PostDto dto = createPostDto(postDto);
        Long id =dto.getId();
        mockMvc.perform(get("/posts/{id}/like",id)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.text").value("text"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void updatePostShouldFinishOk() throws Exception {
        PostDto postDto = new PostDto();
        postDto.setText("post1");
        PostDto postDto1 = createPostDto(postDto);
        PostDto postDto2 = new PostDto();
        postDto2.setId(postDto1.getId());
        postDto2.setText("nothing");
        Long id= postDto1.getId();
        mockMvc.perform(put("/posts")
                .content(objectMapper.writeValueAsString(postDto2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.text").value("nothing"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private PostDto createPostDto(PostDto postDto) {
        return postService.create(postDto);
    }



}
