import Names from '../Names';


export interface IPokemonsGetter {
    pokemons: Promise<Names>;
}

export default class ScalAPIService {
    private static _instance: ScalAPIService | null = null;

    private constructor() {
    }

    static get instance(): ScalAPIService {
        if (ScalAPIService._instance === null) {
            ScalAPIService._instance = new ScalAPIService();
        }
        return ScalAPIService._instance;
    }
}
