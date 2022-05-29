FROM openjdk:11
LABEL maintainer="kramphub-books-albums"
ADD target/BooksAndAlbums-0.0.1-SNAPSHOT.jar kramphub-books-albums.jar
ENTRYPOINT ["java","-jar","kramphub-books-albums.jar"]