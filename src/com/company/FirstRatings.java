package com.company;

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

import java.util.ArrayList;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename)
    {
        ArrayList<Movie> loadedMovies = new ArrayList<>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record:parser)
        {
            Movie obj = new Movie(record.get("id"),record.get("title"),record.get("year"),record.get("genre"),record.get("director"),record.get("country"),record.get("poster"),Integer.parseInt(record.get("minutes")));
            loadedMovies.add(obj);
        }
        return loadedMovies;
    }


    public ArrayList<Rater> loadRaters(String filename)
    {
        ArrayList<Rater> loadedRaters = new ArrayList<>();
        FileResource fr = new FileResource(filename);
        CSVParser parse = fr.getCSVParser();
        for(CSVRecord record:parse)
        {
            Rater obj = new EfficientRater(record.get("rater_id"));
            obj.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
            loadedRaters.add(obj);
        }
        return loadedRaters;
    }



    public void testLoadRaters()
    {
//        ArrayList<Rater> result = loadRaters("inputs/ratings_short.csv");
        ArrayList<Rater> result = loadRaters("inputs/ratings.csv");
        HashMap<String,Integer> freqOfRaterIds = new HashMap<>(); //Id's and their total ratings
        for(Rater r:result)
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
        System.out.println("Total no of raters: "+freqOfRaterIds.size());

        System.out.println("********************************************************************************************");

        //for each rater, print the rater’s ID and the number of ratings they did on one line, followed by each rating (both the movie ID and the rating given) on a separate line.
        for(String s:freqOfRaterIds.keySet())  //for each id
        {
            System.out.println("id: "+s+" noOfRatings: "+freqOfRaterIds.get(s)); //printing id's and their total no of ratings given
            for(Rater r:result)  //for each id
            {
                if(r.getID().equals(s)) //Check for each id
                {
                    ArrayList<String> arr = r.getItemsRated(); // Movies that id rated
                    for(String str:arr) //for each movie
                    {
                        System.out.println("Movie Id: "+str+" rating: "+r.getRating(str)); //print its id and its rating
                    }
                }
            }
        }
        System.out.println("********************************************************************************************");


        //number of ratings for a rater with Id
        String inId = "193";
        for(String s1:freqOfRaterIds.keySet())
        {
            if(s1.equals(inId))
            {
                System.out.println("number of ratings for a rater with Id:"+inId+" is "+freqOfRaterIds.get(s1));
                break;
            }
        }

        System.out.println("********************************************************************************************");


        //maximum number of ratings by any rater
        int max = Collections.max(freqOfRaterIds.values());
        int c=0;
        for(String s1:freqOfRaterIds.keySet())
        {
            if(freqOfRaterIds.get(s1)==max)
            {
                c++;
                System.out.println("Rater with Id:"+s1+" gave "+max+" ratings and leads among all");
            }
        }
        System.out.println("total such lead raters are: "+c);
        System.out.println("********************************************************************************************");


        //Given a movie Id find by how many it was rated
        HashMap<String,Integer> movieTotalRatings = new HashMap<>();
        for(Rater r:result)
        {
            ArrayList<String> arr = r.getItemsRated(); //all movie Ids rated by Rater r
            for(String s:arr)
            {
                if(!movieTotalRatings.containsKey(s))
                {
                    movieTotalRatings.put(s,1);
                }
                else
                {
                    movieTotalRatings.put(s,movieTotalRatings.get(s)+1);
                }
            }
        }
        String movieId = "1798709";
        for(String s:movieTotalRatings.keySet())
        {
            if(s.equals(movieId)) {
                System.out.println(s + " - " + movieTotalRatings.get(s)+" rater/s");
                break;
            }
        }

        //code to determine how many different movies have been rated by all these raters
        System.out.println(movieTotalRatings.size()+" different movies have been rated by all raters");
        System.out.println("********************************************************************************************");



    }








    public void testLoadMovies()
    {
//        ArrayList<Movie> result = loadMovies("inputs/ratedmovies_short.csv");
        ArrayList<Movie> result = loadMovies("inputs/ratedmoviesfull.csv");
        for(Movie m:result)
        {
            System.out.println(m.getTitle());
        }
        System.out.println("Total no of movies read: "+result.size());

        System.out.println("********************************************************************************************");
        //Comedy genre movies
        System.out.println("Comedy movies are: ");
        int countComedy=0;
        for(Movie m:result)
        {
            if(m.getGenres().contains("Comedy")){
                System.out.println(m.getTitle());
                countComedy++;
            }
        }
        System.out.println("Total comedy movies are: "+countComedy);

        System.out.println("********************************************************************************************");


        //movie length greater that 150 mins
        System.out.println("Movies greater than 150 mins len: ");
        int count150LenMovies=0;
        for(Movie m:result)
        {
            if(m.getMinutes()>150) {
                System.out.println(m.getTitle());
                count150LenMovies++;
            }
        }
        System.out.println("Movies greater than 150 mins len: "+count150LenMovies);

        System.out.println("********************************************************************************************");

        //maximum movies by a director
        HashMap<String,Integer> freq = new HashMap<>();
        for(Movie m:result)
        {
            if(m.getDirector().contains(","))
            {
                for(String s:m.getDirector().split(","))
                {
                    if(!freq.containsKey(s))
                    {
                        freq.put(s,1);
                    }
                    else
                    {
                        freq.put(s,freq.get(s)+1);
                    }
                }
            }
            else
            {
                if(!freq.containsKey(m.getDirector()))
                {
                    freq.put(m.getDirector(),1);
                }
                else
                {
                    freq.put(m.getDirector(),freq.get(m.getDirector())+1);
                }
            }
        }
        int max = Collections.max(freq.values());
        System.out.println("maximum number of movies by any director is: "+max);
        int c=0;
        HashSet<String> directorNames = new HashSet<>();
        for(String s:freq.keySet())
        {
            if(freq.get(s)==max)
            {
                directorNames.add(s);
                c++;
            }
        }
        System.out.println("There are/is "+c+" director/s that directed "+max+" such films named "+directorNames.toString());
        System.out.println("********************************************************************************************");

    }


}
