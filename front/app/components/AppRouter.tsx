import * as React from 'react';
import { Route } from 'react-router-dom';

import SearchPokemon from './SearchPokemon';


export default class AppRouter extends React.Component {
    render() {
        return (
            <div>
                <Route path='/' exact={true} component={SearchPokemon}/>
            </div>
        );
    }
}
