import ComparedPokemon from '../../app/models/ComparedPokemon';
import Name from '../../app/models/Name';
import ImageURL from '../../app/models/ImageURL';
import Type from '../../app/models/Type';
import Stat from '../../app/models/Stat';
import ComparedStat from '../../app/models/ComparedStat';


describe(ComparedPokemon, () => {
    describe('constructor', () => {
        it('should construct', () => {
            const pokemon = new ComparedPokemon(
                new Name('foo'),
                new ImageURL('some url'),
                [new Type('fire')],
                [new Stat('def', 50)],
                new ComparedStat('def', { fire: 3 })
            );
            expect(pokemon).toBeInstanceOf(ComparedPokemon);
            expect(pokemon.types).toEqual([new Type('fire')]);
            expect(new ComparedPokemon(new Name(''), null, [], []))
                .toBeInstanceOf(ComparedPokemon);
        });
    });

    describe('fromAny(thing)', () => {
        it('should parse a valid json', () => {
            expect(ComparedPokemon.fromAny({
                name: 'bar',
                image: 'some url',
                types: ['fire', 'air'],
                base_stats: [
                    {
                        name: 'def',
                        value: 50
                    },
                    {
                        name: 'att',
                        value: 60
                    }
                ],
                compared_stats: [
                    {
                        name: 'def',
                        comparisons: {
                            fire: 3,
                            air: 4
                        }
                    },
                    {
                        name: 'att',
                        comparisons: {
                            fire: -1,
                            air: -2
                        }
                    }
                ]
            })).toEqual(
                new ComparedPokemon(
                    new Name('bar'),
                    new ImageURL('some url'),
                    [new Type('fire'), new Type('air')],
                    [new Stat('def', 50), new Stat('att', 60)],
                    new ComparedStat('def', { fire: 3, air: 4 }),
                    new ComparedStat('att', { fire: -1, air: -2 })
                )
            );
        });

        it('should parse another valid json', () => {
            expect(ComparedPokemon.fromAny({
                name: 'bar',
                image: null,
                types: [],
                base_stats: [],
                compared_stats: []
            })).toEqual(new ComparedPokemon(new Name('bar'), null, [], []));
        });

        it('shouldn\'t parse an invalid json', () => {
            expect(ComparedPokemon.fromAny({
                name: 'bar',
                image: null,
                types: {},
                base_stats: [],
                compared_stats: []
            })).toBeNull();
        });
    });

    describe('comparedStatToType(stat, type)', () => {
        const pokemon = new ComparedPokemon(
            new Name('foo'),
            new ImageURL('some url'),
            [new Type('fire')],
            [new Stat('def', 50), new Stat('att', 23)],
            new ComparedStat('def', { fire: 3 }),
            new ComparedStat('att', { fire: -12 })
        );

        it('should return a number if comparison exists for stat', () => {
            expect(
                pokemon.comparedStatToType(
                    new Stat('def', -1), new Type('fire'))
            ).toBe(3);
        });

        it('should return a number if comparison exists for stat 2', () => {
            expect(
                pokemon.comparedStatToType(
                    new Stat('att', -1), new Type('fire'))
            ).toBe(-12);
        });

        it('should return undefined if stat doesn\'t exist', () => {
            expect(
                pokemon.comparedStatToType(
                    new Stat('no such stat', -1), new Type('fire'))
            ).toBeUndefined();
        });

        it('should return undefined if type doesn\'t exist', () => {
            expect(
                pokemon.comparedStatToType(
                    new Stat('att', -1), new Type('no such type'))
            ).toBeUndefined();
        });
    });
});
