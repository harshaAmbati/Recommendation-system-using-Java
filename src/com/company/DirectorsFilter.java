package com.company;

import java.util.ArrayList;

public class DirectorsFilter implements Filter {

    private ArrayList<String> directors;
    public DirectorsFilter(ArrayList<String> dir)
    {
        directors = new ArrayList<>(dir);
    }

    @Override
    public boolean satisfies(String id) {
//        for(String s:directors)
//        {
//            if(s.contains(MovieDatabase.getDirector(id)) || s.equals(MovieDatabase.getDirector(id)))
//            {
//                return true;
//            }
//        }
//        return false;
        boolean b1 = false;
        String s = MovieDatabase.getDirector(id);
//        System.out.println(directors);
        for (String d:directors)
        {
            if(s.contains(d) || s.equals(d))
            {
                b1=true;
            }
        }
        return b1;
    }
}
