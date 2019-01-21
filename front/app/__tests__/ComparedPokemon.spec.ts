import ComparedPokemon from '../ComparedPokemon';
import Name from '../Name';
import ImageURL from '../ImageURL';
import Types from '../Types';
import Type from '../Type';
import Stats from '../Stats';
import Stat from '../Stat';
import ComparedStat from '../ComparedStat';


describe(ComparedPokemon, () => {
    describe('constructor', () => {
        it('should construct', () => {
            expect(
                new ComparedPokemon(
                    new Name('foo'),
                    new ImageURL('some url'),
                    new Types(new Type('fire')),
                    new Stats(new Stat('def', 50)),
                    new ComparedStat(
                        'def',
                        {
                            fire: 3
                        }
                    )
                )
            ).toBeInstanceOf(ComparedPokemon);
            expect(
                new ComparedPokemon(
                    new Name(''),
                    null,
                    new Types(),
                    new Stats()
                )
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
                    new Types(new Type('fire'), new Type('air')),
                    new Stats(new Stat('def', 50), new Stat('att', 60)),
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
                new ComparedPokemon(
                    new Name('bar'),
                    null,
                    new Types(),
                    new Stats()
                )
            );
        });
    });
});
