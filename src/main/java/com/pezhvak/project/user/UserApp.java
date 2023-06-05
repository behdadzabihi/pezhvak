package com.pezhvak.project.user;

import com.pezhvak.project.comment.Comment;
import com.pezhvak.project.common.BaseEntity;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_user")
@Data
@Audited
public class UserApp extends BaseEntity {

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Column(name = "birth_day")
    private Date birthDay;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "userApp",cascade = CascadeType.ALL)
    private List<Comment> comments;
}
