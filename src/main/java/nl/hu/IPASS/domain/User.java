package nl.hu.IPASS.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "users")
public class User extends Account{
    private String name;
    private String aboutMe;
    @OneToMany(mappedBy = "owner")
    private List<OwnedPokemon> ownedPokemonList;

    public User(String username, String password, String email, String accountType, String name) {
        super(username, password, email, accountType);
        this.name = name;
        this.aboutMe = "...";
        this.ownedPokemonList = new ArrayList<>();
    }

    public User() {

    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean checkPassword(String password) {
        return super.checkPassword(password);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getAccountType() {
        return super.getAccountType();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPokemon(Pokemon pokemon) {
        OwnedPokemon op = new OwnedPokemon(this, pokemon);
        this.ownedPokemonList.add(op);
    }

    public void removePokemon(Pokemon pokemon) {
        if (collectionContainsPokemon(pokemon)){
            OwnedPokemon pokemonIndex = returnOwnedPokemon(pokemon);
            this.ownedPokemonList.remove(pokemonIndex);
        }
    }

    public void addPokemonToFav(Pokemon pokemon) {
        if (collectionContainsPokemon(pokemon)){
            OwnedPokemon pokemonIndex = returnOwnedPokemon(pokemon);
            pokemonIndex.setFav(true);
        }
    }

    public void removePokemonFromFav(Pokemon pokemon) {
        if (collectionContainsPokemon(pokemon)){
            OwnedPokemon pokemonIndex = returnOwnedPokemon(pokemon);
            pokemonIndex.setFav(false);
        }
    }

    public OwnedPokemon returnOwnedPokemon(Pokemon pokemon){
        OwnedPokemon returnValue = null;
        for (OwnedPokemon ownedPok : this.ownedPokemonList) {
            if (ownedPok.getPokemon() == pokemon){
                returnValue = ownedPok;
            }
        }
        return returnValue;
    }

    public boolean collectionContainsPokemon(Pokemon pokemon){
        boolean returnValue = false;
        for (OwnedPokemon ownedPok : this.ownedPokemonList) {
            returnValue = ownedPok.getPokemon() == pokemon;
        }
        return returnValue;
    }

    public boolean favContainsPokemon(Pokemon pokemon){
        boolean returnValue = false;
        for (OwnedPokemon ownedPok : getFavoritePokemon()) {
            returnValue = ownedPok.getPokemon() == pokemon;
        }
        return returnValue;
    }

    public List<OwnedPokemon> getOwnedPokemonList() {
        return ownedPokemonList;
    }

    public List<OwnedPokemon> getFavoritePokemon() {
        return this.ownedPokemonList.stream().filter(OwnedPokemon::isFav).collect(Collectors.toList());
    }

    public List<Pokemon> getPokemonList() {
        List<Pokemon> returnValue = new ArrayList<>();
        for (OwnedPokemon pok : getOwnedPokemonList()){
            returnValue.add(pok.getPokemon());
        }
        return returnValue;
    }

    public List<Pokemon> getFavoritePokemonList() {
        List<Pokemon> returnValue = new ArrayList<>();
        for (OwnedPokemon pok : getFavoritePokemon()){
            returnValue.add(pok.getPokemon());
        }
        return returnValue;
    }

    public int getTotalPokemon(){
        return this.ownedPokemonList.size();
    }

    public String getUserDetails(){
        return "#" + getId() + " account type:" + getAccountType() + " by user " + getUsername() + " can be contacted by " + getEmail()
                + " name : " + getName() + " self description : " + getAboutMe();
    }
}
