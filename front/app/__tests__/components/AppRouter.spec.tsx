import * as React from 'react';
import { shallow } from 'enzyme';
import { MemoryRouter } from 'react-router-dom';

import AppRouter from '../../components/AppRouter';
import SearchPokemon from '../../components/SearchPokemon';
import NotFound404 from '../../components/NotFound404';


describe(AppRouter, () => {
    describe('route /', () => {
        const root = shallow(
            <MemoryRouter initialEntries={['/']}>
                <AppRouter/>
            </MemoryRouter>
        );

        it('should contain SearchPokemon component', () => {
            const searchPokemon = shallow(<SearchPokemon/>);
            const rootIncludesSearchPokemon =
                root.html().includes(searchPokemon.html());
            expect(rootIncludesSearchPokemon).toBe(true);
        });

        it('shouldn\'t contain NotFound404 component', () => {
            const notFound404 = shallow(<NotFound404/>);
            const rootIncludesNotFound404 =
                root.html().includes(notFound404.html());
            expect(rootIncludesNotFound404).toBe(false);
        });
    });

    describe('route /kaboum', () => {
        const kaboum = shallow(
            <MemoryRouter initialEntries={['/kaboum']}>
                <AppRouter/>
            </MemoryRouter>
        );

        it('should contain NotFound404 component', () => {
            const notFound404 = shallow(<NotFound404/>);
            const kaboumIncludesNotFound404 =
                kaboum.html().includes(notFound404.html());
            expect(kaboumIncludesNotFound404).toBe(true);
        });
    });
});
