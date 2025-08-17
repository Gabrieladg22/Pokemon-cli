package org.example.services;

import org.example.models.Pokemon;
import org.example.models.Results;
import org.springframework.web.client.RestClient;

import java.util.List;

public class PokemonService {

    private final String API_URL = "https://pokeapi.co/api/v2/pokemon";

    private RestClient restClient = RestClient.create();

    public List<Pokemon> getAllPokemon(){
        Results rs = restClient
                .get()
                .uri(API_URL)
                .retrieve()
                .body(Results.class);

        return rs.getResults();
    }

    //https://pokeapi.co/api/v2/pokemon?offset=40&limit=20
    public List<Pokemon> getMorePokemon(int startVal){
        String moreURL = API_URL + "?offset=" + startVal + "&limit=20";
        Results rs = restClient
                .get()
                .uri(moreURL)
                .retrieve()
                .body(Results.class);
        List<Pokemon> list = rs.getResults();
        //I want to loop through the list of pokemon object
        //I want to parse out the id number and add it to each pokemon object
        for (Pokemon p : list){
            String url = p.getUrl();
            //Index Of returns the index of that first p or pokemon
            int pokemonIndex = url.indexOf("pokemon/");

            String pokemonUrl = url.substring(pokemonIndex);

            int slashIndex = pokemonUrl.indexOf("/");
            int pokemonId =
                    Integer.parseInt(pokemonUrl.substring(slashIndex + 1, pokemonUrl.length() - 1));

            p.setId(pokemonId);
        }
        return list;
    }

}
