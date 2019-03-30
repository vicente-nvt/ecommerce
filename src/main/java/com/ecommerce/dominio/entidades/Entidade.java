package com.ecommerce.dominio.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long Id;

    public Long getId() {
        return this.Id;
    }    
}