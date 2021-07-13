package nl.hu.IPASS.domain;

import javax.persistence.*;

@Entity
public class OwnedPokemon {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;
    @ManyToOne(fetch = FetchType.EAGER)
    private Pokemon pokemon;
    private boolean isFavourite;

    public OwnedPokemon(User owner, Pokemon pokemon) {
        this.owner = owner;
        this.pokemon = pokemon;
        this.isFavourite = false;
    }

    public OwnedPokemon() {

    }

    public int getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean bol) {
        this.isFavourite = bol;
    }

    @Override
    public String toString() {
        return pokemon.getName() + " is owned by " +  owner.getName() + " fav : " + isFavourite;
    }
}
