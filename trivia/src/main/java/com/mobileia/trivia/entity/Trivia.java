package com.mobileia.trivia.entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by matiascamiletti on 3/2/18.
 */

public class Trivia {

    public int id;

    public String title;

    public String caption;

    public String photo;

    public String start_date;

    public String end_date;

    public ArrayList<Option> options;

    public int vote = -1;
}
