package com.pezhvak.project.comment;


import com.pezhvak.project.user.UserAppMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {UserAppMapper.class})

public interface CommentMapper {

    Comment toComment(CommentDTO commentDTO);

    CommentDTO toCommentDTO(Comment comment);

    List<Comment> toComments(List<CommentDTO> commentDTOS);

    List<CommentDTO> toCommentDTOS(List<Comment> comments);

}
