package com.example.myapplication3.Shopping;
import java.util.Map;

public class Brand {

    public String name;
    public String id;
    public Map<String, Boolean> bought;
    public String category;
    public Float rating;

    // Firestore에 연동하려면 빈 생성자가 필수이다.
    Brand() {}

    Brand(String name, Boolean buy) {
        this.name = name;
        this.id = id;
        this.bought = bought;
        this.category = category;
        this.rating = rating;
    }

}