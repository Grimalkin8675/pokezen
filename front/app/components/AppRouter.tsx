import * as React from 'react';
import { Route, Switch } from 'react-router-dom';

import NotMatch from './NotMatch';
import Pokemon from './Pokemon';
import SearchPokemon, { IPokemonsGetter } from './SearchPokemon';


interface IProps {
    getRouter: (child: JSX.Element) => JSX.Element;
    pokemonsGetter: IPokemonsGetter;
}

export default class AppRouter extends React.Component<IProps> {
    render() {
        const searchPokemon = () =>
            <SearchPokemon getter={this.props.pokemonsGetter}/>;
        const pokemon = () => <Pokemon />;
        const notMatch = () => <NotMatch/>;

        return this.props.getRouter(
            <Switch>
                <Route path='/' exact={true} render={searchPokemon} />
                <Route path='/pokemon/:name' exact={true} render={pokemon} />
                <Route render={notMatch} />
            </Switch>
        );
    }
}
