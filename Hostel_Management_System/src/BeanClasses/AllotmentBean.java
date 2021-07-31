/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanClasses;

import java.sql.Date;

/**
 *
 * @author Danish
 */
public class AllotmentBean {

    public int getStdId() {
        return stdId;
    }

    public void setStdId(int stdId) {
        this.stdId = stdId;
    }

    public int getHostelId() {
        return hostelId;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    public int getAllotId() {
        return allotId;
    }

    public void setAllotId(int allotId) {
        this.allotId = allotId;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getChallanNo() {
        return challanNo;
    }

    public void setChallanNo(int challanNo) {
        this.challanNo = challanNo;
    }

    public Date getChallanDate() {
        return challanDate;
    }

    public void setChallanDate(Date challanDate) {
        this.challanDate = challanDate;
    }

    public int getChallanAmount() {
        return challanAmount;
    }

    public void setChallanAmount(int challanAmount) {
        this.challanAmount = challanAmount;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    public String getReasonOfLeaving() {
        return reasonOfLeaving;
    }

    public void setReasonOfLeaving(String reasonOfLeaving) {
        this.reasonOfLeaving = reasonOfLeaving;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    int stdId;
    int hostelId;
    int allotId;
    int roomNo;
    int challanNo;
    Date challanDate;
    int challanAmount;
    Date leavingDate;
    String reasonOfLeaving;
    String remarks;
}
