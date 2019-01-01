import { IPokemonsGetter } from "../services/ScalAPIService";
import Name from "../Name";

const pokemonsGetter: IPokemonsGetter = {
    getPokemons: (): Promise<Name[]> =>
        new Promise((resolve) => {
            resolve([
                new Name("bar"),
                new Name("foo"),
            ]);
        })
};

export default pokemonsGetter;
