package com.test.project.entity;

import com.test.project.enums.InviteStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "invites")
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "date_of_invite")
    private Date dateOfInvite;
    @Enumerated(EnumType.STRING)
    private InviteStatus status;

    @OneToOne
    @JoinColumn(name = "from_user_id")
    private UserProfile userFrom;
    @OneToOne
    @JoinColumn(name = "to_user_id")
    private UserProfile userTo;
}
