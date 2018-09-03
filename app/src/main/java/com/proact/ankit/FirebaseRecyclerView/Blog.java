package com.proact.ankit.FirebaseRecyclerView;

public class Blog {

    private String image;
    private String name;
    private int pdfEnd;
    private int pdfStart;
    private int videoEnd;
    private int videoStart;


    Blog(){

    }

    public Blog(String image, String name, int pdfEnd, int pdfStart, int videoEnd, int videoStart) {
        this.image = image;
        this.name = name;
        this.pdfEnd = pdfEnd;
        this.pdfStart = pdfStart;
        this.videoEnd = videoEnd;
        this.videoStart = videoStart;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPdfEnd() {
        return pdfEnd;
    }

    public void setPdfEnd(int pdfEnd) {
        this.pdfEnd = pdfEnd;
    }

    public int getPdfStart() {
        return pdfStart;
    }

    public void setPdfStart(int pdfStart) {
        this.pdfStart = pdfStart;
    }

    public int getVideoEnd() {
        return videoEnd;
    }

    public void setVideoEnd(int videoEnd) {
        this.videoEnd = videoEnd;
    }

    public int getVideoStart() {
        return videoStart;
    }

    public void setVideoStart(int videoStart) {
        this.videoStart = videoStart;
    }
}
