import { React, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import TextField from "@mui/material/TextField";
import { Button } from 'react-bootstrap'

import "./ITunesSearchCard.scss"

export const ITunesSearchCard = () => {
    const [songSearchValue, setSongSearchValue] = useState('');
    const navigate = useNavigate();

    const searchSong = async () => {
        const response = await fetch(`${process.env.REACT_APP_ROOT_API_URL}/api/BooksAndAlbums/getAlbums/${songSearchValue}`);
        const responseData = await response.json();
        navigate(`/songs/${songSearchValue}`, { state: responseData });
    };

    return (
        <div className="itunes-search-card">
            <h1>ITunes Search</h1>
            <div className="itunes-search-section">
                <TextField
                    id="itunes-search-bar"
                    variant="filled"
                    fullWidth
                    label="Enter a Song Name"
                    color="warning"
                    onChange={(event) => { setSongSearchValue(event.target.value) }}
                />
                <Button className='search-btn' onClick={searchSong}>Search</Button>
            </div>
        </div>
    );
}

