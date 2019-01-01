import * as React from 'react';
import { Route, Switch } from 'react-router-dom';

import SearchPokemon from './SearchPokemon';
import NotMatch from './NotMatch';
import pokemonsGetter from '../__mocks__/pokemonsGetter';


export default class AppRouter extends React.Component {
    render() {
        const searchPokemon = () => <SearchPokemon getter={pokemonsGetter}/>;
        const notMatch = () => <NotMatch/>;

        return (
            <Switch>
                <Route path='/' exact={true} render={searchPokemon}/>
                <Route render={notMatch}/>
            </Switch>
        );
    }
}
