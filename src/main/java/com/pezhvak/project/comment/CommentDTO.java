package com.pezhvak.project.comment;

import com.pezhvak.project.common.BaseDTO;
import com.pezhvak.project.user.UserAppDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
public class CommentDTO extends BaseDTO {

    @ApiModelProperty(required = true,hidden = false)
    private Date date;

    @ApiModelProperty(required = true,hidden = false)
    private String message;

    @ApiModelProperty(required = true,hidden = false)
    private UserAppDTO userApp;



}
