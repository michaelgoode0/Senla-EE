package com.test.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.project.WebAppTest;
import com.test.project.api.service.PostService;
import com.test.project.dto.PostDto;
import com.test.project.dto.UserProfileDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = PostController.class)
public class PostControllerTest extends WebAppTest{

    @Autowired
    private PostService postService;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void getPostShouldFinishOk() throws Exception {
        PostDto postDto= new PostDto();
        postDto.setText("text");
        PostDto postDto1= createPostDto(postDto);
        Long id = postDto1.getId();

        mockMvc.perform(get("/posts/get/{id}",id))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.text").value( "text"))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void savePostShouldFinishOk() throws Exception {
        PostDto post= new PostDto();
        post.setText("hello");

        mockMvc.perform(post("/posts")
                .content(objectMapper.writeValueAsString(post))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    public void deletePostShouldFinishOk() throws Exception {
        PostDto postDto= new PostDto();
        postDto.setText("Bye Bye");
        PostDto postDto1 = createPostDto(postDto);
        Long id = postDto1.getId();
        mockMvc.perform(delete("/posts/delete/{id}",id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void updatePostShouldFinishOk() throws Exception { ;
       PostDto postDto= new PostDto();
       postDto.setText("post1");
       PostDto postDto1= createPostDto(postDto);
       PostDto postDto2= new PostDto();
       postDto2.setId(postDto1.getId());
       postDto2.setText("nothing");
        mockMvc.perform(put("/posts")
                .content(objectMapper.writeValueAsString(postDto2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(postDto1.getId()))
                .andExpect(jsonPath("$.text").value("nothing"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    private PostDto createPostDto(PostDto postDto){
        return postService.create(postDto);
    }


}
