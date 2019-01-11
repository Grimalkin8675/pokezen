import { IPokemonsGetter } from '../components/SearchPokemon';
import Names from '../Names';


export interface IWSClient {
    get: (url: string) => Promise<any>;
}

export default class ScalAPIService implements IPokemonsGetter {
    private wsClient: IWSClient;

    constructor(wsClient: IWSClient) {
        this.wsClient = wsClient;
    }

    get pokemons(): Promise<Names | null> {
        return this.wsClient
            .get(`/pokemons`)
            .then(response => Names.fromAny(response));
    }
}
