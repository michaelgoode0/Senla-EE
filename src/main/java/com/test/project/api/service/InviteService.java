package com.test.project.api.service;

import com.test.project.dto.InviteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InviteService {
    InviteDto sendInvite(Long toUserId);
    InviteDto acceptInvite(Long toUserId);
    InviteDto read(Long id);
    InviteDto denyInvite(Long id);
    Page<InviteDto> findAll(Pageable pageable);
}
