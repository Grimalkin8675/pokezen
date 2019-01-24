import ComparedPokemon from '../ComparedPokemon';
import Name from '../Name';
import ImageURL from '../ImageURL';
import Type from '../Type';
import Stat from '../Stat';
import ComparedStat from '../ComparedStat';


describe(ComparedPokemon, () => {
    describe('constructor', () => {
        it('should construct', () => {
            const pokemon = new ComparedPokemon(
                new Name('foo'),
                new ImageURL('some url'),
                [new Type('fire')],
                [new Stat('def', 50)],
                new ComparedStat(
                    'def',
                    {
                        fire: 3
                    }
                )
            );
            expect(pokemon).toBeInstanceOf(ComparedPokemon);
            expect(pokemon.types).toEqual([new Type('fire')]);
            expect(
                new ComparedPokemon(new Name(''), null, [], [])
            ).toBeInstanceOf(ComparedPokemon);
        });
    });

    describe('fromAny(thing)', () => {
        it('should parse a valid json', () => {
            expect(ComparedPokemon.fromAny(
                {
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
                }
            )).toEqual(
                new ComparedPokemon(
                    new Name('bar'),
                    new ImageURL('some url'),
                    [new Type('fire'), new Type('air')],
                    [new Stat('def', 50), new Stat('att', 60)],
                    new ComparedStat(
                        'def',
                        {
                            fire: 3,
                            air: 4
                        }
                    ),
                    new ComparedStat(
                        'att',
                        {
                            fire: -1,
                            air: -2
                        }
                    )
                )
            );
        });

        it('should parse another valid json', () => {
            expect(ComparedPokemon.fromAny(
                {
                    name: 'bar',
                    image: null,
                    types: [],
                    base_stats: [],
                    compared_stats: []
                }
            )).toEqual(
                new ComparedPokemon(new Name('bar'), null, [], [])
            );
        });

        it('shouldn\'t parse an invalid json', () => {
            expect(ComparedPokemon.fromAny(
                {
                    name: 'bar',
                    image: null,
                    types: {},
                    base_stats: [],
                    compared_stats: []
                }
            )).toBeNull();
        });
    });
});
