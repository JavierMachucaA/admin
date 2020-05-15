package cl.course.admin.rest.api;

import cl.course.admin.model.AuthenticationRequest;
import cl.course.admin.model.JwtResponse;
import cl.course.admin.security.JwtArtifact;
import cl.course.admin.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorizationRestService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtArtifact jwtArtifact;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @GetMapping("/token")
    public ResponseEntity<JwtResponse> getToken() throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken("1", "1")
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername("1");

        final String jwt = jwtArtifact.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @GetMapping("/hello" )
    public String firstPage() {
        return "Hello World";
    }

    @PostMapping("/evaluate")
    public String evaluate( @RequestParam(value = "token") String token ){
        //return jwtArtifact.validateToken(token)+"";
        return "";
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
