package com.springwiththeo.week17.advanced_jpa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "sales_record")
public class SalesRecord extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int copiesSold;
    @Column(name = "`month`")
    private LocalDate month;

    public SalesRecord(int copiesSold, LocalDate month) {
        this.copiesSold = copiesSold;
        this.month = month;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;
}
