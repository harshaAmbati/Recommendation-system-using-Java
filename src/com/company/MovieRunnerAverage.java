package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MovieRunnerAverage{

    public void printAverageRatings()
    {
        String movieFile = "inputs/ratedmovies_short.csv";
//        String movieFile = "inputs/ratedmoviesfull.csv";
        String raterFile = "inputs/ratings_short.csv";
//        String raterFile = "inputs/ratings.csv";
        SecondRatings obj = new SecondRatings(movieFile,raterFile);
        System.out.println(obj.getMovieSize());
        System.out.println(obj.getRaterSize());

        ArrayList<Rating> a1 = obj.getAverageRatings(3);
        Collections.sort(a1);
        for(Rating r:a1)
        {
            System.out.println(r.getValue()+" "+obj.getTitle(r.getItem()));
        }
    }

    public void getAverageRatingOneMovie()
    {
//        String movieFile = "inputs/ratedmovies_short.csv";
        String movieFile = "inputs/ratedmoviesfull.csv";
//        String movieFile = "inputs/Temp.csv";
//        String raterFile = "inputs/ratings_short.csv";
//        String raterFile = "inputs/TempRater.csv";
        String raterFile = "inputs/ratings.csv";
        SecondRatings sr = new SecondRatings(movieFile,raterFile);
        System.out.println(sr.getAverageRatingByTitle("Inside Llewyn Davis"));
        ArrayList<Rating> arr = sr.getAverageRatings(12);
        Collections.sort(arr);
        for(Rating r:arr)
        {
            System.out.println(sr.getTitle(r.getItem()));
            break;
        }
    }





}
