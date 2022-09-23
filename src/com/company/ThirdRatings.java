package com.company;

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("inputs/ratings.csv");
    }

    public ThirdRatings(String ratingsfile)
    {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }


    public int getRaterSize()
    {
        HashSet<String> rat = new HashSet<>();
        for(Rater r:myRaters)
        {
            rat.add(r.getID());
        }
        return rat.size();
    }

    public double getAverageByID(String movieId,int minimalRaters)
    {
        double avg = 0;
        for(Rater r:myRaters)
        {
            if(r.getRating(movieId)!=-1)
                avg+=r.getRating(movieId);
        }
        return (avg/minimalRaters);
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        HashMap<String,Integer> movieToNoOfRaters = new HashMap<>();
        ArrayList<Rating> result = new ArrayList<>();
        ArrayList<Rater> temp = myRaters;
        HashMap<String,Integer> freqOfRaterIds = new HashMap<>(); //Id's and their total ratings
        for(Rater r:temp)
        {
            if(!freqOfRaterIds.containsKey(r.getID()))
            {
                freqOfRaterIds.put(r.getID(),1);
            }
            else
            {
                freqOfRaterIds.put(r.getID(),freqOfRaterIds.get(r.getID())+1);
            }
        }
        for(String s:freqOfRaterIds.keySet())  //for each id
        {
//            System.out.println("id: "+s+" noOfRatings: "+freqOfRaterIds.get(s)); //printing id's and their total no of ratings given
            for(Rater r:temp)  //for each id
            {
                if(r.getID().equals(s)) //Check for each id
                {
                    ArrayList<String> arr = r.getItemsRated(); // Movies that id rated
                    for(String str:arr) //for each movie
                    {
                        if(!movieToNoOfRaters.containsKey(str))
                        {
                            movieToNoOfRaters.put(str,1);
                        }
                        else
                        {
                            movieToNoOfRaters.put(str,movieToNoOfRaters.get(str)+1);
                        }
                    }
                }
            }
        }
        for(String s:movieToNoOfRaters.keySet())
        {
            if(movieToNoOfRaters.get(s)>=minimalRaters)
            {
                Rating obj = new Rating(s,getAverageByID(s,movieToNoOfRaters.get(s)));
                result.add(obj);
            }
        }
        return result;
    }


//    public double getAverageRatingByTitle(String title)
//    {
//        HashMap<String,Integer> map = new HashMap<>();
//        for(Rater r:myRaters)
//        {
//            ArrayList<String> arr = r.getItemsRated();
//            for(String s:arr)
//            {
//                if(!map.containsKey(s))
//                    map.put(s,1);
//                else
//                    map.put(s,map.get(s)+1);
//            }
//        }
//        for(String s:map.keySet())
//        {
//            if(s.equals(getID(title)))
//            {
//                return getAverageByID(s,map.get(s));
//            }
//        }
//        return 0.0;
//    }


//    public ArrayList<Rating> getAverageRatings(int minimalRaters)
//    {
//        ArrayList<Rating> result = new ArrayList<>();
//        HashMap<String,Integer> mappingMovieIdWithNoOfRaters = new HashMap<>();
//        HashSet<String> raterIds = new HashSet<>();
//        ArrayList<String> movieIds = MovieDatabase.filterBy(new TrueFilter()); //get all movie ids if exists
//        for(Rater r:myRaters)
//        {
//            raterIds.add(r.getID());
//        }
//        for(Rater r:myRaters)
//        {
//            for(String s:raterIds)
//            {
//                if(r.getID().equals(s))
//                {
//                    ArrayList<String> arr = r.getItemsRated();
//                    for(String s1:arr)
//                    {
//                        if(!mappingMovieIdWithNoOfRaters.containsKey(s1))
//                            mappingMovieIdWithNoOfRaters.put(s1,1);
//                        else
//                            mappingMovieIdWithNoOfRaters.put(s1,mappingMovieIdWithNoOfRaters.get(s1)+1);
//                    }
//                }
//            }
//        }
//        for(String item:mappingMovieIdWithNoOfRaters.keySet())
//        {
//            if(mappingMovieIdWithNoOfRaters.get(item)>=minimalRaters)
//            {
//                Rating obj = new Rating(item,getAverageByID(item,mappingMovieIdWithNoOfRaters.get(item)));
//                result.add(obj);
//            }
//        }
//        return result;
//    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria)
    {
        ArrayList<Rating> result = new ArrayList<>();
        HashMap<String,Integer> mappingMovieIdWithNoOfRaters = new HashMap<>();
        ArrayList<String> res = MovieDatabase.filterBy(filterCriteria);
        for(Rater r:myRaters)
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




}