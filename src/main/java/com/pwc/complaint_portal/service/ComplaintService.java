package com.pwc.complaint_portal.service;

import com.pwc.complaint_portal.models.Complaint;
import com.pwc.complaint_portal.repository.ComplaintRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ComplaintService {

    private ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public List<Complaint> findAll(){
        return this.complaintRepository.findAll();
    }

    public Complaint save(Complaint complaint){
        return this.complaintRepository.save(complaint);
    }

    public Optional<Complaint> findById(Long id) {
        return this.complaintRepository.findById(id);
    }


}