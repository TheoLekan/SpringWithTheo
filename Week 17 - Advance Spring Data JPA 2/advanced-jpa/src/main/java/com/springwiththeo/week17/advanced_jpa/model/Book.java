package com.springwiththeo.week17.advanced_jpa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Book extends Auditable {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;
    private String title;
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    @ToString.Exclude
    @OneToMany(mappedBy = "book", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    @EqualsAndHashCode.Exclude
    private Set<SalesRecord> salesRecords = new HashSet<>();


    public void addSalesRecord(SalesRecord... salesRecords) {
        Arrays.stream(salesRecords).forEach(salesRecord -> {
            this.salesRecords.add(salesRecord);
            salesRecord.setBook(this);
        });
    }
}
