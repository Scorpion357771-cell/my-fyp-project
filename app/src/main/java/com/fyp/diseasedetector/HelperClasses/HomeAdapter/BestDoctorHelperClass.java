package com.fyp.diseasedetector.HelperClasses.HomeAdapter;

public class BestDoctorHelperClass {
    int image;
    String title,description;


    public BestDoctorHelperClass(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
