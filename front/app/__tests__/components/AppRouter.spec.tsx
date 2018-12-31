import * as React from 'react';
import { shallow } from 'enzyme';
import { MemoryRouter } from 'react-router-dom';

import AppRouter from '../../components/AppRouter';
import SearchPokemon from '../../components/SearchPokemon';
import NotMatch from '../../components/NotMatch';


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

        it('shouldn\'t contain NotMatch component', () => {
            const noMatch = shallow(<NotMatch/>);
            const rootIncludesNotMatch =
                root.html().includes(noMatch.html());
            expect(rootIncludesNotMatch).toBe(false);
        });
    });

    describe('route /kaboum', () => {
        const kaboum = shallow(
            <MemoryRouter initialEntries={['/kaboum']}>
                <AppRouter/>
            </MemoryRouter>
        );

        it('should contain NotMatch component', () => {
            const noMatch = shallow(<NotMatch/>);
            const kaboumIncludesNotMatch =
                kaboum.html().includes(noMatch.html());
            expect(kaboumIncludesNotMatch).toBe(true);
        });
    });
});
