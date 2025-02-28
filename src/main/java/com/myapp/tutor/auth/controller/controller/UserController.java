package com.myapp.tutor.auth.controller.controller;

import com.myapp.portalnordsyspb.auth.dto.UserResponseDto;
import com.myapp.portalnordsyspb.auth.entity.User;
import com.myapp.portalnordsyspb.visitCounter.service.VisitCounterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/user")
@Tag(name = "Authentication", description = "Description for authentication")
@RequiredArgsConstructor
public class UserController {

    private final VisitCounterService visitCounterService;

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserInfo(@AuthenticationPrincipal User user){
        visitCounterService.incrementVisitCount();
        if (user != null){
            return ResponseEntity.ok(new UserResponseDto(user.getName(),
                    user.getEmail(),
                    true));
        } else {
            return ResponseEntity.ok(new UserResponseDto("",
                    "",
                    false));
        }
    }

//    public boolean isUserAuthenticated() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication != null && authentication.isAuthenticated();
//    }


//    boolean answer;
//
//    @GetMapping("/api/user-role")
//    public String getUserRoles2(@AuthenticationPrincipal User user) {
//        if (user != null) {
//            // Извлекаем роли пользователя
//            StringBuilder roles = new StringBuilder();
//
//            user.getAuthorities().forEach(this::checkIsAdmin);
//            user.getAuthorities().forEach(authority -> roles.append(authority.getAuthority()).append(" "));
//            System.out.println("user.getAuthorities(): " + user.getAuthorities().toString());
//            System.out.println("Roles: " + roles.toString());
//            System.out.println("ANSWER: " + answer);
//            return "Roles: " + roles.toString();
//        }
//        System.out.println("User is not authenticated");
//        return "User is not authenticated";
//    }
//
//    private void checkIsAdmin(GrantedAuthority grantedAuthority) {
//        if (grantedAuthority.getAuthority().equals("USER")) answer = true;
//    }
}
