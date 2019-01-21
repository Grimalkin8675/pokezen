import * as React from 'react';
import { Route, Switch, RouteComponentProps } from 'react-router-dom';

import NotMatch from './NotMatch';
import PokemonDetails, { IPokemonDetailsGetter } from './PokemonDetails';
import SearchPokemon, { IPokemonsGetter } from './SearchPokemon';
import Name from '../Name';


interface IProps {
    getRouter: (child: JSX.Element) => JSX.Element;
    pokemonsGetter: IPokemonsGetter;
    pokemonDetailsGetter: IPokemonDetailsGetter;
}

export default class AppRouter extends React.Component<IProps> {
    render() {
        const searchPokemon = () =>
            <SearchPokemon getter={this.props.pokemonsGetter}/>;
        const pokemonDetails = (match: RouteComponentProps<{ name: string }>) =>
            <PokemonDetails name={new Name(match.match.params.name)}
                            getter={this.props.pokemonDetailsGetter} />;
        const notMatch = () => <NotMatch/>;

        return this.props.getRouter(
            <Switch>
                <Route path='/' exact={true} render={searchPokemon} />
                <Route path='/pokemon/:name' exact={true}
                       render={pokemonDetails} />
                <Route render={notMatch} />
            </Switch>
        );
    }
}
