import { IPokemonsGetter } from '../components/SearchPokemon';
import Names from '../Names';


export interface IWSClient {
    get: (url: string) => Promise<any>;
}

export default class ScalAPIService implements IPokemonsGetter {
    private wsClient: IWSClient;
    private _pokemons: Names | null = null;

    constructor(wsClient: IWSClient) {
        this.wsClient = wsClient;
    }

    get pokemons(): Promise<Names | null> {
        if (this._pokemons !== null) {
            return new Promise(resolve => resolve(this._pokemons));
        }
        return this.wsClient
            .get(`/pokemons`)
            .then(response => {
                this._pokemons = Names.fromAny(response);
                return this._pokemons;
            });
    }
}
