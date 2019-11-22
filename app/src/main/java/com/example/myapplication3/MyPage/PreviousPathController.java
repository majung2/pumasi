package com.example.myapplication3.MyPage;


public class PreviousPathController {

    private String path = "0";
    private PreviousPathSpecificBoundary user1;


    public void loadPath(String selectedPath){

        path = selectedPath;

    }

    public String getPath()
    {
        return path;
    }
}
