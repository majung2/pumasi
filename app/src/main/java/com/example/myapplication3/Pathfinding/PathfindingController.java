package com.example.myapplication3.Pathfinding;

import androidx.annotation.NonNull;

import com.example.myapplication3.Entity.SpotsInMall;
import com.example.myapplication3.Entity.Path;
import com.example.myapplication3.Entity.Brand;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PathfindingController { //finding path logic
    private Integer startX, startY; //시작점 좌표
    private Integer nowX, nowY;
    private Path path1;
    private Path path2;
    private Path path3;
    private Path path4;
    private ArrayList<Brand> selectedBrandsNameList=new ArrayList<>(); //넘겨받은 브랜드명 정보
    private ArrayList<Brand> brandsList = new ArrayList<>(); //선택된 Brand 위치(좌표, 층수)정보
    private ArrayList<Brand> brands1 = new ArrayList<>(); //사용자가 선택한 매장들 중 1층에 있는 매장
    private ArrayList<Brand> brands2 = new ArrayList<>(); //사용자가 선택한 매장들 중 2층에 있는 매장
    private ArrayList<Brand> brands3 = new ArrayList<>(); //사용자가 선택한 매장들 중 3층에 있는 매장
    private ArrayList<SpotsInMall> move1 = new ArrayList<>(); // 1층 에스컬레이터및엘리베이터 정보
    private ArrayList<SpotsInMall> move2 = new ArrayList<>(); //2층 에스컬레이터및엘리베이터 정보
    private ArrayList<SpotsInMall> move3 = new ArrayList<>(); //3층 에스컬레이터및엘리베이터 정보
    private FirebaseFirestore db;
    private CollectionReference BrandRef;
    private ArrayList<String> cat= new ArrayList<>();


    public PathfindingController(){}
    public PathfindingController(ArrayList<Brand> selectedBrands,Integer currentX, Integer currentY,ArrayList<String> selectedCat){ //날짜, 사용자가 선택한 매장들, 시작점 넘겨받음
        String recdate = getToDay();
        path1 = new Path(recdate);
        path2 = new Path(recdate);
        path3 = new Path(recdate);
        path4 = new Path(recdate);
        //  this.selectedBrandsNameList = selectedBrands;
        for(int i=0;i<selectedBrands.size();i++){
            selectedBrandsNameList.add(selectedBrands.get(i));
        }
        this.cat = selectedCat;
        this.nowX = currentX;
        this.nowY = currentY;
        this.startX=currentX;
        this.startY=currentY;
        System.out.println("selected brand");
        System.out.println(this.nowX);
        System.out.println(selectedBrandsNameList.get(0).getSpotName());
        //     brandInfo();

        brandFloor(); //브랜드 층별로 분리

        firstPathFinder();
        FourthPathFinder();

    }




    private void brandFloor(){ //브랜드 층별로 분리
        for(Brand x : selectedBrandsNameList){
            System.out.println(x.getSpotFloor());
            System.out.println("층층");
            if(x.getSpotFloor()==1){
                brands1.add(x);
                System.out.println("1층");
            }
            else if(x.getSpotFloor()==2) {
                brands2.add(x);
                System.out.println("2층");
            }
            else {
                brands3.add(x);
                System.out.println("3층");
            }
        }

    }

    private SpotsInMall findClosestSpot(Integer nowX, Integer nowY, ArrayList<Brand> spotList){//현재 위치 좌표, 다음 방문 후보 list
        int closestSpotNum =0;
        double m=0,n=0;
        double currentDis= 1000000000;
        for(int i=0;i<spotList.size();i++){
            m=nowX-spotList.get(i).getSpotLocation().get(0);
            System.out.println("this is m: "+m);
            n=nowY-spotList.get(i).getSpotLocation().get(1);
            if(Math.sqrt((m*m)+(n*n)) < currentDis){
                currentDis = Math.sqrt((m*m)+(n*n));
                closestSpotNum = i;
            }
        }
        return spotList.get(closestSpotNum);
    }

    private void shortestPathFinder(ArrayList<Brand> brandFloor){ //결과 path, 사용자가 선택한 매장들
        //  ArrayList<Brand> leftList = brandFloor;
        ArrayList<Brand> leftList = new ArrayList<>();
        leftList = brandFloor;
        SpotsInMall next;
        while(leftList.size()!=0){
            System.out.println(this.nowX);
            next = findClosestSpot(this.nowX,nowY,leftList);
            path1.addBrandLast(next.getSpotName()); //Path에 해당 브랜드명 추가
            this.nowX = next.getSpotLocation().get(0); //now 좌표 업데이트
            this.nowY = next.getSpotLocation().get(1);
            leftList.remove(next);
        }
    }


    private void firstPathFinder(){
        if(brands1.size()!=0){ //1층에 있는 브랜드 선택한 것이 있을 경우
            nowX = startX;
            nowY = startY;
            System.out.println(startX);
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

    public static String getToDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}