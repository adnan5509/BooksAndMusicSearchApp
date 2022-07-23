import { React } from 'react';
import "./SongTile.scss"

import sngImg from './No_Image_Available.jpeg';

export const SongTile = ({ songDetails }) => {
    const songImage = songDetails.imageLink === "Image Not Available" ? sngImg : songDetails.imageLink;
    
    return (
        <div className='SongTile'>
            <div className='song-image'>
                <img className='sng-img'
                    src={songImage}
                    alt="new"
                />
            </div>
            <div className='song-info'>
                <h4>Title: {songDetails.title}</h4>
                <h4>Type: {songDetails.information}</h4>
                <h4>Artist: {songDetails.artist}</h4>
                <h4>Collection Name: {songDetails.collectionName}</h4>
                <h4>Release Date: {songDetails.releaseDate}</h4>
                <h4>Country: {songDetails.country}</h4>
                <h4>Genre: {songDetails.genre}</h4>
                <h4>Track Price: {songDetails.trackPrice}</h4>
                <h4>Collection Price: {songDetails.collectionPrice}</h4>
                <h4>Track Duration: {songDetails.trackDuration}</h4>
                <h4>Currency: {songDetails.currency}</h4>
            </div>
        </div>
    );
}