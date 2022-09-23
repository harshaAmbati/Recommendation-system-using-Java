package com.company;

public class MinutesFilter implements Filter{

    private int max;
    private int min;

    public MinutesFilter(int min,int max)
    {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean satisfies(String id) {
        if(MovieDatabase.getMinutes(id) >=min && MovieDatabase.getMinutes(id)<=max)
            return true;
        return false;
    }
}
