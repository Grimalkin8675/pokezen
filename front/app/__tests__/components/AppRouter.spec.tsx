import * as React from 'react';
import { shallow } from 'enzyme';
import { MemoryRouter } from 'react-router-dom';

import AppRouter from '../../components/AppRouter';
import SearchPokemon from '../../components/SearchPokemon';


describe(AppRouter, () => {
    describe('route /', () => {
        it('should contain SearchPokemon component', () => {
            const router = shallow(
                <MemoryRouter initialEntries={['/']}>
                    <AppRouter/>
                </MemoryRouter>
            );
            const searchPokemon = shallow(<SearchPokemon/>);
            expect(router.html().includes(searchPokemon.html())).toBe(true);
        });
    });
});
