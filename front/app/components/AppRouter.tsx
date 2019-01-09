import * as React from 'react';
import { Route, Switch } from 'react-router-dom';

import SearchPokemon, { IPokemonsGetter } from './SearchPokemon';
import NotMatch from './NotMatch';


interface IProps {
    getRouter: (child: JSX.Element) => JSX.Element;
    pokemonsGetter: IPokemonsGetter;
}

export default class AppRouter extends React.Component<IProps> {
    render() {
        const searchPokemon = () =>
            <SearchPokemon getter={this.props.pokemonsGetter}/>;
        const notMatch = () => <NotMatch/>;

        return this.props.getRouter(
            <Switch>
                <Route path='/' exact={true} render={searchPokemon} />
                <Route render={notMatch} />
            </Switch>
        );
    }
}
