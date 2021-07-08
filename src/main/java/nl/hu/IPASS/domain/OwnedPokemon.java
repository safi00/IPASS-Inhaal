package nl.hu.IPASS.domain;

import javax.persistence.*;

@Entity
public class OwnedPokemon {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
    @ManyToOne(fetch = FetchType.EAGER)
    private Pokemon pokemon;
    private boolean isFav;

    public OwnedPokemon(User owner, Pokemon pokemon) {
        this.owner = owner;
        this.pokemon = pokemon;
        this.isFav = false;
    }

    public OwnedPokemon() {

    }

    public User getOwner() {
        return owner;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(Boolean bol) {
        this.isFav = bol;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return pokemon.getName() + " is owned by " +  owner.getName() + " fav : " + isFav;
    }
}
