package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieRunnerWithFilters {

    public void printAverageRatings()
    {
//        String movieFile = "inputs/ratedmovies_short.csv";
////        String movieFile = "inputs/ratedmoviesfull.csv";
//        String raterFile = "inputs/ratings_short.csv";
////        String raterFile = "inputs/ratings.csv";
//        SecondRatings obj = new SecondRatings(movieFile,raterFile);
//        System.out.println(obj.getMovieSize());
//        System.out.println(obj.getRaterSize());
//
//        ArrayList<Rating> a1 = obj.getAverageRatings(3);
//        Collections.sort(a1);
//        for(Rating r:a1)
//        {
//            System.out.println(r.getValue()+" "+obj.getTitle(r.getItem()));
//        }

//        ThirdRatings tr = new ThirdRatings("inputs/ratings_short.csv");
        ThirdRatings tr = new ThirdRatings("inputs/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

//        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Total noOf raters: "+tr.getRaterSize());
        System.out.println("Total movies: "+MovieDatabase.size());
        //movies with ratings are returned, then sort them, and print out the rating and title of each movie.
        ArrayList<Rating> arr = tr.getAverageRatings(4);
        System.out.println("found "+arr.size()+" movies");
        Collections.sort(arr);
        for(Rating r:arr)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue());
            System.out.println(MovieDatabase.getDirector(r.getItem()));
        }
    }

    public void printAverageRatingsByYear()
    {
//        ThirdRatings tr = new ThirdRatings("inputs/ratings_short.csv");
//        MovieDatabase.initialize("ratedmovies_short.csv");
        ThirdRatings tr = new ThirdRatings("inputs/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total noOf raters: "+tr.getRaterSize());
        System.out.println("Total movies: "+MovieDatabase.size());
        ArrayList<Rating> arr = tr.getAverageRatingsByFilter(20,new YearAfterFilter(2000));
        System.out.println("found "+arr.size()+" movies");
        Collections.sort(arr);
        for(Rating r:arr)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue()+" "+MovieDatabase.getYear(r.getItem()));
        }

    }

    public void printAverageRatingsByGenre()
    {
//        ThirdRatings tr = new ThirdRatings("inputs/ratings_short.csv");
//        MovieDatabase.initialize("ratedmovies_short.csv");
        ThirdRatings tr = new ThirdRatings("inputs/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total noOf raters: "+tr.getRaterSize());
        System.out.println("Total movies: "+MovieDatabase.size());
        ArrayList<Rating> arr = tr.getAverageRatingsByFilter(20,new GenreFilter("Comedy"));
        System.out.println("found "+arr.size()+" movies");
        Collections.sort(arr);
        for(Rating r:arr)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue()+" "+MovieDatabase.getGenres(r.getItem()));
        }

    }


    public void printAverageRatingsByMinutes()
    {
//        ThirdRatings tr = new ThirdRatings("inputs/ratings_short.csv");
//        MovieDatabase.initialize("ratedmovies_short.csv");
        ThirdRatings tr = new ThirdRatings("inputs/ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total noOf raters: "+tr.getRaterSize());
        System.out.println("Total movies: "+MovieDatabase.size());
        ArrayList<Rating> arr = tr.getAverageRatingsByFilter(5,new MinutesFilter(105,135));
        System.out.println("found "+arr.size()+" movies");
        Collections.sort(arr);
        for(Rating r:arr)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue()+" "+MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printAverageRatingsByDirectors()
    {
//        ThirdRatings tr = new ThirdRatings("inputs/ratings_short.csv");
        ThirdRatings tr = new ThirdRatings("inputs/ratings.csv");
//        MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total noOf raters: "+tr.getRaterSize());
        System.out.println("Total movies: "+MovieDatabase.size());
        ArrayList<String> arr2 = new ArrayList<String>(List.of("Clint Eastwood","Joel Coen","Martin Scorsese","Roman Polanski","Nora Ephron","Ridley Scott","Sydney Pollack"));
        ArrayList<Rating> arr = tr.getAverageRatingsByFilter(4,new DirectorsFilter(arr2));
        System.out.println("found "+arr.size()+" movies");
        Collections.sort(arr);
        for(Rating r:arr)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue()+" "+MovieDatabase.getGenres(r.getItem())+","+MovieDatabase.getDirector(r.getItem()));
        }

    }

    public void printAverageRatingsByYearAfterAndGenre()
    {
//        ThirdRatings tr = new ThirdRatings("inputs/ratings_short.csv");
        ThirdRatings tr = new ThirdRatings("inputs/ratings.csv");
//        MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total noOf raters: "+tr.getRaterSize());
        System.out.println("Total movies: "+MovieDatabase.size());
        AllFilters af = new AllFilters();
        Filter f1 = new YearAfterFilter(1990);
        Filter f2 = new GenreFilter("Drama");
        af.addFilter(f1);
        af.addFilter(f2);
//        ArrayList<Rating> arr = tr.getAverageRatingsByFilter(1,new DirectorsFilter(new ArrayList<>(List.of("Charles Chaplin,Michael Mann,Spike Jonze"))));
        ArrayList<Rating> arr = tr.getAverageRatingsByFilter(8,af);
        System.out.println("found "+arr.size()+" movies");
        Collections.sort(arr);
        for(Rating r:arr)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue()+","+MovieDatabase.getDirector(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes()
    {
//        ThirdRatings tr = new ThirdRatings("inputs/ratings_short.csv");
        ThirdRatings tr = new ThirdRatings("inputs/ratings.csv");
//        MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total noOf raters: "+tr.getRaterSize());
        System.out.println("Total movies: "+MovieDatabase.size());
        AllFilters af = new AllFilters();
        ArrayList<String> in = new ArrayList<String>(List.of("Clint Eastwood","Joel Coen","Tim Burton","Ron Howard","Nora Ephron","Sydney Pollack"));
        Filter f1 = new DirectorsFilter(in);
        Filter f2 = new MinutesFilter(90,180);
        af.addFilter(f1);
        af.addFilter(f2);
//        ArrayList<Rating> arr = tr.getAverageRatingsByFilter(1,new DirectorsFilter(new ArrayList<>(List.of("Charles Chaplin,Michael Mann,Spike Jonze"))));
        ArrayList<Rating> arr = tr.getAverageRatingsByFilter(3,af);
        System.out.println("found "+arr.size()+" movies");
        Collections.sort(arr);
        for(Rating r:arr) {
            System.out.println(r.getValue()+" Time: " +MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem())+ "," +MovieDatabase.getDirector(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }

}
