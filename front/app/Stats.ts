import Stat from './Stat';


export default class Stats {
    stats: Stat[];

    constructor (...stats: Stat[]) {
        this.stats = stats;
    }
}
