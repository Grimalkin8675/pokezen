import * as React from 'react';
import { Route, Switch } from 'react-router-dom';

import SearchPokemon from './SearchPokemon';
import NotMatch from './NotMatch';


export default class AppRouter extends React.Component {
    render() {
        return (
            <Switch>
                <Route path='/' exact={true} component={SearchPokemon}/>
                <Route component={NotMatch}/>
            </Switch>
        );
    }
}
