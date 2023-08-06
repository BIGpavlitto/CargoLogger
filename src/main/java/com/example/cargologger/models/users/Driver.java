package com.example.cargologger.models.users;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("Driver")
public class Driver extends User {
    //private List<Trailer> trailer;
    //private List<Truck> trucks;
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
    //private List<Route> route;
}
