import * as React from 'react';

import { IPokemonsGetter } from '../services/ScalAPIService';


interface IProps {
    getter: IPokemonsGetter;
}

export default class SearchPokemon extends React.Component<IProps> {
    render() {
        return (
            <div className='SearchPokemon'>
                <input type='text' placeholder='Search for pokemons!' />
            </div>
        );
    }
}
