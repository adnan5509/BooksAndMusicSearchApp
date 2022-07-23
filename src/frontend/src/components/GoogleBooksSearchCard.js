import { React, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import TextField from "@mui/material/TextField";
import { Button } from 'react-bootstrap'

import "./GoogleBooksSearchCard.scss"

export const GoogleBooksSearchCard = () => {
    const [bookSearchValue, setBookSearchValue] = useState('');
    const navigate = useNavigate();

    const searchBook = async () => {
        const response = await fetch(`${process.env.REACT_APP_ROOT_API_URL}/api/BooksAndAlbums/getBooks/${bookSearchValue}`);
        const responseData = await response.json();
        navigate(`/books/${bookSearchValue}`, { state: responseData });
    };

    return (
        <div className="google-books-search-card">
            <h1>Google Books Search</h1>
            <div className="google-books-search-section">
                <TextField
                    id="google-books-search-bar"
                    variant="filled"
                    fullWidth
                    label="Enter a Book Name"
                    color="warning"
                    onChange={(event) => { setBookSearchValue(event.target.value) }}
                />
                <Button className='search-btn' onClick={searchBook}>Search</Button>
            </div>
        </div>
    );
}

