package com.pwc.complaint_portal.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "complaint")
@Getter
@Setter
@ToString
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "complaintTitle")
    private String complaintTitle;

    @Column(name = "complaint")
    private String complaint;

    @Column(name = "status")
    private ComplaintStatus status;

    @Column(name = "userId")
    private Long userId;
}

