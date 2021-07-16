console.log('script imported')

//DOMContentLoaded so the event runs when the page is fully loaded
document.addEventListener("DOMContentLoaded", () =>{

    let genPokemonButton = document.querySelector('#generate-all-pokemon');
    let loginButton      = document.querySelector('#login-btn');

    genPokemonButton.addEventListener('click', renderAll)
    // loginButton.addEventListener('click', )
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