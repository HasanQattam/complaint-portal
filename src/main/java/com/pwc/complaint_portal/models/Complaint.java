package com.pwc.complaint_portal.models;

import javax.persistence.*;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "complaintTitle")
    private String complaintTitle;

    @Column(name = "complaint")
    private String complaint;

    @Column(name = "status")
    private String status;

    public Complaint() {
        super();
    }

    public Complaint(Long id, String complaintTitle, String complaint, String status) {
        this.id = id;
        this.complaintTitle = complaintTitle;
        this.complaint = complaint;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComplaintTitle() {
        return complaintTitle;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

