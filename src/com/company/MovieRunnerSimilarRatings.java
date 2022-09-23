package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieRunnerSimilarRatings {

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
        FourthRatings tr = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");

//        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Total noOf raters: "+RaterDatabase.size());
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

    public void printAverageRatingsByYearAfterAndGenre()
    {
//        ThirdRatings tr = new ThirdRatings("inputs/ratings_short.csv");
        FourthRatings tr = new FourthRatings();
//        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total noOf raters: "+RaterDatabase.size());
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
//        MovieDatabase.initialize("ratedmovies_short.csv");
        FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        System.out.println("Total noOf raters: "+RaterDatabase.size());
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

    public void printAverageRatingsByDirectors()
    {
//        ThirdRatings tr = new ThirdRatings("inputs/ratings_short.csv");
        FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
//        MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Total noOf raters: "+RaterDatabase.size());
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


    public void printSimilarRatings()
    {
        FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
//        MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> res = tr.getSimilarRatings("65",20,5);
        System.out.println("Found movies: "+res.size());
        try {
            for (Rating r : res) {
                System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
            }
        }
        catch (Exception e)
        {
            return;
        }

    }

    public void printSimilarRatingsByGenre()
    {
        FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
//        MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> res = tr.getSimilarRatingsByFilter("65",20,5,new GenreFilter("Action"));
        System.out.println("Found movies: "+res.size());
        for(Rating r:res)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem())+","+r.getValue());
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printSimilarRatingsByDirector() {
        FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
//        MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> directors = new ArrayList<>(List.of("Clint Eastwood","Sydney Pollack","David Cronenberg","Oliver Stone"));
        ArrayList<Rating> res = tr.getSimilarRatingsByFilter("1034",10,3,new DirectorsFilter(directors));
        System.out.println("Found movies: "+res.size());
        Collections.sort(res,Collections.reverseOrder());


        for(Rating r:res)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem())+","+r.getValue()+","+MovieDatabase.getDirector(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes()
    {
        FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
//        MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters af = new AllFilters();
        af.addFilter(new GenreFilter("Adventure"));
        af.addFilter(new MinutesFilter(100,200));
        ArrayList<Rating> res = tr.getSimilarRatingsByFilter("65",10,5,af);
        System.out.println("Found movies: "+res.size());
        Collections.sort(res,Collections.reverseOrder());
        for(Rating r:res)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem())+","+r.getValue());
            System.out.println(MovieDatabase.getGenres(r.getItem())+","+MovieDatabase.getMinutes(r.getItem()));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes()
    {
        FourthRatings tr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
//        MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(2000));
        af.addFilter(new MinutesFilter(80,100));
        ArrayList<Rating> res = tr.getSimilarRatingsByFilter("65",10,5,af);
        System.out.println("Found movies: "+res.size());
        Collections.sort(res,Collections.reverseOrder());

        for(Rating r:res)
        {
            System.out.println(MovieDatabase.getTitle(r.getItem())+","+r.getValue()+"-"+MovieDatabase.getYear(r.getItem()));
            System.out.println(MovieDatabase.getGenres(r.getItem())+","+MovieDatabase.getMinutes(r.getItem()));
        }
    }

}
