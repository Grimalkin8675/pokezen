import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import axios from 'axios';

import AppRouter from './components/AppRouter';
import ScalAPIService from './services/ScalAPIService';


const router = (child: JSX.Element) => <BrowserRouter>{child}</BrowserRouter>;
ReactDOM.render(
    <AppRouter getRouter={router}
               pokemonsGetter={new ScalAPIService(axios)} />,
    document.getElementById('root')
);
