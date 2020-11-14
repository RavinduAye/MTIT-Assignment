package com.example.joke_app;

public class Joke {
    private int ID;
    private String Type;
    private String Setup;
    private String PunchLine;

    public Joke(int id, String type, String setup, String punchLine) {
        ID = id;
        Type = type;
        Setup = setup;
        PunchLine = punchLine;
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        ID = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSetup() {
        return Setup;
    }

    public void setSetup(String setup) {
        Setup = setup;
    }

    public String getPunchLine() {
        return PunchLine;
    }

    public void setPunchLine(String punchLine) {
        PunchLine = punchLine;
    }
}
