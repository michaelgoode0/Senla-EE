package com.test.project.api.repository;

import com.test.project.entity.Invite;
import com.test.project.entity.UserProfile;
import com.test.project.enums.InviteStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteRepository extends JpaRepository<Invite,Long> {
    Invite findInviteByUserToAndUserFrom(UserProfile userTo,UserProfile userFrom);
    Page<Invite> findAllByStatus(InviteStatus status, Pageable pageable);
}
