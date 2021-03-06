package com.pwc.complaint_portal.service;

import com.pwc.complaint_portal.PrincipalUtil;
import com.pwc.complaint_portal.dto.ComplaintDto;
import com.pwc.complaint_portal.exceptions.ResourceNotFoundException;
import com.pwc.complaint_portal.models.Complaint;
import com.pwc.complaint_portal.models.ComplaintStatus;
import com.pwc.complaint_portal.repository.ComplaintRepository;
import com.pwc.complaint_portal.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public List<Complaint> findAll(){
        return this.complaintRepository.findAll();
    }
    public List<Complaint> findAllByUserId(Long userId) {
        return complaintRepository.findAllByUserId(userId);
    }
    public Optional<Complaint> findById(Long id) { return this.complaintRepository.findById(id); }

    public Complaint save(Principal principal, ComplaintDto dto) {
        UserDetailsImpl userDetails = PrincipalUtil.parse(principal);

        Complaint newComplaint = new Complaint();
        newComplaint.setComplaint(dto.getComplaint());
        newComplaint.setComplaintTitle(dto.getComplaintTitle());
        newComplaint.setStatus(ComplaintStatus.PENDING);
        newComplaint.setUserId(userDetails.getId());
        return complaintRepository.save(newComplaint);
    }

    public Complaint update(Long id, ComplaintStatus newStatus) throws ResourceNotFoundException {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found for this id :: " + id));
        complaint.setStatus(newStatus);
        return complaintRepository.save(complaint);
    }

    public void delete(Long id) throws ResourceNotFoundException {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found for this id :: " + id));
        complaintRepository.delete(complaint);
    }

}