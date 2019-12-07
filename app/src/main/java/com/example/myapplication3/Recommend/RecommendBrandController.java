package com.example.myapplication3.Recommend;

import com.example.myapplication3.Entity.Brand;
import com.example.myapplication3.Entity.Category;
import com.example.myapplication3.Entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RecommendBrandController {
    private ArrayList<ArrayList<Brand>> brList;
    private HashMap<String, HashMap<String, Object>> AllUserMap;
    public ArrayList<ArrayList<Brand>> getBrList(){
        return this.brList;
    }
    public void getAllbrand(){
        Brand b = new Brand();
        brList=b.getAllBrand();
    }
    public void getAllUserRate(){
        User user = new User();





        AllUserMap = user.getAllUserMap();
    }
    public HashMap pearsonCheck(String id, boolean[] selectedCategory){
        HashMap<String, Object> UserRate =  AllUserMap.get(id);
        HashMap<String, String> recommendBrand = new HashMap<>();
        for(int i = 1; i<selectedCategory.length; i++){
            if(selectedCategory[i]==true)recommendBrand.put("", "c"+i);
        }
        ArrayList<Category> catList = new ArrayList<>();
        for(String CNr : recommendBrand.values()){
            Category tmp = new Category();
            tmp.setCNr(CNr);
            tmp.findBrands();
            catList.add(tmp);
        }
        HashMap<String, Double> list= new HashMap<>();
        for(String userid : AllUserMap.keySet()){
            double score = 0;
            double tmp = 0;
            int sum1 = 0;        int sum2 = 0;        int sumPow1 = 0;        int sumPow2 = 0;        int sum12 = 0;
            int count = 0;
            if(userid.equals(id)){
                continue;
            }
            else{
                HashMap<String, Object> tmpHshMap =  AllUserMap.get(userid);
                for(String ratedBrandByUser : UserRate.keySet()){
                    for(String ratedBrandByTmp : tmpHshMap.keySet()){
                        if(ratedBrandByUser.equals(ratedBrandByTmp)){
                            int num1 = Integer.valueOf(UserRate.get(ratedBrandByUser).toString()); int num2 = Integer.valueOf(UserRate.get(ratedBrandByUser).toString());;
                            sum1+=num1;
                            sum2+=num2;
                            sumPow1+=Math.pow(num1, 2);
                            sumPow2+=Math.pow(num2, 2);
                            sum12 += num1*num2;
                            count+=1;
                        }
                    }
                }
                score = (sum12-((sum1*sum2)/count)/Math.sqrt((sumPow1-(Math.pow(sum1,2)/count))*(sumPow2-(Math.pow(sum2,2)/count))));
                list.put(userid, score);
            }
        }
        HashMap<String, Double> pearsonList= new HashMap<>();
        Iterator it = sortByValue(list).iterator();
        while(it.hasNext()){
            String tmp = (String)it.next();
            pearsonList.put(tmp, list.get(tmp));
        }
        for(int i = 0; i< catList.size(); i++){
            String topBr = "";
            int max = 0;
            for(String userid:pearsonList.keySet() ){
                int fornum = 0;
                for(String brand: AllUserMap.get(userid).keySet()){
                    for(int j = 0; j<catList.get(i).getCatBrand().size(); j++){
                        fornum++;
                        if(catList.get(i).getCatBrand().get(j).equals(brand)){
                            if(max<Integer.parseInt(AllUserMap.get(userid).get(brand).toString())){
                                max=Integer.parseInt(AllUserMap.get(userid).get(brand).toString());
                                topBr = brand;
                            }
                        }
                    }

                }
                if(fornum>5)break;
            }
            recommendBrand.put(topBr, catList.get(i).getCNr());
        }
        return recommendBrand;
    }

    public static List sortByValue(final Map map){
        List<String> list = new ArrayList();
        list.addAll(map.keySet());

        Collections.sort(list,new Comparator(){

            public int compare(Object o1,Object o2){
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);

                return ((Comparable) v1).compareTo(v2);
            }

        });
        Collections.reverse(list); // 주석시 오름차순
        return list;
    }


//
  //  public Map<Integer,  >
}
