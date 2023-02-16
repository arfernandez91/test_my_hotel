package com.myhotel.carsolution.Controllers;


import com.myhotel.carsolution.Services.MyUserDetailsService;
import com.myhotel.carsolution.Util.ApiResponse;
import com.myhotel.carsolution.Util.AuthenticationRequest;
import com.myhotel.carsolution.Util.AuthenticationResponse;
import com.myhotel.carsolution.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/api/v1/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping(value = "/autheticante",method = RequestMethod.POST)
    public ResponseEntity<?> createAutenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        ApiResponse apiResponse = new ApiResponse();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrors(Arrays.asList(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        apiResponse.setMessage("Se autenticó correctamente");
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setContent(jwt);
        return  ResponseEntity.ok(apiResponse);
    }

}
