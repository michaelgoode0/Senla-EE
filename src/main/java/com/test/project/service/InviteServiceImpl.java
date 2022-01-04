package com.test.project.service;

import com.test.project.api.repository.InviteRepository;
import com.test.project.api.service.InviteService;
import com.test.project.api.service.UserProfileService;
import com.test.project.dto.InviteDto;
import com.test.project.dto.UserProfileWithAllDto;
import com.test.project.entity.Invite;
import com.test.project.entity.UserProfile;
import com.test.project.enums.InviteStatus;
import com.test.project.exceptions.ResourceNotFoundException;
import com.test.project.security.api.service.UserService;
import com.test.project.security.dto.UserWithAllDto;
import com.test.project.util.AuthNameHolder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class InviteServiceImpl implements InviteService {

    private final InviteRepository inviteRepository;
    private final UserService userService;
    private final UserProfileService userProfileService;
    private final ModelMapper mapper;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<InviteDto> findAll(Pageable pageable) {
        return inviteRepository.findAll(pageable).map(entity->mapper.map(entity, InviteDto.class));
    }

    @Override
    @Transactional
    public InviteDto sendInvite(Long toUserId){
            UserWithAllDto user =  userService.loadByUsername(AuthNameHolder.getAuthUsername());
            UserProfile fromUser = mapper.map(user.getProfile(),UserProfile.class);
            UserProfileWithAllDto userProfile = userProfileService.read(toUserId);
            UserProfile toUser = mapper.map(userProfile,UserProfile.class);
            if(inviteRepository.findInviteByUserToAndUserFrom(toUser,fromUser)==null &&
                    !toUser.getFriends().contains(toUser) && !toUserId.equals(fromUser.getId())) {
                Invite inv = new Invite();
                inv.setUserFrom(fromUser);
                inv.setUserTo(toUser);
                inv.setDateOfInvite(new Date());
                inv.setStatus(InviteStatus.WAITING);
                Invite invite = inviteRepository.save(inv);
                return mapper.map(invite, InviteDto.class);
            }
            else{
                throw new ResourceNotFoundException("User can not be invited");
            }
    }
    @Override
    @Transactional
    @PreAuthorize("@inviteServiceImpl.read(#id).userTo.user.username==authentication.name" + "|| hasRole('ROLE_ADMIN')")
    public InviteDto acceptInvite(Long id){
        Invite invite= inviteRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Invite object with id:" + id + " not found"));
        UserProfile fromUser = invite.getUserFrom();
        UserProfile toUser = invite.getUserTo();
        fromUser.getFriends().add(toUser);
        toUser.getFriends().add(fromUser);
        inviteRepository.delete(invite);
        return mapper.map(invite,InviteDto.class);
    }
    @Override
    @Transactional
    @PreAuthorize("@inviteServiceImpl.read(#id).userTo.user.username==authentication.name"+ "|| hasRole('ROLE_ADMIN')")
    public InviteDto denyInvite(Long id){
        Invite invite= inviteRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Invite object with id:" + id + " not found"));
        inviteRepository.delete(invite);
        return mapper.map(invite,InviteDto.class);
    }


    @Override
    @Transactional
    public InviteDto read(Long id){
        Invite invite = inviteRepository.findById(id)
                .orElseThrow((()->new ResourceNotFoundException("Invite object with id:" + id + " not found")));
        return mapper.map(invite,InviteDto.class);
    }


}
