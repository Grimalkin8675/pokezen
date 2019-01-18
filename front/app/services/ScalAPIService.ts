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
}
