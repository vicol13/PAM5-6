package com.example.vicol.lab5pam.domain;

public class User {
    private String  FullName;
    private String  Birthday;
    private String  Email;
    private String  Phone;
    private String  Address;
    private String  Username;
    private String  Password;
    private String  Base64Photo ;

    public User(String fullName, String birthday, String email, String phone, String address, String username, String base64Photo) {
        FullName = fullName;
        Birthday = birthday;
        Email = email;
        Phone = phone;
        Address = address;
        Username = username;
        Base64Photo = base64Photo;
    }

    public User(String fullName, String birthday, String email, String phone, String address, String username, String password, String base64Photo) {
        FullName = fullName;
        Birthday = birthday;
        Email = email;
        Phone = phone;
        Address = address;
        Username = username;
        Password = password;
        this.Base64Photo = base64Photo;
//        Base64Photo = "iVBORw0KGgoAAAANSUhEUgAAACAAAAAeCAYAAABNChwpAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAIGNIUk0AAHolAACAgwAA+f8AAIDpAAB1MAAA6mAAADqYAAAXb5JfxUYAAAIoSURBVHjaxJe7a1RBFMZ/e0kUw8oEB6IZ/AN8okKQiK4bm5hGTUBsVCwExYggBHw0Woj46ARriyB2CotVQBRsFQQh2ETRBKbQHMOB+AqBtZli97K77r3eu3tguMyD831zzsx35hYWl5Q0Zr05AtwGloCz4nQujZ+elOBFYBroD0MPgbE0viLS2akacIBR682WThK4GOsXgMmOELDelIEdDaZOh9TkHoFmO+0HTuZKwHozCEwkSE3mETgH9LaY32m9OZgLAetND3A+xQFNpgPWm/XAhppmw3cXMNiGz3HrzVVgEfgeayJOf9ddn+osJ4ArwOYA1Eu+9jOQ+QhcKlRn+QH00R17HaWV44ysLwIuAKtdAP8KTEbi9BFwGJAOgr8FhsTpmwhAnL4E9gLvOwA+DZTE6UKdDojTT8B+4FlOwKvAZXF6pvYq1gmROF0GjgM3gWqG4N+AUXH6ID5RaPYist4cAx4Dxf8EfwdMiNMviaRYnFaAJxnsfqoZeDu1oJQBgVKqYmS92QhszYBAOW01LGd0APdZb9amITCSEYF1QWO6FoGWvqIm+R9oI/8fwr/ALeDPP9YeShqBkfDUbmS/gOvAbnE6I05vhFfyTAsCw9abNUkIHGgy/hzYJk7vitOVGs2YE6djQUXnG5VdYCgJgU2x/jwwLk6PitPPLcTrKbAduAesxKaLSQjcARZCuO+HXVfaOW3idFmcXgP2AC/CcAV41Wj93wEAdguYnKEnRU4AAAAASUVORK5CYII=";
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getBase64Photo() {
        return Base64Photo;
    }

    public void setBase64Photo(String base64Photo) {
        Base64Photo = base64Photo;
    }


}



