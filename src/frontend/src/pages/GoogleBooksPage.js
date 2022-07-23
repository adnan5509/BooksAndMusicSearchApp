import { React, useState, useEffect } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import ReactPaginate from 'react-paginate';
import { BookTile } from '../components/BookTile';

import "./GoogleBooksPage.scss"

export const GoogleBooksPage = () => {

    const [booksData, setBooksData] = useState([]);
    const { search } = useParams();
    const location = useLocation();
    const booksSearchValue = search;
    const booksResponseData = location.state;

    const PER_PAGE = 10;
    const [currentPage, setCurrentPage] = useState(0);

    useEffect(() => {
        setBooksData(booksResponseData);
    }, []);

    function handlePageClick({ selected: selectedPage }) {
        setCurrentPage(selectedPage);
    }

    const offset = currentPage * PER_PAGE;
    const currentPageData = booksData.slice(offset, offset + PER_PAGE);

    const pageCount = Math.ceil(booksData.length / PER_PAGE);

    return (
        <div className='GoogleBooksPage'>
            <p className='books-header-section'>Google Book Results for {booksSearchValue}</p>
            <div className='book-tiles-section'>
                {
                    currentPageData.map(book => <BookTile key={book.title + "_" + book.author + "_" + book.pageCount} bookDetails={book} />)
                }
                <ReactPaginate
                    previousLabel={"⇦ Previous"}
                    nextLabel={"Next ⇨"}
                    pageCount={pageCount}
                    onPageChange={handlePageClick}
                    containerClassName={"pagination"}
                    previousLinkClassName={"pagination__link"}
                    nextLinkClassName={"pagination__link"}
                    disabledClassName={"pagination__link--disabled"}
                    activeClassName={"pagination__link--active"}
                />
            </div>
        </div>
    );
}
