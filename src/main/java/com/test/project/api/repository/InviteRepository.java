package com.test.project.api.repository;

import com.test.project.entity.Invite;
import com.test.project.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepository extends JpaRepository<Invite,Long> {
    Invite findInviteByUserToAndUserFrom(UserProfile userTo,UserProfile userFrom);
}
