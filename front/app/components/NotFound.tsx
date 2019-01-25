import * as React from 'react';
import { Link } from 'react-router-dom';

import * as styles from './styles/NotFound.css';


export default class NotFound extends React.Component {
    render() {
        return (
            <div className={styles.main}>
                <div className={styles.message}>
                    404: Who's that pokemon?
                </div>
                <Link to={'/'} className={styles.goBack}>go back</Link>
            </div>
        );
    }
}
