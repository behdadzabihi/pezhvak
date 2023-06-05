package com.pezhvak.project.comment;

import com.pezhvak.project.common.BaseEntity;
import com.pezhvak.project.user.UserApp;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tbl_comment")
@Audited
@Data
public class Comment extends BaseEntity {

    @NotNull
    @Column(name = "date")
    private Date date;


    @NotNull
    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApp userApp;



}
