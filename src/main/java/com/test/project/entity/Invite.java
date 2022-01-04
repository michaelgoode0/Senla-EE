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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_invite")
    private Date dateOfInvite;
    @Enumerated(EnumType.STRING)
    private InviteStatus status;

    @OneToOne
    @JoinTable(name = "invites_from_user", joinColumns = {
            @JoinColumn(name = "invites_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "from_user_id", referencedColumnName = "id")
            })
    private UserProfile userFrom;
    @OneToOne
    @JoinTable(name = "invites_to_user", joinColumns = {
            @JoinColumn(name = "invites_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "to_user_id", referencedColumnName = "id")
            })
    private UserProfile userTo;
}
