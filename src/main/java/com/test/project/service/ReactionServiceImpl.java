package com.test.project.service;

import com.test.project.api.repository.ReactionRepository;
import com.test.project.api.service.ReactionService;
import com.test.project.dto.PostDto;
import com.test.project.dto.ReactionDto;
import com.test.project.entity.Post;
import com.test.project.entity.Reaction;
import com.test.project.entity.UserProfile;
import com.test.project.security.api.service.UserService;
import com.test.project.security.dto.UserWithAllDto;
import com.test.project.util.AuthNameHolder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository reactionRepository;
    private final UserService userService;
    private final ModelMapper mapper;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ReactionDto> getAll(){
        return reactionRepository.findAll().stream()
                .map(entity->mapper.map(entity,ReactionDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void react(PostDto postDto, boolean react) {
        UserWithAllDto user = userService.loadByUsername(AuthNameHolder.getAuthUsername());
        UserProfile userProfile = mapper.map(user.getProfile(), UserProfile.class);
        Post post = mapper.map(postDto,Post.class);
        Reaction reaction = new Reaction();
        reaction.setPost(post);
        reaction.setReaction(react);
        reaction.setProfile(userProfile);
        Reaction postReaction = reactionRepository.findAll().stream()
                .filter(k-> k.getPost().getId().equals(post.getId()) &&
                        k.getProfile().getId().equals(userProfile.getId())).findFirst().orElse(null);
        if (postReaction!=null){
            Boolean isLike = postReaction.getReaction() ;
            reactionRepository.deleteById(postReaction.getId());
            if(!isLike.equals(react)){
                reactionRepository.save(reaction);
            }
        }
        else {
            reactionRepository.save(reaction);
        }
    }

}
