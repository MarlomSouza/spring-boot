package com.brasilprev.loja.dominio.entidade;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    public abstract long getId();
}