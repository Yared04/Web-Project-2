package com.gada.root;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Donation{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(value=5, message="Donation must be greater than or equal to 5")  
    private int amount;

    private String donatorName;
    @NotBlank(message = "You haven't entered account")
    private String donatorAccount;
    private LocalDateTime date;

    @ManyToOne
    private Posts post;
}