package com.example.myapplication3.Pathfinding;

import com.example.myapplication3.Path;
import com.example.myapplication3.Entity.Brand;

import java.util.ArrayList;


public class PathfindingController { //finding path logic
    private Path path1;
    private Path path2;
    private Path path3;
    private Path path4;
    private ArrayList<Brand> brandsList;
    private ArrayList<Brand> brands1 = new ArrayList<>();
    private ArrayList<Brand> brands2 = new ArrayList<>();
    private ArrayList<Brand> brands3 = new ArrayList<>();
    private double maps1[][]; //1층 브랜드간 거리 저장할 변수
    private double maps2[][]; //2층 브랜드간 거리 저장할 변수
    private double maps3[][]; //3층 브랜드간 거리 저장할 변수

    PathfindingController(){}
    PathfindingController(String recdate, ArrayList<Brand> selectedBrands){ //날짜, 사용자가 선택한 매장들 넘겨받음
        path1 = new Path(recdate);
        path2 = new Path(recdate);
        path3 = new Path(recdate);
        path4 = new Path(recdate);
        this.brandsList = selectedBrands;

        brandFloor(); //브랜드 층별로 분리
        disCal(); //선택된 브랜드간 거리계산

    }

    private void brandFloor(){ //브랜드 층별로 분리
        for(Brand x : brandsList){
            if(x.getSpotFloor()==1){
                brands1.add(x);
            }
            else if(x.getSpotFloor()==2) {
                brands2.add(x);
            }
            else {
                brands3.add(x);
            }
        }

    }

    private void disCal(){ //선택된 브랜드 간 거리계산
        maps1 = new double[brands1.size()][brands1.size()];
        maps2 = new double[brands2.size()][brands2.size()];
        maps3 = new double[brands3.size()][brands3.size()];
        int m,n; //두 매장 사이 거리 (m:x값 차, n:y값 차)
        Brand point1, point2; //선택된 두 매장

        for(int i=0; i<brands1.size();i++){
            for(int j=i;j<brands1.size();j++){
                point1 = brands1.get(i);
                point2 = brands1.get(j);
                m = point1.getSpotLocation().get(0) - point2.getSpotLocation().get(0);
                n = point1.getSpotLocation().get(1) - point2.getSpotLocation().get(1);
                maps1[i][j]= Math.sqrt((m*m)+(n*n));
            }
        }
        for(int i=0; i<brands2.size();i++){
            for(int j=i;j<brands2.size();j++){
                point1 = brands2.get(i);
                point2 = brands2.get(j);
                m = point1.getSpotLocation().get(0) - point2.getSpotLocation().get(0);
                n = point1.getSpotLocation().get(1) - point2.getSpotLocation().get(1);
                maps1[i][j]= Math.sqrt((m*m)+(n*n));
            }
        }
        for(int i=0; i<brands3.size();i++){
            for(int j=i;j<brands3.size();j++){
                point1 = brands3.get(i);
                point2 = brands3.get(j);
                m = point1.getSpotLocation().get(0) - point2.getSpotLocation().get(0);
                n = point1.getSpotLocation().get(1) - point2.getSpotLocation().get(1);
                maps1[i][j]= Math.sqrt((m*m)+(n*n));
            }
        }
    }

    private void dijkstra(){
     

    }



    public void firstPathFinder(Path firstPath){ //결과 path, 사용자가 선택한 매장들

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
