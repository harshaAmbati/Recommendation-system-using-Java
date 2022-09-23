package com.company;

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("inputs/ratedmoviesfull.csv", "inputs/ratings.csv");
    }

    public SecondRatings(String moviefile,String ratingsfile)
    {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }

    public int getMovieSize()
    {
        return myMovies.size();
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

    public String getTitle(String Id)
    {
        for(Movie m:myMovies)
        {
            if(m.getID().equals(Id))
            {
                return m.getTitle();
            }
        }
        return "NOT_FOUND";
    }

    public String getID(String title)
    {
        for(Movie m:myMovies)
        {
            if(m.getTitle().equals(title))
                return m.getID();
        }
        return "NO SUCH TITLE.";
    }

    public double getAverageRatingByTitle(String title)
    {
        HashMap<String,Integer> map = new HashMap<>();
        for(Rater r:myRaters)
        {
            ArrayList<String> arr = r.getItemsRated();
            for(String s:arr)
            {
                if(!map.containsKey(s))
                    map.put(s,1);
                else
                    map.put(s,map.get(s)+1);
            }
        }
        for(String s:map.keySet())
        {
            if(s.equals(getID(title)))
            {
                return getAverageByID(s,map.get(s));
            }
        }
        return 0.0;
    }






}