package com.example.artem.Home.utility;

public class Arts {
    private String Artist;
    private long Art_id;
    private String time_stamp;
    private String title;
    private String imageId;

    public Arts(String artist, String Title, long art_id, String time_stamp, String ImageId) {
        Artist = artist;
        title = Title;
        Art_id = art_id;
        this.time_stamp = time_stamp;
        imageId = ImageId;

    }

    public String getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return Artist;
    }

    public long getArt_id() {
        return Art_id;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

}
