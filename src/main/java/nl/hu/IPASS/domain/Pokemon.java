package nl.hu.IPASS.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Pokemon {
    @Id
    private int pokedexNumber;
    private String name;
    private String typing;
    private int    generation;
    private String rarity;
    private String region;
    private String abilities;
    private boolean hasHiddenAbility;

    public Pokemon(int pokedexNumber, String name, String typing, int gen, String region, String rarity, String abilities){
        this.pokedexNumber    = pokedexNumber;
        this.name             = name;
        this.typing           = typing;
        this.generation       = gen;
        this.region           = region;
        this.rarity           = rarity;
        this.abilities        = abilities;
        this.hasHiddenAbility = true;
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
        String[] rawTypeList = typing.split(", ", 2);
        List<String> typings = new ArrayList<>();
        String type1 = rawTypeList[0];
        String type2 = rawTypeList[1];
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
        String[] rawTypeList = typing.split(", ", 2);
        String type1 = rawTypeList[0];
        String type2 = rawTypeList[1];
        if (type2.equals("none")){
            return type1;
        }else {
            return type1 + "/" + type2;
        }
    }

    public String getAbility1() {
        String[] rawAbilityList = abilities.split(", ", 3);
        String ability1 = rawAbilityList[0];
        return ability1;
    }

    public String getAbility2() {
        String ability2 = "";
        String[] rawAbilityList = abilities.split(", ", 3);
        if (rawAbilityList.length ==2 && !hasHiddenAbility || rawAbilityList.length == 3){
            ability2 = rawAbilityList[1];
        }
        return ability2;
    }

    public String getHiddenAbility() {
        String hiddenAbility = "";
        String[] rawAbilityList = abilities.split(", ", 3);
        if (rawAbilityList.length ==2 && hasHiddenAbility || rawAbilityList.length == 3){
            hiddenAbility = rawAbilityList[2];
        }
        return hiddenAbility;
    }

    public List<String>  getAbilities(){
        List<String> abilitiesList = new ArrayList<>();
        String[] rawAbilityList = abilities.split(", ", 3);
        Collections.addAll(abilitiesList, rawAbilityList);
        return abilitiesList;
    }

    public String getAbilitiesString(){
        return abilities;
    }

    @Override
    public String toString() {
        return name + " a " + rarity + " type pokemon, with the type(s): " + getTypingString() + " | from region: " + region + " from Generation: " + generation + " | with ability(s): " + getAbilitiesString() + " |";
    }
}
