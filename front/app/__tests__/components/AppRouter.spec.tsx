import * as React from 'react';
import { shallow } from 'enzyme';
import { MemoryRouter } from 'react-router-dom';

import AppRouter from '../../components/AppRouter';
import SearchPokemon from '../../components/SearchPokemon';
import NotMatch from '../../components/NotMatch';
import pokemonsGetter from '../../__mocks__/pokemonsGetter';


describe(AppRouter, () => {
    describe('route /', () => {
        const rootRouter = (child: JSX.Element) =>
            <MemoryRouter initialEntries={['/']}>{child}</MemoryRouter>;
        const root = shallow(<AppRouter getRouter={rootRouter} />);

        it('should contain SearchPokemon component', () => {
            const searchPokemon = shallow(
                <SearchPokemon getter={pokemonsGetter} />
            );
            const rootIncludesSearchPokemon =
                root.html().includes(searchPokemon.html());
            expect(rootIncludesSearchPokemon).toBe(true);
        });


        it('shouldn\'t contain NotMatch component', () => {
            const noMatch = shallow(<NotMatch />);
            const rootIncludesNotMatch =
                root.html().includes(noMatch.html());
            expect(rootIncludesNotMatch).toBe(false);
        });
    });

    describe('route /kaboum', () => {
        const kaboumRouter = (child: JSX.Element) =>
            <MemoryRouter initialEntries={['/kaboum']}>{child}</MemoryRouter>;
        const kaboum = shallow(<AppRouter getRouter={kaboumRouter} />);

        it('should contain NotMatch component', () => {
            const noMatch = shallow(<NotMatch/>);
            const kaboumIncludesNotMatch =
                kaboum.html().includes(noMatch.html());
            expect(kaboumIncludesNotMatch).toBe(true);
        });
    });
});
