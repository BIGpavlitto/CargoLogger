package com.example.cargologger.models.users;

import com.example.cargologger.models.Company;
import com.example.cargologger.models.users.Driver;
import com.example.cargologger.models.users.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@DiscriminatorValue("Employer")
public class Employer extends User {
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @Column(nullable = false)
    @OneToMany
    private List<Driver> drivers;
}
