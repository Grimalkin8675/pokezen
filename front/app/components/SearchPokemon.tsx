import * as React from 'react';


export default class SearchPokemon extends React.Component {
    render() {
        return (
            <div className='SearchPokemon'>
                <input type='text' placeholder='Search for pokemons!' />
            </div>
        );
    }
}
