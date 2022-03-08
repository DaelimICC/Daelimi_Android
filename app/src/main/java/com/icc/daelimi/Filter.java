package com.icc.daelimi;

/**
 * 입력 메세지 에서 학교내의 건물이나 시설의 이름, 강의실 코드가 있는지 판단한다.
 * 판단 결과에 따라 필터값을 정한 Request 객체 생성
 */

public class Filter {
    private String locationCodeFilter = ".*[ABCDEFGHJKLMNPV]0\\d{3}.*";
    private String locationNameFilter = ".*수암도서관.*|.*도서관.*|.*WCC홀.*|.*한림관.*|.*방송실.*|.*체육관관람석.*|" +
                                        ".*체육관.*|.*언어치료센터.*|.*글로벌잡스테이션.*|.*잡스테이션.*|" +
                                        ".*대림아트홀.*|.*에이스스테이션.*|.*대림학보사.*|.*학보사.*|.*입학팀.*|.*교육행정팀.*|.*산학협력단.*|" +
                                        ".*링크사업단.*|.*보건실.*|.*스마트스테이션.*|.*매점.*|.*교내서점.*|.*서점.*|.*안경점.*|.*교내카페.*|.*카페.*";

    private int isFilter;

    public Filter(){

    }

    public void setLocationCodeFilter(String locationCodeFilter) {
        this.locationCodeFilter = locationCodeFilter;
    }

    public void setLocationNameFilter(String locationNameFilter) {
        this.locationNameFilter = locationNameFilter;
    }

    public String getLocationCodeFilter() {
        return locationCodeFilter;
    }

    public String getLocationNameFilter() {
        return locationNameFilter;
    }
    //사용자가 장소를 물어보는 건지 판별 후 필터 값을 정하여 서버로 보낼 리퀘스트 생성
    public Request sendMessage(String userMessage){
        if(userMessage.toUpperCase().matches(locationCodeFilter) || userMessage.matches(locationNameFilter)){
            isFilter = 1;
        }else{
            isFilter = 0;
        }

        return new Request(isFilter, userMessage);
    }
}
