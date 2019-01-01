import Names from '../Names';
import Name from '../Name';


describe(Names, () => {
    describe('constructor', () => {
        it('should construct', () => {
            expect(new Names(new Name('foo'), new Name('bar')))
                .toBeInstanceOf(Names);
        });
    });
});
