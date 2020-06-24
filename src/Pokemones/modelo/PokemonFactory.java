package Pokemones.modelo;

public class PokemonFactory {

    public Pokemon getPokemon(String tipoPokemon, String nombrePokemon){
        if(tipoPokemon==null){
            return null;
        }else if(tipoPokemon.equalsIgnoreCase("AGUA")){
            return new PokemonAgua(nombrePokemon);
        }else if(tipoPokemon.equalsIgnoreCase("FANTASMA")){
            return new PokemonFantasma(nombrePokemon);
        }else if(tipoPokemon.equalsIgnoreCase("FUEGO")){
            return new PokemonFuego(nombrePokemon);
        }else if(tipoPokemon.equalsIgnoreCase("HIELO")){
            return new PokemonHielo(nombrePokemon, true);
        }else if(tipoPokemon.equalsIgnoreCase("PLANTA")){
            return new PokemonPlanta(nombrePokemon);
        }else{
            return null;
        }
    }

    public Pokemon getPokemon(String tipoPokemon, String nombrePokemon, boolean recarga){
        if(tipoPokemon==null){
            return null;
        }else if(tipoPokemon.equalsIgnoreCase("AGUA")){
            return new PokemonAgua(nombrePokemon);
        }else if(tipoPokemon.equalsIgnoreCase("FANTASMA")){
            return new PokemonFantasma(nombrePokemon);
        }else if(tipoPokemon.equalsIgnoreCase("FUEGO")){
            return new PokemonFuego(nombrePokemon);
        }else if(tipoPokemon.equalsIgnoreCase("HIELO")){
            return new PokemonHielo(nombrePokemon, recarga);
        }else if(tipoPokemon.equalsIgnoreCase("PLANTA")){
            return new PokemonPlanta(nombrePokemon);
        }else{
            return null;
        }
    }
}
