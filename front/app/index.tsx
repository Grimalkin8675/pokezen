import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';

import AppRouter from './components/AppRouter';
import pokemonsGetter from './__mocks__/pokemonsGetter';


const router = (child: JSX.Element) => <BrowserRouter>{child}</BrowserRouter>;
ReactDOM.render(
    <AppRouter getRouter={router}
               pokemonsGetter={pokemonsGetter} />,
    document.getElementById('root')
);
