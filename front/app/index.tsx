import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import axios from 'axios';

import config from './config';
import AppRouter from './components/AppRouter';
import ScalAPIService, { IWSClient } from './services/ScalAPIService';


const router = (child: JSX.Element) => <BrowserRouter>{child}</BrowserRouter>;
const api = axios.create({
    baseURL: `http://${config.apiHost}:${config.apiPort}`
});
const wsClient: IWSClient = {
    get: (url: string) => api.get(url).then(response => response.data)
};

ReactDOM.render(
    <AppRouter getRouter={router}
               pokemonsGetter={new ScalAPIService(wsClient)} />,
    document.getElementById('root')
);
