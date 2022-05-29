package com.KrampHub.BooksAndAlbums.service;


import com.KrampHub.BooksAndAlbums.model.Album;
import com.KrampHub.BooksAndAlbums.model.Book;
import com.KrampHub.BooksAndAlbums.model.BookAlbum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookAlbumService {
    Logger logger = LoggerFactory.getLogger(BookAlbumService.class);

    @Autowired
    CallingBooksService callingBooksService;

    @Autowired
    CallingAlbumsService callingAlbumsService;

    public BookAlbum getBooksAlbums(String searchText) {
        logger.info("Inside Books and Albums Service getBooksAlbums Method");

        List<Book> bookList = callingBooksService.getBooks(searchText);
        List<Album> albumList = callingAlbumsService.getAlbums(searchText);

        BookAlbum bookAlbum = new BookAlbum();
        bookAlbum.setAlbumList(albumList);
        bookAlbum.setBookList(bookList);
        return bookAlbum;
    }

}
