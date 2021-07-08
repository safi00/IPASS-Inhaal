console.log('script imported')

//DOMContentLoaded so the event runs when the page is fully loaded
document.addEventListener("DOMContentLoaded", () =>{

    let genPokemonButton = document.querySelector('#generate-all-pokemon');
    genPokemonButton.addEventListener('click', renderAll)

    displayDeleteButton().addEventListener('click', deleteEverything);
})

function renderAll(){
    console.log('all pokemon are Being Fetched')
    let container = document.querySelector('#poke-container')
    container.innerText = "";
    fetchAllPokemon();

    displayDeleteButton().style.display = 'block'
    console.log('pokemon list is being displayed')
}

document.addEventListener("DOMContentLoaded", () =>{

    let genPokemonButton = document.querySelector('#alola');
    genPokemonButton.addEventListener('click', renderAlola)

    displayDeleteButton().addEventListener('click', deleteEverything);
})

function renderAlola(){
    console.log('alola pokemon are Being Fetched')
    let container = document.querySelector('#poke-container')
    container.innerText = "";
    fetchAlolaPokemon();

    displayDeleteButton().style.display = 'block'
    console.log('pokemon list is being displayed')
}

document.addEventListener("DOMContentLoaded", () =>{

    let genPokemonButton = document.querySelector('#galar');
    genPokemonButton.addEventListener('click', renderGalar)

    displayDeleteButton().addEventListener('click', deleteEverything);
})

function renderGalar(){
    console.log('galar pokemon are Being Fetched')
    let container = document.querySelector('#poke-container')
    container.innerText = "";
    fetchGalarPokemon();

    displayDeleteButton().style.display = 'block'
    console.log('pokemon list is being displayed')
}

document.addEventListener("DOMContentLoaded", () =>{

    let genPokemonButton = document.querySelector('#hoenn');
    genPokemonButton.addEventListener('click', renderHoenn)

    displayDeleteButton().addEventListener('click', deleteEverything);
})

function renderHoenn(){
    console.log('hoenn pokemon are Being Fetched')
    let container = document.querySelector('#poke-container')
    container.innerText = "";
    fetchHoennPokemon();

    displayDeleteButton().style.display = 'block'
    console.log('pokemon list is being displayed')
}

document.addEventListener("DOMContentLoaded", () =>{

    let genPokemonButton = document.querySelector('#johto');
    genPokemonButton.addEventListener('click', renderJohto)

    displayDeleteButton().addEventListener('click', deleteEverything);
})

function renderJohto(){
    console.log('johto pokemon are Being Fetched')
    let container = document.querySelector('#poke-container')
    container.innerText = "";
    fetchJohtoPokemon();

    displayDeleteButton().style.display = 'block'
    console.log('pokemon list is being displayed')
}

document.addEventListener("DOMContentLoaded", () =>{

    let genPokemonButton = document.querySelector('#kalos');
    genPokemonButton.addEventListener('click', renderKalos)

    displayDeleteButton().addEventListener('click', deleteEverything);
})

function renderKalos(){
    console.log('kalos pokemon are Being Fetched')
    let container = document.querySelector('#poke-container')
    container.innerText = "";
    fetchKalosPokemon();

    displayDeleteButton().style.display = 'block'
    console.log('pokemon list is being displayed')
}

document.addEventListener("DOMContentLoaded", () =>{

    let genPokemonButton = document.querySelector('#kanto');
    genPokemonButton.addEventListener('click', renderKanto)

    displayDeleteButton().addEventListener('click', deleteEverything);
})

function renderKanto(){
    console.log('kanto pokemon are Being Fetched')
    let container = document.querySelector('#poke-container')
    container.innerText = "";
    fetchKantoPokemon();

    displayDeleteButton().style.display = 'block'
    console.log('pokemon list is being displayed')
}

document.addEventListener("DOMContentLoaded", () =>{

    let genPokemonButton = document.querySelector('#sinnoh');
    genPokemonButton.addEventListener('click', renderSinnoh)

    displayDeleteButton().addEventListener('click', deleteEverything);
})

function renderSinnoh(){
    console.log('sinnoh pokemon are Being Fetched')
    let container = document.querySelector('#poke-container')
    container.innerText = "";
    fetchSinnohPokemon();

    displayDeleteButton().style.display = 'block'
    console.log('pokemon list is being displayed')
}

document.addEventListener("DOMContentLoaded", () =>{

    let genPokemonButton = document.querySelector('#unova');
    genPokemonButton.addEventListener('click', renderUnova)

    displayDeleteButton().addEventListener('click', deleteEverything);
})

function renderUnova(){
    console.log('unova pokemon are Being Fetched')
    let container = document.querySelector('#poke-container')
    container.innerText = "";
    fetchUnovaPokemon();

    displayDeleteButton().style.display = 'block'
    console.log('pokemon list is being displayed')
}

function displayDeleteButton(){
    return document.querySelector('#delete-btn')
}

function fetchAllPokemon(){
    fetch('https://pokeapi.co/api/v2/pokemon?limit=898') // here we are fetching a ton of pokemon, in a big json package and we work through all of em and trandlate em to display on the site
    .then(response => response.json())
    .then(function(pokemonList){
        pokemonList.results.forEach(function(pokemon){
            renderPokemon(pokemon);
        })
    })
}

function fetchAlolaPokemon(){
    fetch('https://pokeapi.co/api/v2/pokemon?offset=721&limit=88')
    .then(response => response.json())
    .then(function(pokemonList){
        pokemonList.results.forEach(function(pokemon){
            renderPokemon(pokemon);
        })
    })
}

function fetchGalarPokemon(){
    fetch('https://pokeapi.co/api/v2/pokemon?offset=809&limit=79')
    .then(response => response.json())
    .then(function(pokemonList){
        pokemonList.results.forEach(function(pokemon){
            renderPokemon(pokemon);
        })
    })
}

function fetchHoennPokemon(){
    fetch('https://pokeapi.co/api/v2/pokemon?offset=251&limit=135')
    .then(response => response.json())
    .then(function(pokemonList){
        pokemonList.results.forEach(function(pokemon){
            renderPokemon(pokemon);
        })
    })
}

function fetchJohtoPokemon(){
    fetch('https://pokeapi.co/api/v2/pokemon?offset=151&limit=100')
    .then(response => response.json())
    .then(function(pokemonList){
        pokemonList.results.forEach(function(pokemon){
            renderPokemon(pokemon);
        })
    })
}

function fetchKalosPokemon(){
    fetch('https://pokeapi.co/api/v2/pokemon?offset=649&limit=72')
    .then(response => response.json())
    .then(function(pokemonList){
        pokemonList.results.forEach(function(pokemon){
            renderPokemon(pokemon);
        })
    })
}

function fetchKantoPokemon(){
    fetch('https://pokeapi.co/api/v2/pokemon?limit=151')
    .then(response => response.json())
    .then(function(pokemonList){
        pokemonList.results.forEach(function(pokemon){
            renderPokemon(pokemon);
        })
    })
}

function fetchSinnohPokemon(){
    fetch('https://pokeapi.co/api/v2/pokemon?offset=386&limit=107')
    .then(response => response.json())
    .then(function(pokemonList){
        pokemonList.results.forEach(function(pokemon){
            renderPokemon(pokemon);
        })
    })
}

function fetchUnovaPokemon(){
    fetch('https://pokeapi.co/api/v2/pokemon?offset=493&limit=166') // here we are fetching a ton of pokemon
    .then(response => response.json())
    .then(function(pokemonList){
        pokemonList.results.forEach(function(pokemon){              // and then passing them individually to get there names and urls
            renderPokemon(pokemon);
        })
    })
}

function renderPokemon(pokemon){
    let url = pokemon.url // here we are fetching each pokemon to get their typings and number (pokedex number)
    fetch(url)
    .then(response => response.json())
    .then(function(pokemonData){
        parseRenderedInfo(pokemonData)
    })
}

function parseRenderedInfo(pokemon){
    let container = document.getElementById('poke-container');
    let pokeContainer = document.createElement("div")
    pokeContainer.classList.add('ui', 'card');

    grabPokemonSprite(pokemon.id, pokeContainer);

    let pokeName = document.createElement("h3")
    pokeName.innerText = ' #'+ pokemon.id + ' ' + pokemon.name

    let pokeTypes = document.createElement('ul')
    grabPokemonTypes(pokemon.types, pokeTypes) 

    pokeName.id = `${pokemon.id}` + `${pokemon.name}`

    pokeContainer.append(pokeName, pokeTypes);   //appending all details to the pokeContainer div
    container.appendChild(pokeContainer);
}

function grabPokemonTypes(types, ul){
    types.forEach(function(type){
        let typeLi = document.createElement('li');
        typeLi.innerText = type['type']['name'];
        ul.append(typeLi)
    })
}

function grabPokemonSprite(pokemonID, containerDiv){
    let pokemonImgContainer = document.createElement('div')
    pokemonImgContainer.classList.add('image')

    let pokemonSprite = document.createElement('img')
    pokemonSprite.srcset = `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonID}.png`

    pokemonImgContainer.append(pokemonSprite);
    containerDiv.append(pokemonImgContainer);
}

function deleteEverything(event){
    event.target.style = 'none';
    let container = document.querySelector('#poke-container')
    container.innerText = ""

    let genPokemonButton = document.createElement('button')
    genPokemonButton.innerText = "Generate All Pokemon"
    genPokemonButton.id = 'generate-all-pokemon'
    genPokemonButton.classList.add('globalButton')
    genPokemonButton.addEventListener('click', renderAll);

    container.append(genPokemonButton)
}