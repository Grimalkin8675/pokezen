import * as React from 'react';
import { shallow } from 'enzyme';
import { MemoryRouter } from 'react-router-dom';

import AppRouter from '../../components/AppRouter';
import SearchPokemon from '../../components/SearchPokemon';
import NotFound404 from '../../components/NotFound404';


describe(AppRouter, () => {
    describe('route /', () => {
        const defaultRoute = shallow(
            <MemoryRouter initialEntries={['/']}>
                <AppRouter/>
            </MemoryRouter>
        );

        it('should contain SearchPokemon component', () => {
            const searchPokemon = shallow(<SearchPokemon/>);
            const defaultRouteIncludesSearchPokemon =
                defaultRoute.html().includes(searchPokemon.html());
            expect(defaultRouteIncludesSearchPokemon).toBe(true);
        });

        it('shouldn\'t contain NotFound404 component', () => {
            const notFound404 = shallow(<NotFound404/>);
            const defaultRouteIncludesNotFound404 =
                defaultRoute.html().includes(notFound404.html());
            expect(defaultRouteIncludesNotFound404).toBe(false);
        });
    });
});
