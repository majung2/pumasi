package com.example.myapplication3.Pathfinding;

import com.example.myapplication3.Path;

public class PathfindingController { //finding path logic
    private Path path1;
    private Path path2;
    private Path path3;
    private Path path4;
    //private ArrayList<Brand> brandsList;
    //private ArrayList<Brand> brands1 = new ArrayList<>();
    //private ArrayList<Brand> brands2 = new ArrayList<>();
    //private ArrayList<Brand> brands3 = new ArrayList<>();
    private int maps1[][]; //1층 브랜드간 거리 저장할 변수
    private int maps2[][]; //2층 브랜드간 거리 저장할 변수
    private int maps3[][]; //3층 브랜드간 거리 저장할 변수

    PathfindingController(){}
    PathfindingController(String recdate){ //날짜, 사용자가 선택한 매장들 넘겨받음 ArrayList<Brand> brands
        path1 = new Path(recdate);
        path2 = new Path(recdate);
        path3 = new Path(recdate);
        path4 = new Path(recdate);
//      this.brandsList = brands;

    }

    private void brandFloor(){ //브랜드 층별로 분리
        /*
        for(Brand x : brandsList){ //브랜드 entity
            if(x.floor==1){ // 브랜드 entity에서 층수정보
                n1++;
                brands1.add(x);
            }
            else if(x.floor==2) {
                n2++;
                brands2.add(x);
            }
            else {
                n3++;
                brands3.add(x);
            }
        }

         */
    }

    private void disCal(){ //선택된 브랜드 간 거리계산

    }



    private void firstPathFinder(Path firstPath){ //결과 path, 사용자가 선택한 매장들

    }










    public Path getPath1(){
        return path1;
    }

    public Path getPath2(){
        return path2;
    }

    public Path getPath3(){
        return path3;
    }

    public Path getPath4(){
        return path4;
    }
}
