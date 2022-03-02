package com.icc.daelimi;

public class Filter {
    private String locationCodeFilter = ".*[ABCDEFGHJKLMNPV]0\\d{3}.*";
    private String locationNameFilter = ".*수암관|수암도서관|도서관|WCC홀|한림관|방송실|" +
                                        "체육관관람석|체육관|홍지관|언어치료센터|글로벌잡스테이션|" +
                                        "잡스테이션|대림아트홀|퇴계관|에이스스테이션|대림학보사|대학본부|" +
                                        "입학팀|교육행정팀|산학협력단|링크사업단|임곡관|보건실|전산관|스마트스테이션.*";
    private int isFilter;

    public Filter(){

    }

    public void setLocationCodeFilter(String locationCodeFilter) {
        this.locationCodeFilter = locationCodeFilter;
    }

    public void setLocationNameFilter(String locationNameFilter) {
        this.locationNameFilter = locationNameFilter;
    }

    public Request sendMessage(String userMessage){
        if(userMessage.toUpperCase().matches(locationCodeFilter) || userMessage.matches(locationNameFilter)){
            isFilter = 1;
        }else{
            isFilter = 0;
        }

        return new Request(isFilter, userMessage);
    }
}
