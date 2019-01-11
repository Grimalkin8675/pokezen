import Names from '../Names';
import Name from '../Name';


describe(Names, () => {
    describe('constructor', () => {
        it('should construct', () => {
            expect(new Names(new Name('foo'), new Name('bar')))
            .toBeInstanceOf(Names);
        });
    });

    describe('fromAny(thing)', () => {
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

    describe('isEmpty()', () => {
        it('should return true when it contains no names', () => {
            expect(new Names().isEmpty()).toBe(true);
        });

        it('should return false when it contains at least one name', () => {
            expect(new Names(new Name('foo')).isEmpty()).toBe(false);
        });
    });

    describe('map((name, i) => transformedName)', () => {
        it('should return an Array', () => {
            expect(new Names().map((name: Name) => name)).toBeInstanceOf(Array);
        });

        it('should map the values', () => {
            const names = new Names(new Name('foo'), new Name('bar'));
            expect(names.map(name => name.toString())).toEqual(['foo', 'bar']);
        });
    });

    describe('filter(substr)', () => {
        it('should return a Names', () => {
            expect(new Names(new Name('bar')).filter('foo')).toEqual(new Names());
        });

        it('should return same Names for an empty string', () => {
            const names = new Names(new Name('foo'));
            expect(names.filter('')).toEqual(names);
        });

        it('should return same Names for an empty trimed string', () => {
            const names = new Names(new Name('foo'));
            expect(names.filter('  ')).toEqual(names);
        });

        it('should return a complete matching Name', () => {
            const received = new Names(new Name('foo'), new Name('bar'));
            const expected = new Names(new Name('foo'));
            expect(received.filter(' foo')).toEqual(expected);
        });
    });
});
