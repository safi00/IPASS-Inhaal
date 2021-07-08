package nl.hu.IPASS.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Pokemon {
    @Id
    private int pokedexNumber;
    private String name;
    private String type1;
    private String type2;
    private int    generation;
    private String rarity;
    private String region;
    private String ability1;
    private String ability2;
    private String hiddenAbility;

    public Pokemon(int pokedexNumber, String name, String type1, String type2, int gen, String region, String rarity, String ability1, String ability2, String hiddenAbility){
        this.pokedexNumber = pokedexNumber;
        this.name          = name;
        this.type1         = type1;
        this.type2         = type2;
        this.generation    = gen;
        this.region        = region;
        this.rarity        = rarity;
        this.ability1      = ability1;
        this.ability2      = ability2;
        this.hiddenAbility = hiddenAbility;
    }

    public Pokemon() {

    }

    public int getPokedexNumber() {
        return pokedexNumber;
    }

    public String getName() {
        return name;
    }

    public int getGeneration() {
        return generation;
    }

    public String getRegion() {
        return region;
    }

    public String getRarity() {
        return rarity;
    }

    public List<String> getTyping() {
        List<String> typings = new ArrayList<>();
        if (type2.equals("none")){
            typings.add(type1);
            return typings;
        }else {
            typings.add(type1);
            typings.add(type2);
            return typings;
        }
    }

    public String getTypingString() {
        if (type2.equals("none")){
            return type1;
        }else {
            return type1 + "/" + type2;
        }
    }

    public String getAbility1() {
        return ability1;
    }

    public String getAbility2() {
        return ability2;
    }

    public String getHiddenAbility() {
        return hiddenAbility;
    }

    public List<String>  getAbilities(){
        List<String> abilities = new ArrayList<>();
        if (ability2.equals("none")){
            if (hiddenAbility.equals("none")){
                abilities.add(ability1);
                return abilities;
            }else {
                abilities.add(ability1);
                abilities.add(hiddenAbility);
                return abilities;
            }
        }else{
            if (hiddenAbility.equals("none")){
                abilities.add(ability1);
                abilities.add(ability2);
                return abilities;
            }else {
                abilities.add(ability1);
                abilities.add(ability2);
                abilities.add(hiddenAbility);
                return abilities;
            }
        }
    }

    public String getAbilitiesString(){
        if (ability2.equals("none")){
            if (hiddenAbility.equals("none")){
                return ability1;
            }else {
                return ability1 + " hidden ability : " + hiddenAbility;
            }
        }else{
            if (hiddenAbility.equals("none")){
                return ability1 + " / " + ability2;
            }else {
                return ability1 + " / " + ability2 + " hidden ability: " + hiddenAbility;
            }
        }
    }

    @Override
    public String toString() {
        return name + " a " + rarity + " type pokemon, with the type(s): " + getTypingString() + " from region: " + region + " from Generation: " + generation + " with ability(s): " + getAbilitiesString();
    }
}
