import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import Axios from 'axios';

import config from './config';
import AppRouter from './components/AppRouter';
import ScalAPIService, { IWSClient } from './services/ScalAPIService';


const router = (child: JSX.Element) => <BrowserRouter>{child}</BrowserRouter>;
const api = Axios.create({
    baseURL: `http://${config.apiHost}:${config.apiPort}`
});
const wsClient: IWSClient = {
    get: (url: string) => api.get(url).then(response => response.data)
};
const scalAPIService = new ScalAPIService(wsClient);

ReactDOM.render(
    <AppRouter getRouter={router}
               pokemonsGetter={scalAPIService}
               pokemonDetailsGetter={scalAPIService} />,
    document.getElementById('root')
);
