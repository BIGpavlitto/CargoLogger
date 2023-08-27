package com.example.cargologger.models.users;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@DiscriminatorValue("Driver")
public class Driver extends User {
    //private List<Trailer> trailer;
    //private List<Truck> trucks;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_id")
    @ToString.Exclude
    private Employer employer;
    //private List<Route> route;
}
