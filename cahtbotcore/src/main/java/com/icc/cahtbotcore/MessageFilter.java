package com.icc.cahtbotcore;

public class MessageFilter {
    private String code = ".*[ABCDEFGHJKLMNPV]0\\d{3}\\D.*";
    private String locations = "수암관|수암도서관|수암관|수암도서관|도서관|수암관|WCC홀|한림관|방송실|한림관|" +
            "체육관관람석|한림관|체육관|홍지관|언어치료센터|홍지관|글로벌잡스테이션|잡스테이션|홍지관|대림아트홀|퇴계관|" +
            "에이스스테이션|퇴계관|대림학보사|대학본부|입학팀|대학본부|교육행정팀|대학본부|산학협력단|대학본부|링크사업단|" +
            "임곡관|보건실|전산관|스마트스테이션|";
    private String[] filter = locations.split("|");
    private boolean filterResult;

    public MessageFilter(){

    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setFilter(String locations, String regex) {
        this.filter = locations.split(regex);
    }

    public boolean getResult(String userMessage){
        for(String location : filter){
            if(userMessage.contains(location)){
                filterResult = true;
                break;
            }
        }
        if(userMessage.toUpperCase().matches(code) || filterResult){
            return true;
        }else{
            return false;
        }
    }
}
