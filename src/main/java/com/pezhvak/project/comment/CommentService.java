package com.pezhvak.project.comment;

import com.pezhvak.project.user.UserApp;
import com.pezhvak.project.user.UserService;
import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository repository;


    private UserService userService;

    @RolesAllowed({"admin","user"})
    public Comment save(Comment comment) {
        Long userAppId=comment.getUserApp().getId();
        UserApp userApp=userService.getById(userAppId);
        comment.setUserApp(userApp);
        return repository.save(comment);
    }
    @RolesAllowed({"admin","user"})
    public Comment update(Comment comment) {
        if(comment.getId() != null) {
            Comment lastComment = getById(comment.getId());
            lastComment.setDate(comment.getDate());
            lastComment.setMessage(comment.getMessage());
            Long userAppId = comment.getUserApp().getId();
            UserApp userApp = userService.getById(userAppId);
            lastComment.setUserApp(userApp);
            return repository.save(lastComment);
        }else {
            throw new NotFoundException("Comment Not Found");
        }
    }


    public void delete(Long id) {
        KeycloakSecurityContext securityContext = extractKeycloakSecurityContext();
        if (securityContext != null && hasRole("admin", securityContext)) {
            getById(id);
            repository.deleteById(id);
        } else {
            throw new AccessDeniedException("Access denied");
        }
    }

    @RolesAllowed("admin")
    public List<Comment> getAll() {
        return (List<Comment>) repository.findAll();
    }

    @RolesAllowed({"admin","user"})
    public Comment getById(Long id) {
        Optional<Comment> commentOptional=repository.findById(id);
        if(!commentOptional.isPresent()){
            throw new NotFoundException("NOT FOUND COMMENT");
        }
        return commentOptional.get();
    }
    private KeycloakSecurityContext extractKeycloakSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal) {
            KeycloakPrincipal<?> principal = (KeycloakPrincipal<?>) authentication.getPrincipal();
            return principal.getKeycloakSecurityContext();
        }
        return null;
    }

    private boolean hasRole(String role, KeycloakSecurityContext securityContext) {
        return securityContext.getToken().getRealmAccess().isUserInRole(role);
    }
}
