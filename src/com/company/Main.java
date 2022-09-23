package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        MovieRunnerSimilarRatings obj = new MovieRunnerSimilarRatings();
//        obj.printAverageRatingsByDirectorsAndMinutes();
//        obj.printAverageRatingsByDirectors();

//        RaterDatabase.initialize("ratings_short.csv");
//        for(Rater r:RaterDatabase.getRaters())
//        {
////            System.out.println(r.getID());
////            System.out.println(r.getItemsRated());
//            System.out.println(r.getID()+" " +r.getItemsRated());
//        }
//        Rater r1 = RaterDatabase.getRater("2");
//        Rater r2 = RaterDatabase.getRater("5");
//        ArrayList<String> moviesRatedByMe = r1.getItemsRated();
//        ArrayList<String> moviesRatedByr = r2.getItemsRated();
//        System.out.println(r1.getItemsRated());
//        moviesRatedByMe.retainAll(moviesRatedByr);
//        System.out.println(moviesRatedByMe);
        MovieRunnerSimilarRatings ms = new MovieRunnerSimilarRatings();
//        ms.printSimilarRatings();
//        ms.printSimilarRatingsByGenre();
//        ms.printAverageRatingsByDirectors();
//ms.printSimilarRatingsByGenreAndMinutes();
ms.printSimilarRatingsByYearAfterAndMinutes();
    }
}
