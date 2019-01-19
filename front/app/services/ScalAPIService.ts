import { IPokemonsGetter } from '../components/SearchPokemon';
import { IPokemonDetailsGetter } from '../components/Pokemon';
import Names from '../Names';
import ComparedPokemon from '../ComparedPokemon';
import Name from '../Name';
import ImageURL from '../ImageURL';
import Types from '../Types';
import Type from '../Type';
import Stats from '../Stats';
import Stat from '../Stat';
import ComparedStat from '../ComparedStat';


export interface IWSClient {
    get: (url: string) => Promise<any>;
}

export default class ScalAPIService implements IPokemonsGetter,
                                               IPokemonDetailsGetter {
    private wsClient: IWSClient;
    private _pokemons: Names | null = null;

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
        return new Promise(resolve => resolve(
            new ComparedPokemon(
                name,
                new ImageURL('some url'),
                new Types(new Type('fire')),
                new Stats(new Stat('def', 50)),
                new ComparedStat(
                    'def',
                    {
                        fire: 3
                    }
                )
            )
        ));
    }
}
