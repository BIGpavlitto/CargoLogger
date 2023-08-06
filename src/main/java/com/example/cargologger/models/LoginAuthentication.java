package com.example.cargologger.models;

import com.example.cargologger.models.users.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class LoginAuthentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String loginId;
    @Column(nullable = false)
    private String password;
    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
