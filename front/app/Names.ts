import Name from './Name';


export default class Names {
    private names: Name[];

    constructor (...names: Name[]) {
        this.names = names;
    }

    static fromAny(thing: any): Names | null {
        if (  Array.isArray(thing)
           && thing.every((elt: any) => typeof elt === 'string')) {
            const names: Name[] =
                thing.map((elt: string) => new Name(elt));
            return new Names(...names);
        }
        return null;
    }

    isEmpty(): boolean {
        return this.names.length === 0;
    }

    map<T>(f: (name: Name, i: number) => T): T[] {
        return this.names.map(f);
    }
}
