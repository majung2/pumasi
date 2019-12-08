package com.example.myapplication3.Pathfinding;

import com.example.myapplication3.Entity.SpotsInMall;
import com.example.myapplication3.Entity.Path;
import com.example.myapplication3.Entity.Brand;

import java.util.ArrayList;


public class PathfindingController { //finding path logic
    private Integer startX, startY; //시작점 좌표
    private Integer nowX, nowY;
    private Path path1;
    private Path path2;
    private Path path3;
    private Path path4;
    private ArrayList<Brand> brandsList;
    private ArrayList<Brand> brands1 = new ArrayList<>(); //사용자가 선택한 매장들 중 1층에 있는 매장
    private ArrayList<Brand> brands2 = new ArrayList<>(); //사용자가 선택한 매장들 중 2층에 있는 매장
    private ArrayList<Brand> brands3 = new ArrayList<>(); //사용자가 선택한 매장들 중 3층에 있는 매장
    private ArrayList<SpotsInMall> move1 = new ArrayList<>(); // 1층 에스컬레이터및엘리베이터 정보
    private ArrayList<SpotsInMall> move2 = new ArrayList<>(); //2층 에스컬레이터및엘리베이터 정보
    private ArrayList<SpotsInMall> move3 = new ArrayList<>(); //3층 에스컬레이터및엘리베이터 정보


    PathfindingController(){}
    PathfindingController(String recdate, ArrayList<Brand> selectedBrands){ //날짜, 사용자가 선택한 매장들, 시작점 넘겨받음
        path1 = new Path(recdate);
        path2 = new Path(recdate);
        path3 = new Path(recdate);
        path4 = new Path(recdate);
        this.brandsList = selectedBrands;

        brandFloor(); //브랜드 층별로 분리

        firstPathFinder();
        FourthPathFinder();

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

    private SpotsInMall findClosestSpot(Integer nowX, Integer nowY, ArrayList<Brand> spotList){//현재 위치 좌표, 다음 방문 후보 list
        int closestSpotNum =0;
        double m,n;
        double currentDis= 1000000000;
        for(int i=0;i<spotList.size();i++){
            m=nowX-spotList.get(i).getSpotLocation().get(0);
            n=nowY-spotList.get(i).getSpotLocation().get(1);
            if(Math.sqrt((m*m)+(n*n)) < currentDis){
                currentDis = Math.sqrt((m*m)+(n*n));
                closestSpotNum = i;
            }
        }
        return spotList.get(closestSpotNum);
    }

    private void shortestPathFinder(ArrayList<Brand> brandFloor){ //결과 path, 사용자가 선택한 매장들
        ArrayList<Brand> leftList = brandFloor;
        SpotsInMall next;
        while(leftList.size()!=0){
            next = findClosestSpot(nowX,nowY,leftList);
            path1.addBrandLast(next.getSpotName()); //Path에 해당 브랜드명 추가
            nowX = next.getSpotLocation().get(0); //now 좌표 업데이트
            nowY = next.getSpotLocation().get(1);
            leftList.remove(next);
        }
    }


    private void firstPathFinder(){
        if(brands1.size()!=0){ //1층에 있는 브랜드 선택한 것이 있을 경우
            nowX = startX;
            nowY = startY;
            path1.addFirst("현위치");
            shortestPathFinder(brands1); //1층에 있는 브랜드 먼저 길찾기
            if(brands2.size()!=0){ //2층 브랜드도 선택한 경우
                shortestPathFinder(brands2);
            }
            else if(brands3.size()!=0){
                shortestPathFinder(brands3);
            }
        }
        else if(brands2.size()!=0){
            shortestPathFinder(brands2);
            if(brands3.size()!=0) {
                shortestPathFinder(brands3);
            }
        }
        else{
            shortestPathFinder(brands3);
        }
    }

    private void FourthPathFinder(){
        //세일하는 매장들만 남기기
        for (Brand x : brands1){
            if (!x.getSale())
                brands1.remove(x);
        }
        for (Brand x : brands2){
            if (!x.getSale())
                brands1.remove(x);
        }
        for (Brand x : brands3){
            if (!x.getSale())
                brands1.remove(x);
        }

        firstPathFinder();

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










/*
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
*/



}

