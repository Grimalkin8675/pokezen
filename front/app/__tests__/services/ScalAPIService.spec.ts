import ScalAPIService, { IWSClient } from '../../services/ScalAPIService';
import Name from '../../Name';
import Names from '../../Names';


describe(ScalAPIService, () => {
    const validGetter: IWSClient = {
        get: () => new Promise(resolve => resolve(['foo'])),
    };

    describe('constructor', () => {
        it('should create a ScalAPIService', () => {
            const service = new ScalAPIService(validGetter);
            expect(service).toBeInstanceOf(ScalAPIService);
        });
    });

    describe('get pokemons()', () => {
        it('should return a parsed Names for a valid response', () => {
            return expect(new ScalAPIService(validGetter).pokemons()).resolves
                .toEqual(new Names(new Name('foo')));
        });

        it('should return null for an invalid response', () => {
            const invalidGetter: IWSClient = {
                get: () => new Promise(resolve => resolve('not an array')),
            };
            return expect(new ScalAPIService(invalidGetter).pokemons()).rejects
                .toEqual(new Error('Couldn\'t parse response'));
        });
    });
});
