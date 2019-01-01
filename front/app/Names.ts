import Name from './Name';


export default class Names {
    names: Name[];

    constructor (...names: Name[]) {
        this.names = names;
    }
}
