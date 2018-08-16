package com.tech387.wokroutapp.data;

public class Video {

    private String mVideo;
    private String mImg;

    public Video(String mVideo, String mImg) {
        this.mVideo = mVideo;
        this.mImg = mImg;
    }

    public String getVideo() {
        return mVideo;
    }

    public void setVideo(String mVideo) {
        this.mVideo = mVideo;
    }

    public String getImg() {
        return mImg;
    }

    public void setImg(String mImg) {
        this.mImg = mImg;
    }
}
