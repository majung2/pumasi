package com.example.myapplication3.Pathfinding;

import com.example.myapplication3.Path;

public class PathfindingController { //finding path logic
    private Path path1;
    private Path path2;
    private Path path3;
    private Path path4;

    PathfindingController(){}
    PathfindingController(String recdate){ //날짜, 사용자가 선택한 매장들 넘겨받음
        path1 = new Path(recdate);
        path2 = new Path(recdate);
        path3 = new Path(recdate);
        path4 = new Path(recdate);



    }

    private void firstPathFinder(Path firstPath){ //결과 path, 사용자가 선택한 매장들

    }



}
