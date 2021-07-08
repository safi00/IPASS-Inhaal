//package nl.hu.IPASS.domain;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class DomainTest {
//    private User user1;
//    private User user2;
//    private Employee emp1;
//    private Employee emp2;
//    private Pokemon gliscor;
//    private Pokemon salamence;
//    private Pokemon snorlax;
//    private Pokemon rayquaza;
//    private Pokemon mew;
//
//    @BeforeEach
//    public void init(){
//        user1      = new User("safi","12345","safi@hotmail.com","user","rishi");
//        user2      = new User("eddy","12345","eddddd@hotmail.com","user","edd");
//        emp1       = new Employee("loki","12345","dumbo@hotmail.com","employee","mod","dumbo");
//        emp2       = new Employee("fareep","12345","fareep@hotmail.com","employee", "admin","far");
//        gliscor    = new Pokemon(472,"Gliscor", "Ground","Flying", 4, "Sinnoh","common", "Hyper Cutter", "Sand Veil","Poison Heal");
//        salamence  = new Pokemon(373,"Salamence", "Dragon","Flying", 3,"Hoenn","common","Intimidate","none","Moxie");
//        snorlax    = new Pokemon(143,"Snorlax", "Normal","none", 1,"Kanto","common","Immunity","Thick Fat","Gluttony");
//        rayquaza   = new Pokemon(384,"Rayquaza", "Dragon","Flying", 3,"Hoenn","legendary","Air Lock","none","none");
//        mew        = new Pokemon(151,"Mew", "Psychic","none", 1,"Kanto","mythical","Synchronize","none","none");
//    }
//
//    @Test
//    public void testUser1AccDetails(){
//        user1.setEmail("safi_GO@hotmail.com");
//        assertEquals(user1.toString(), "#0 account type:user by user safi can be contacted by safi_GO@hotmail.com");
//    }
//
//    @Test
//    public void testUser1UserDetails(){
//        user1.setAboutMe("i'm awkward");
//        user1.setEmail("safi_GO@hotmail.com");
//        user1.setName("Rishino");
//        user1.setPassword("34512");
//        assertEquals(user1.getUserDetails(), "#0 account type:user by user safi can be contacted by safi_GO@hotmail.com name : Rishino self description : i'm awkward");
//    }
//
//    @Test
//    public void testUser1vsUser2(){
//        assertNotEquals(user1, user2);
//    }
//
//    @Test
//    public void testEmp1vsEmp2(){
//        assertNotEquals(emp1, emp2);
//    }
//
//    @Test
//    public void testUser1addPokemon(){
//        user1.addPokemon(gliscor);
//        assertEquals(user1.getPokemonList().toString(), "[Gliscor a common type pokemon, with the type(s): Ground/Flying from region: Sinnoh from Generation: 4 with ability(s): Hyper Cutter / Sand Veil hidden ability: Poison Heal]");
//    }
//
//    @Test
//    public void testUser1totalPokemon(){
//        user1.addPokemon(gliscor);
//        assertEquals(user1.getTotalPokemon(), 1);
//    }
//
//    @Test
//    public void testUser1TotalPokemons(){
//        user1.addPokemon(gliscor);
//        user1.addPokemon(salamence);
//        user1.addPokemon(snorlax);
//        user1.addPokemon(rayquaza);
//        user1.addPokemon(mew);
//        user1.addPokemonToFav(gliscor);
//        assertEquals(user1.getTotalPokemon(), 5);
//    }
//
//    @Test
//    public void testUser1removePokemon(){
//        user1.addPokemon(gliscor);
//        user1.removePokemon(gliscor);
//        assertEquals(user1.getPokemonList().toString(), "[]");
//    }
//
//    @Test
//    public void testUser1addFavPokemonWithItNotInCol(){
//        user1.addPokemonToFav(gliscor);
//        assertEquals(user1.getFavoritePokemonList().toString(), "[]");
//    }
//
//    @Test
//    public void testUser1addFavPokemon(){
//        user1.addPokemon(gliscor);
//        user1.addPokemonToFav(gliscor);
//        assertEquals(user1.getFavoritePokemonList().toString(), "[Gliscor a common type pokemon, with the type(s): Ground/Flying from region: Sinnoh from Generation: 4 with ability(s): Hyper Cutter / Sand Veil hidden ability: Poison Heal]");
//    }
//
//    @Test
//    public void testUser1removeFavPokemon(){
//        user1.addPokemon(gliscor);
//        user1.addPokemonToFav(gliscor);
//        user1.removePokemon(gliscor);
//        user1.removePokemonFromFav(gliscor);
//        assertEquals(user1.getFavoritePokemonList().toString(), "[]");
//    }
//}