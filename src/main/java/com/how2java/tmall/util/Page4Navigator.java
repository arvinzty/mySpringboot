package com.how2java.tmall.util;

import org.springframework.data.domain.Page;

import java.util.List;

public class Page4Navigator<T> {
    Page<T> pageFromJpa;
    int navigatePages;
    int totalPages;
    int number;
    long totalElements;
    int size;
    int numberOfElements;
    List<T> content;
    boolean isHasConten;
    boolean first;
    boolean last;
    boolean isHasNext;
    boolean isHasPrevirous;
    int[] navigatepageNums;

    public Page<T> getPageFromJpa() {
        return pageFromJpa;
    }

    public void setPageFromJpa(Page<T> pageFromJpa) {
        this.pageFromJpa = pageFromJpa;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> conten) {
        this.content = conten;
    }

    public boolean isHasConten() {
        return isHasConten;
    }

    public void setHasConten(boolean hasConten) {
        isHasConten = hasConten;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isHasNext() {
        return isHasNext;
    }

    public void setHasNext(boolean hasNext) {
        isHasNext = hasNext;
    }

    public boolean isHasPrevirous() {
        return isHasPrevirous;
    }

    public void setHasPrevirous(boolean hasPrevirous) {
        isHasPrevirous = hasPrevirous;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public Page4Navigator() {
        //这个空的分页是为了 Redis 从 json格式转换为 Page4Navigator 对象而专门提供的
    }


    public Page4Navigator(Page<T> pageFromJpa,int navigatePages){
        this.pageFromJpa=pageFromJpa;
        this.navigatePages=navigatePages;
        totalPages=pageFromJpa.getTotalPages();
        number=pageFromJpa.getNumber();
        totalElements=pageFromJpa.getTotalElements();
        size=pageFromJpa.getSize();
        numberOfElements=pageFromJpa.getNumberOfElements();
        content=pageFromJpa.getContent();
        isHasConten=pageFromJpa.hasContent();
        first=pageFromJpa.isFirst();
        last=pageFromJpa.isLast();
        isHasNext=pageFromJpa.hasNext();
        isHasPrevirous=pageFromJpa.hasPrevious();
        calcNavigatepageNums();
    }
    public void  calcNavigatepageNums(){
        int navigatepageNums[];
        int totalPages=getTotalPages();
        int num=getNumber();
        //当总页数小于或等于导航页码数时
        if(totalPages<=navigatePages) {
            navigatepageNums = new int[totalPages];
            for (int i = 0; i < totalPages; i++) {
                navigatepageNums[i] = i + 1;
            }
        }else {//当总页数大于导航页码数时
            navigatepageNums=new int[navigatePages];
            int startNum=num - navigatePages/2;
            int endNum=num + navigatePages/2;
            if (startNum<1){
                startNum=1;
                //(最前navigatePages页
                for (int i=0;i<navigatePages;i++){
                    navigatepageNums[i]=startNum++;
                }
            }else if (endNum > totalPages) {
                endNum = totalPages;
                //最后navigatePages页
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            }else{
                    //所有中间页
                    for (int i = 0; i < navigatePages; i++) {
                        navigatepageNums[i] = startNum++;
                    }
                }
            }
            this.navigatepageNums = navigatepageNums;
        }
}


