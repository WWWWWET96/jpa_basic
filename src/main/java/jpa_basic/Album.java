package jpa_basic;

import javax.persistence.Entity;

@Entity
public class Album extends Item{
    private String artist;
}