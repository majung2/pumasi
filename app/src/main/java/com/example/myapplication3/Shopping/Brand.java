package com.example.myapplication3.Shopping;

public class Brand {

    public String name;
    public Boolean buy;
    public String category;
    public Integer rating;

    // Firestore에 연동하려면 빈 생성자가 필수이다.
    Brand() {}

    Brand(String name, Boolean buy) {
        this.name = name;
        this.buy = buy;
        this.category = category;
        this.rating = rating;
    }

}