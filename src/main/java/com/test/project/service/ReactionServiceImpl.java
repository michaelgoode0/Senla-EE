package com.test.project.service;

import com.test.project.api.repository.PostRepository;
import com.test.project.api.repository.ReactionRepository;
import com.test.project.api.service.ReactionService;
import com.test.project.dto.ReactionDto;
import com.test.project.entity.Post;
import com.test.project.entity.Reaction;
import com.test.project.entity.UserProfile;
import com.test.project.security.api.repository.UserRepository;
import com.test.project.security.model.User;
import com.test.project.util.AuthNameHolder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReactionServiceImpl implements ReactionService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ReactionRepository reactionRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public ReactionDto like(Long postId, boolean react) {
        User user = userRepository.loadUserByUsername(AuthNameHolder.authUsername());
        Post post = postRepository.read(postId);
        UserProfile userProfile = user.getUserProfile();
        List<Reaction> reactions = post.getReactions();
        Reaction reaction = new Reaction();
        reaction.setPost(post);
        reaction.setReaction(react);
        reaction.setProfile(userProfile);
        if (reactions.contains(userProfile.getReaction())) {
            Boolean isLike = userProfile.getReaction().getReaction();
            reactionRepository.delete(userProfile.getReaction().getId());
            if(!isLike.equals(react)){
                reactionRepository.create(reaction);
            }
        }
        else {
            reactionRepository.create(reaction);
        }
        return mapper.map(reaction, ReactionDto.class);
    }

}
