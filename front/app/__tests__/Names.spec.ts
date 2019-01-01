import Names from '../Names';
import Name from '../Name';


describe(Names, () => {
    describe('constructor', () => {
        it('should construct', () => {
            expect(new Names(new Name('foo'), new Name('bar')))
            .toBeInstanceOf(Names);
        });
    });

    describe('fromAny', () => {
        it('should be valid for a string[]', () => {
            expect(Names.fromAny(['foo', 'bar']))
            .toEqual(new Names(new Name('foo'), new Name('bar')));
        });
    });
});
