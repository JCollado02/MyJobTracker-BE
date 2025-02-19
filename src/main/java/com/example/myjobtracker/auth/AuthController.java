package com.example.myjobtracker.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Date;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1")
public class AuthController {


    private final Key SECRET_KEY = generateSecretKey();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        if (isValidUser(request.getEmail(), request.getPassword())) {
            String token = generateToken(request.getEmail());

            Cookie cookie = new Cookie("jwt", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setMaxAge(86400); // 1 day
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @GetMapping("/auth-check")
    public ResponseEntity<?> checkAuth(@CookieValue(value = "jwt", required = false) String token) {
        if (token != null && validateToken(token)) {
            return ResponseEntity.ok("Authenticated");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(0); // Expire cookie
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok("Logged out successfully");
    }

    private boolean isValidUser(String email, String password) {
        String adminEmail = System.getenv("ADMIN_EMAIL");
        String adminPassword = System.getenv("ADMIN_PASSWORD");

        return email.equals(adminEmail) && password.equals(adminPassword);
    }


    private String generateToken(String email) {
        if (!email.equals(System.getenv("ADMIN_EMAIL"))) {
            throw new IllegalStateException("Unauthorized user!"); // admin only for now
        }

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expire
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }


    private boolean validateToken(String token) {
        try {
            Jwts.parser() // Updated to parser()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Invalid or expired JWT: " + e.getMessage()); // LOGS!!!
            return false;
        }
    }

    private static Key generateSecretKey() {
        String secret = System.getenv("JWT_SECRET");
        if (secret == null || secret.isEmpty()) {
            throw new IllegalStateException("JWT_SECRET environment variable is not set!");
        }
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(decodedKey);
    }
}
