package com.example.raptor.shopping;

/**
 * Created by android on 2017-03-10.
 */

public class ShoppingItem {
    private long id;
    private String title;
    private int quality;
    private int idImage;

    public ShoppingItem(int id, String title, int quality, int idImage) {
        this.id = id;
        this.title = title;
        this.quality = quality;
        this.idImage = idImage;
    }

    public long get_id() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getQuality() {
        return quality;
    }

    public Integer getIdImage() {
        return idImage;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void setIdImage(Integer idImage) {
        this.idImage = idImage;
    }
}
