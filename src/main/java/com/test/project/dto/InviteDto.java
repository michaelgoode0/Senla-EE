package com.test.project.dto;

import com.test.project.enums.InviteStatus;
import lombok.Data;

import java.util.Date;

@Data
public class InviteDto {
    private Long id;
    private Date dateOfInvite;
    private InviteStatus status;
    private UserProfileWithUserDto userFrom;
    private UserProfileWithUserDto userTo;
}
