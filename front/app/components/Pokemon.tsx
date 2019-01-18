import * as React from 'react';
import Name from 'app/Name';


interface IProps {
    name: Name;
}

export default class Pokemon extends React.Component<IProps> {
    render() {
        return (
            <div className='Pokemon'>
                {this.props.name.upper().toString()}
            </div>
        );
    }
}
