import { IPokemonsGetter } from '../components/SearchPokemon';
import { IPokemonDetailsGetter } from '../components/PokemonDetails';
import Names from '../Names';
import ComparedPokemon from '../ComparedPokemon';
import Name from '../Name';


export interface IWSClient {
    get: (url: string) => Promise<any>;
}

export default class ScalAPIService implements IPokemonsGetter,
                                               IPokemonDetailsGetter {
    private wsClient: IWSClient;
    private _pokemons: Names | null = null;
    private _pokemonDetails: { [name: string]: ComparedPokemon } = {};

    constructor(wsClient: IWSClient) {
        this.wsClient = wsClient;
    }

    pokemons(): Promise<Names> {
        if (this._pokemons !== null) {
            return new Promise(resolve => resolve(this._pokemons as Names));
        }
        return this.wsClient
            .get(`/pokemons`)
            .then(response => {
                const res = Names.fromAny(response);
                if (res === null) throw Error('Couldn\'t parse response');
                this._pokemons = res;
                return res;
            });
    }

    pokemonDetails(name: Name): Promise<ComparedPokemon> {
        if (this._pokemonDetails.hasOwnProperty(name.toString())) {
            return new Promise(resolve => resolve(
                this._pokemonDetails[name.toString()]
            ));
        }
        return this.wsClient
            .get(`/pokemon/${name}`)
            .then(response => {
                const res = ComparedPokemon.fromAny(response);
                if (res === null) throw Error('Couldn\'t parse response');
                this._pokemonDetails[name.toString()] = res;
                return res;
            });
    }
}
