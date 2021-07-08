console.log('script imported')

function seachFunction() {
  const seachedPokemonName = document.getElementById("searchForm");
  console.log('searching for : "'+seachedPokemonName.elements[0].value + '"');
  let pokemonName = seachedPokemonName.elements[0].value
  let returnValue = '' + pokemonName
  return returnValue
}

document.addEventListener("DOMContentLoaded", () =>{

    let generateBtn = document.querySelector('#generate-pokemon');
    generateBtn.addEventListener('click', renderSeachedPokemon)

    displayDeleteButton().addEventListener('click', deleteEverything);
})

function renderSeachedPokemon(){
    let allPokemonContainer = document.querySelector('#search-container')
    allPokemonContainer.innerText = "";
    fetchSeachedPokemon();

    displayDeleteButton().style.display = 'block'
}

function displayDeleteButton(){
    return document.querySelector('#delete-btn')
}

function fetchSeachedPokemon(){
    fetch('https://pokeapi.co/api/v2/pokemon/' + seachFunction())
    .then(response => response.json())
    .then(function(pokemon){
        parseRenderedInfo(pokemon)
    })
}

function parseRenderedInfo(pokeData){
    let allPokemonContainer = document.getElementById('search-container');
    let pokeContainer = document.createElement("div")
    pokeContainer.classList.add('ui', 'card');

    grabPokemonSprite(pokeData.id, pokeContainer);
    grabPokemonShinySprite(pokeData.id, pokeContainer);

    let pokemonName = document.createElement('h3')
    pokemonName.innerText = " #" + pokeData.id + " " + pokeData.name

    let pokemonTypes = document.createElement('ul')
    grabPokemonTypes(pokeData.types, pokemonTypes) 

    let pokemonAbilities = document.createElement('ul')
    grabPokemonAbilities(pokeData.abilities, pokemonAbilities) 

    let pokemonMoves = document.createElement('ul')
    createPokemonMoves(pokeData.moves, pokemonMoves) 

    let typeLabel = document.createElement("label")
    typeLabel.innerText = "type(s):"

    let abilityLabel = document.createElement("label")
    abilityLabel.innerText = "Possible pokemon abilities:"

    let moveLabel = document.createElement("label")
    moveLabel.innerText = "Learnable pokemon moves:"

    pokeContainer.append(pokemonName, typeLabel, pokemonTypes, abilityLabel, pokemonAbilities, moveLabel, pokemonMoves);   //appending all details to the pokeContainer div
    allPokemonContainer.appendChild(pokeContainer);
}

function grabPokemonTypes(types, ul){
    types.forEach(function(type){
        let typeLi = document.createElement('li');
        typeLi.innerText = type['type']['name'];
        ul.append(typeLi)
    })
}

function grabPokemonAbilities(abilities, ul){
    abilities.forEach(function(ability){
        let abilityLi = document.createElement('li');
        abilityLi.innerText = ability['ability']['name'];
        ul.append(abilityLi)
    })
}

function createPokemonMoves(moves, ul){
    moves.forEach(function(move){
        let moveLi = document.createElement('li');
        moveLi.innerText = move['move']['name']
        ul.append(moveLi)
    })
}

function grabPokemonSprite(pokemonID, containerDiv){
    let pokemonShinyContainer = document.createElement('div')
    pokemonShinyContainer.classList.add('image')

    let pokemonSprite = document.createElement('img')
    pokemonSprite.srcset = `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonID}.png`

    pokemonShinyContainer.append(pokemonSprite);
    containerDiv.append(pokemonShinyContainer);
}

function grabPokemonShinySprite(pokemonID, containerDiv){
    let pokemonShinySpriteContainer = document.createElement('div')
    pokemonShinySpriteContainer.classList.add('image')

    let pokemonShinySprite = document.createElement('img')
    pokemonShinySprite.srcset = `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/${pokemonID}.png`

    pokemonShinySpriteContainer.append(pokemonShinySprite);
    containerDiv.append(pokemonShinySpriteContainer);
}

function deleteEverything(event){
    event.target.style = 'none';
    let allPokemonContainer = document.querySelector('#search-container')
    allPokemonContainer.innerText = ""
}
//search-container