import ScalAPIService, { IWSClient } from '../../app/services/ScalAPIService';
import Name from '../../app/models/Name';
import Names from '../../app/models/Names';
import ComparedPokemon from '../../app/models/ComparedPokemon';
import ImageURL from '../../app/models/ImageURL';
import Type from '../../app/models/Type';
import Stat from '../../app/models/Stat';
import ComparedStat from '../../app/models/ComparedStat';


describe(ScalAPIService, () => {
    const mockWSClient: IWSClient = {
        get: (url: string) => new Promise((resolve, reject) => {
            if (url === '/pokemons') return resolve(['foo']);
            if (url === '/pokemon/foo') return resolve({
                name: 'foo',
                image: 'an url',
                types: ['fire'],
                base_stats: [
                    {
                        name: 'def',
                        value: 50
                    }
                ],
                compared_stats: [
                    {
                        name: 'def',
                        comparisons: {
                            fire: 3,
                        }
                    }
                ]
            });
            if (url === '/pokemon/kaboum') return resolve(
                `"not a ComparedPokemon DTO"`
            );
            return reject(`Route not found ${url}`);
        }),
    };
    const mockedScalAPIService = new ScalAPIService(mockWSClient);

    describe('constructor', () => {
        it('should create a ScalAPIService', () => {
            expect(mockedScalAPIService).toBeInstanceOf(ScalAPIService);
        });
    });

    describe('pokemons()', () => {
        it('should return a parsed Names for a valid response', () => {
            return expect(mockedScalAPIService.pokemons()).resolves
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

    describe('pokemonDetails(name)', () => {
        it('should return a parsed ComparedPromise for an existing pokemon', () => {
            return expect(mockedScalAPIService.pokemonDetails(new Name('foo')))
                .resolves
                .toEqual(
                    new ComparedPokemon(
                        new Name('foo'),
                        new ImageURL('an url'),
                        [new Type('fire')],
                        [new Stat('def', 50)],
                        new ComparedStat('def', { fire: 3 })
                    )
                );
        });

        it('should reject for a not existing pokemon', () => {
            return expect(mockedScalAPIService.pokemonDetails(new Name('bar')))
                .rejects
                .toBe('Route not found /pokemon/bar');
        });

        it('should reject for an invalid pokemon', () => {
            return expect(
                mockedScalAPIService.pokemonDetails(new Name('kaboum'))
            ).rejects.toEqual(new Error('Couldn\'t parse response'));
        });
    });
});
