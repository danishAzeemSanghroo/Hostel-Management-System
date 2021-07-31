/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanClasses;

/**
 *
 * @author Danish
 */
public class HostelsBean {

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public int getNumOfBathrooms() {
        return numOfBathrooms;
    }

    public void setNumOfBathrooms(int numOfBathrooms) {
        this.numOfBathrooms = numOfBathrooms;
    }

    public int getNumOfStories() {
        return numOfStories;
    }

    public void setNumOfStories(int numOfStories) {
        this.numOfStories = numOfStories;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public int getStdPerRoom() {
        return stdPerRoom;
    }

    public void setStdPerRoom(int stdPerRoom) {
        this.stdPerRoom = stdPerRoom;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String toString(){
    return hostelName;
    }
    
    int hotelId;

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }
    String hostelName;
    int numOfRooms;
    int numOfBathrooms;
    int numOfStories;
    String mess;
    int stdPerRoom;
    String remarks;
    
}
