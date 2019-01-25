import { IWSClient } from '../services/ScalAPIService';

const axiosMock: IWSClient = {
    get: (url: string) => new Promise((resolve, reject) => {
        if (url === '/pokemons') {
            resolve(
                ['abra','alakazam','arbok','arcanine','beedrill','bellsprout','blastoise','bulbasaur','butterfree','caterpie','charizard','charmander','charmeleon','clefable','clefairy','cloyster','dewgong','diglett','dodrio','doduo','drowzee','dugtrio','ekans','farfetchd','fearow','gastly','gengar','geodude','gloom','golbat','golduck','golem','graveler','grimer','growlithe','haunter','hypno','ivysaur','jigglypuff','kadabra','kakuna','kingler','krabby','machamp','machoke','machop','magnemite','magneton','mankey','meowth','metapod','muk','nidoking','nidoqueen','nidoran-f','nidoran-m','nidorina','nidorino','ninetales','oddish','onix','paras','parasect','persian','pidgeot','pidgeotto','pidgey','pikachu','poliwag','poliwhirl','poliwrath','ponyta','primeape','psyduck','raichu','rapidash','raticate','rattata','sandshrew','sandslash','seel','shellder','slowbro','slowpoke','spearow','squirtle','tentacool','tentacruel','venomoth','venonat','venusaur','victreebel','vileplume','voltorb','vulpix','wartortle','weedle','weepinbell','wigglytuff','zubat']
            );
        } else if (url === '/pokemon/pikachu') {
            resolve(
                {
                    name: 'pikachu',
                    image: 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png',
                    types: ['electric'],
                    base_stats:[
                        {
                            name: 'speed',
                            value: 90
                        },
                        {
                            name: 'special-defense',
                            value: 50
                        },
                        {
                            name: 'special-attack',
                            value: 50
                        },
                        {
                            name: 'defense',
                            value: 40
                        },
                        {
                            name: 'attack',
                            value: 55
                        },
                        {
                            name: 'hp',
                            value: 35
                        }
                    ],
                    compared_stats: [
                        {
                            name:'speed',
                            comparisons: {
                                electric: 8.205479452054789
                            }
                        },
                        {
                            name:'special-defense',
                            comparisons: {
                                electric: -18.808219178082197
                            }
                        },
                        {
                            name:'special-attack',
                            comparisons: {
                                electric: -32.52054794520548
                            }
                        },
                        {
                            name:'defense',
                            comparisons: {
                                electric: -25.28767123287672
                            }
                        },
                        {
                            name:'attack',
                            comparisons: {
                                electric: -15
                            }
                        },
                        {
                            name:'hp',
                            comparisons: {
                                electric: -24.164383561643838
                            }
                        }
                    ]
                }
            );
        } else reject(`url not found: "${url}"`);
    })
};

export default axiosMock;
