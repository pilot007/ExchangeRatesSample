package com.rateservice.testproject.datamodel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "RATES")
@SequenceGenerator(name = "ID",  sequenceName = "RATES_SQ",
        allocationSize = 1)
@Getter
@Setter
public class Rates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column()
    private String exchange1;

    @Column()
    private String exchange2;

    @Column()
    private String rate;

    @Column()
    private LocalDateTime datum;

    public Rates() {
    }

    public Rates(String exchange1, String exchange2, String rate) {

        this.exchange1= exchange1;
        this.exchange2= exchange2;
        this.rate= rate;
        this.datum = java.time.LocalDateTime.now();
    }

}
