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
        it('should parse a string[] to Names', () => {
            expect(Names.fromAny(['foo', 'bar']))
            .toEqual(new Names(new Name('foo'), new Name('bar')));
        });

        it('should be null for a boolean', () => {
            expect(Names.fromAny(true)).toBeNull();
        });

        it('should be null for a number Array', () => {
            expect(Names.fromAny([1,2,3])).toBeNull();
        });
    });
});
