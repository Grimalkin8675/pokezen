import * as utils from '../app/utils';
import Name from '../app/Name';
import Type from '../app/Type';
import Stat from '../app/Stat';


describe('utils', () => {
    describe(utils.hoverComparedStat, () => {
        it('should return that a pokemon has more stat than average', () => {
            expect(
                utils.hoverComparedStat(
                    new Name('pikachu'),
                    new Stat('speed', 3),
                    new Type('electric')
                )
            ).toBe('Pikachu has 3 more base speed compared to the average electric pokemon.');
        });
    });
});
