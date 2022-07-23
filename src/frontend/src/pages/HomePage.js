import { React } from 'react'
import { GoogleBooksSearchCard } from '../components/GoogleBooksSearchCard';
import { ITunesSearchCard } from '../components/ITunesSearchCard';

import './HomePage.scss';

export const HomePage = () => {
    return (
        <div className="HomePage">
            <div className='header-section'>
                <h1 className='app-name-section'>
                    Search Google Books and ITunes Songs
                </h1>
            </div>
            <div className='google-books-search-section'>
                <GoogleBooksSearchCard />
            </div>
            <div className='itunes-search-section'>
                <ITunesSearchCard />
            </div>
        </div>
    );
}
