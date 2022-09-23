package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class FourthRatings {
    // is this rater = efficientRater ?
    private double dotProduct(Rater me, Rater r) {
        double dp = 0;
        ArrayList<String> memovieid = me.getItemsRated();
        for (String id : memovieid) {
            if (r.getItemsRated().contains(id)) {
                dp += (me.getRating(id) - 5) * (r.getRating(id) - 5);
            }
        }
        return dp;
    }

//  String id is the target user id;
    private ArrayList<Rating> getSimilarities(String raterId) {
        ArrayList<Rating> simiList = new ArrayList<>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        for (Rater r : raters) {
            if (!r.getID().equals(raterId)) {
                double dotProduct = dotProduct(RaterDatabase.getRater(raterId), r);
                if (dotProduct > 0) {
                    simiList.add(new Rating(r.getID(), dotProduct));
                    // System.out.println("rater id = " + r.getID() + " dotProduct " + dotProduct);
                }
            }
        }
        Collections.sort(simiList);
        Collections.reverse(simiList);

        return simiList;
    }

    public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        //rating for all movie
        Filter trueFilter = new TrueFilter();
        for (String j : MovieDatabase.filterBy(trueFilter)) {
            // rating for one movie
            double ave = 0;
            ArrayList<Rating> simiList = getSimilarities(raterID);
            // List<Rating> topsimiList = simiList.subList(0, numSimilarRaters);
            int count = 0;
            double total = 0;
            int simiweighttotal = 0;
            // System.out.println("total");
            for (int i = 0; i < numSimilarRaters; i++) {
                //   System.out.println("i=" + i);

                double rating = RaterDatabase.getRater(simiList.get(i).getItem()).getRating(j);
                //System.out.println(count + " : " + "id = " + simiList.get(i).getItem() + " rating " + rating + " ave " + total);
                if (rating != -1) {
                    count++;
                    total += rating * simiList.get(i).getValue();
                    simiweighttotal += simiList.get(i).getValue();
                    //System.out.println("Movie id = " + j + " count " + count + " : " + "id = " + simiList.get(i).getItem() + " rating " + rating + " ave " + total);

                }
            }
            if (count >= minimalRaters)
                //ave = total / simiweighttotal;
                ave = total / count;
            //System.out.println("Movie id = " + j + " count " + count + " : " + " rating " + ave + " total/count " + total / count);
            //(9*31+10*20)/50=279+200=479/50=9.58
            //(9*31+10*20)/2 = 479/2 = 239.5
            // rating for one movie end
            if (ave > 0)
                ratingList.add(new Rating(j, ave));
            // rating for all movie end
        }
        Collections.sort(ratingList);
        Collections.reverse(ratingList);

        return ratingList;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String raterID, int numSimilarRaters, int minimalRaters, Filter f) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        //rating for all movie
        Filter trueFilter = new TrueFilter();
        //print similarList
        ArrayList<Rating> simiListTest = getSimilarities(raterID);
        //print out  top 20 similar rater id and their dot product.
        //        for (int i = 0; i < numSimilarRaters; i++) {
        //            double simirating = simiListTest.get(i).getValue();
        //
        //            System.out.println("id = " + simiListTest.get(i).getItem() + " rating " + simirating);
        //        }

        for (String j : MovieDatabase.filterBy(trueFilter)) {
            if (f.satisfies(j)) {
                // rating for one movie
                double ave = 0;
                ArrayList<Rating> simiList = getSimilarities(raterID);
                // List<Rating> topsimiList = simiList.subList(0, numSimilarRaters);
                int count = 0;
                double total = 0;
                double simiweighttotal = 0;
                for (int i = 0; i < numSimilarRaters; i++) {
                    double rating = RaterDatabase.getRater(simiList.get(i).getItem()).getRating(j);
                    if (rating != -1) {
                        count++;
                        total += rating * simiList.get(i).getValue();
                        simiweighttotal += simiList.get(i).getValue();

                        //System.out.println(count + " : " + "id = " + simiList.get(i).getItem() + " rating " + rating + " ave " + total);

                    }
                }
                if (count >= minimalRaters)
                    //ave = total / simiweighttotal;
                    ave = total / count;

                // rating for one movie end
                if (ave > 0)
                    ratingList.add(new Rating(j, ave));
            }
            // rating for all movie end
        }
        Collections.sort(ratingList);
        Collections.reverse(ratingList);

        return ratingList;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria)
    {
        ArrayList<Rating> result = new ArrayList<>();
        HashMap<String,Integer> mappingMovieIdWithNoOfRaters = new HashMap<>();
        ArrayList<String> res = MovieDatabase.filterBy(filterCriteria);
        for(Rater r:RaterDatabase.getRaters())
        {
            ArrayList<String> arr = r.getItemsRated();
            for(String s1:arr)
            {
                if(res.contains(s1)) //only if the rater has rated a movie that is inside filtered data
                {
                    if(!mappingMovieIdWithNoOfRaters.containsKey(s1)){
                        mappingMovieIdWithNoOfRaters.put(s1,1);
                    }
                    else{
                        mappingMovieIdWithNoOfRaters.put(s1,mappingMovieIdWithNoOfRaters.get(s1)+1);
                    }
                }
            }
        }
        for(String item:mappingMovieIdWithNoOfRaters.keySet())
        {
            if(mappingMovieIdWithNoOfRaters.get(item)>=minimalRaters)
            {
                Rating obj = new Rating(item,getAverageByID(item,mappingMovieIdWithNoOfRaters.get(item)));
                result.add(obj);
            }
        }

        return result;
    }
//Average method(Not that important)
    public double getAverageByID(String movieId,int minimalRaters)
    {
        double avg = 0;
        for(Rater r:RaterDatabase.getRaters())
        {
            if(r.getRating(movieId)!=-1)
                avg+=r.getRating(movieId);
        }
        return (avg/minimalRaters);
    }
//Not that important
    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        ArrayList<Rating> result = new ArrayList<>();
        HashMap<String,Integer> mappingMovieIdWithNoOfRaters = new HashMap<>();
        HashSet<String> raterIds = new HashSet<>();
        ArrayList<String> movieIds = MovieDatabase.filterBy(new TrueFilter()); //get all movie ids if exists
        for(Rater r:RaterDatabase.getRaters())
        {
            raterIds.add(r.getID());
        }
        for(Rater r:RaterDatabase.getRaters())
        {
            for(String s:raterIds)
            {
                if(r.getID().equals(s))
                {
                    ArrayList<String> arr = r.getItemsRated();
                    for(String s1:arr)
                    {
                        if(!mappingMovieIdWithNoOfRaters.containsKey(s1))
                            mappingMovieIdWithNoOfRaters.put(s1,1);
                        else
                            mappingMovieIdWithNoOfRaters.put(s1,mappingMovieIdWithNoOfRaters.get(s1)+1);
                    }
                }
            }
        }
        for(String item:mappingMovieIdWithNoOfRaters.keySet())
        {
            if(mappingMovieIdWithNoOfRaters.get(item)>=minimalRaters)
            {
                Rating obj = new Rating(item,getAverageByID(item,mappingMovieIdWithNoOfRaters.get(item)));
                result.add(obj);
            }
        }
        return result;
    }

}