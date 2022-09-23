package com.company;

public class GenreFilter implements Filter{

    private String genre;
    public GenreFilter(String genre)
    {
        this.genre = genre;
    }

    @Override
    public boolean satisfies(String id) {
        if(MovieDatabase.getGenres(id).contains(genre))
        {
//            System.out.println(MovieDatabase.getGenres(id));
            return true;
        }
        return false;
    }
}
