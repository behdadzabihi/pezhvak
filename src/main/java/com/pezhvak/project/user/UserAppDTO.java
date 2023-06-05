package com.pezhvak.project.user;

import com.pezhvak.project.comment.Comment;
import com.pezhvak.project.common.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;


@Data
public class UserAppDTO extends BaseDTO {


    @ApiModelProperty(required = true,hidden = false)
    private String firstName;

    @ApiModelProperty(required = true,hidden = false)
    private String lastName;

    @ApiModelProperty(required = true,hidden = false)
    private String email;

    @ApiModelProperty(required = true,hidden = false)
    private Gender gender;

    @ApiModelProperty(required = true,hidden = false)
    private Date birthDay;



}
