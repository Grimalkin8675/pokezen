import * as React from 'react';
import { Route, Switch } from 'react-router-dom';

import SearchPokemon from './SearchPokemon';
import NotFound404 from './NotFound404';


export default class AppRouter extends React.Component {
    render() {
        return (
            <Switch>
                <Route path='/' exact={true} component={SearchPokemon}/>
                <Route component={NotFound404}/>
            </Switch>
        );
    }
}
