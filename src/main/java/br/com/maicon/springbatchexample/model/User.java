package br.com.maicon.springbatchexample.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    private Long id;
    private String name;
    private String dept;
    private BigDecimal salary;
}
