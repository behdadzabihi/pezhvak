package com.pezhvak.project.user;

import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(value = "/user/")
@AllArgsConstructor
public class UserAppController {
    private UserService service;

    private UserAppMapper mapper;

    @PostMapping("v1")
    public ResponseEntity<UserAppDTO> save(@RequestBody UserAppDTO UserAppDTO){
        UserApp userApp=mapper.toUser(UserAppDTO);
        service.save(userApp);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("v1")
    public ResponseEntity<List<UserAppDTO>> getAll(){
        List<UserApp> userApps=service.getAll();
        List<UserAppDTO> userAppDTOS =mapper.toUserDTOs(userApps);
        return ResponseEntity.ok(userAppDTOS);
    }


    @DeleteMapping("v1/{id}")
    public ResponseEntity delete(@PathVariable Long id){
            service.delete(id);
            return ResponseEntity.ok().build();
    }



    @PutMapping("v1")
    public ResponseEntity<UserAppDTO> update(@RequestBody UserAppDTO userAppDTO){
        UserApp userApp=mapper.toUser(userAppDTO);
        service.update(userApp);
        return ResponseEntity.ok(userAppDTO);
    }

    @GetMapping("v1/{id}")
    public ResponseEntity<UserAppDTO> findById(@PathVariable Long id){
        UserApp userApp= service.getById(id);
        UserAppDTO userAppDTO=mapper.toUserDTO(userApp);
        return ResponseEntity.ok(userAppDTO);
    }
}
