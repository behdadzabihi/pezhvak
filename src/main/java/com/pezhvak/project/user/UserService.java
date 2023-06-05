package com.pezhvak.project.user;

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
public class UserService {

   private UserRepository repository;



    @RolesAllowed({"admin","user"})
    public UserApp save(UserApp userApp) {
        return repository.save(userApp);
    }

    @RolesAllowed({"admin","user"})
    public UserApp update(UserApp userApp) {
        if(userApp.getId() != null) {
            UserApp lastUserApp = getById(userApp.getId());
            lastUserApp.setFirstName(userApp.getFirstName());
            lastUserApp.setLastName(userApp.getLastName());
            lastUserApp.setEmail(userApp.getEmail());
            lastUserApp.setGender(userApp.getGender());
            lastUserApp.setBirthDay(userApp.getBirthDay());
            return repository.save(lastUserApp);
        }
        else {
            throw new NotFoundException("User Not Found");
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
    public List<UserApp> getAll() {
        return (List<UserApp>) repository.findAll();
    }


    @RolesAllowed({"admin","user"})
    public UserApp getById(Long id) {
        Optional<UserApp> userOptional=repository.findById(id);
        if(!userOptional.isPresent()){
            throw new NotFoundException("NOT FOUND USER");
        }
        return userOptional.get();
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
