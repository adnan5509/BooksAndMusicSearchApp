import { React } from 'react';
import "./BookTile.scss"

import bkImg from './No_Image_Available.jpeg';

export const BookTile = ({ bookDetails }) => {
    const bookImage = bookDetails.imageLink === "Image Not Available" ? bkImg : bookDetails.imageLink;
    return (
        <div className='BookTile'>
            <div className='book-image'>
                <img className='bk-img'
                    src={bookImage}
                    alt="new"
                />
            </div>
            <div className='book-info'>
                <h4>Title: {bookDetails.title}</h4>
                <h4>Print Type: {bookDetails.information}</h4>
                <h4>Author: {bookDetails.author}</h4>
                <h4>Publisher: {bookDetails.publisher}</h4>
                <h4>Publish Date: {bookDetails.publishDate}</h4>
                <h4>Page Count: {bookDetails.pageCount}</h4>
                <h4>Categories: {bookDetails.categories}</h4>
                <h4>Language: {bookDetails.language}</h4>
                <h4>Description: {bookDetails.description}</h4>
            </div>
        </div>
    );
}