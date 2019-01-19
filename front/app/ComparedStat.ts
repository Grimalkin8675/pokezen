export default class ComparedStat {
    statName: string;
    comparisons: { [typeName: string]: number };

    constructor (
        statName: string,
        comparisons: { [typeName: string]: number }
    ) {
        this.statName = statName;
        this.comparisons = comparisons;
    }
}
