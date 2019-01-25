import Type from '../../app/models/Type';

describe(Type, () => {
    describe('upper()', () => {
        it('should apply lodash capitalize', () => {
            const upper = new Type('foo').upper();
            expect(typeof upper).toBe('string');
            expect(upper).toBe('Foo');
        });
    });
});
