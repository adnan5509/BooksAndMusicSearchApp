import { React, useState, useEffect } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import ReactPaginate from 'react-paginate';
import { SongTile } from '../components/SongTile';

import "./ITunesSongsPage.scss"


export const ITunesSongsPage = () => {

    const [songsData, setSongsData] = useState([]);
    const { search } = useParams();
    const location = useLocation();
    const songsSearchValue = search;
    const songsResponseData = location.state;

    const PER_PAGE = 10;
    const [currentPage, setCurrentPage] = useState(0);

    useEffect(() => {
        setSongsData(songsResponseData);
    }, []);

    function handlePageClick({ selected: selectedPage }) {
        setCurrentPage(selectedPage);
    }

    const offset = currentPage * PER_PAGE;
    const currentPageData = songsData.slice(offset, offset + PER_PAGE);

    const pageCount = Math.ceil(songsData.length / PER_PAGE);
    return (
        <div className='ITunesSongsPage'>
            <p className='songs-header-section'>ITunes Song Results for {songsSearchValue}</p>
            <div className='song-tiles-section'>
                {
                    currentPageData.map(song => <SongTile key={song.title + "_" + song.artist + "_" + song.genre} songDetails={song} />)
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
