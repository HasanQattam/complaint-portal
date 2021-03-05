package com.pwc.complaint_portal.controllers;

import com.pwc.complaint_portal.exceptions.ResourceNotFoundException;
import com.pwc.complaint_portal.models.Complaint;
import com.pwc.complaint_portal.service.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1")
public class ComplaintController {

    private ComplaintService complaintService;
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    //get all complaints
    @GetMapping("complaints")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Complaint> getAllComplaints(){
        return this.complaintService.findAll();
    }

    @PostMapping("/complaints")
    @PreAuthorize("hasRole('USER')")
    public void createComplaint( @RequestBody Complaint complaint) {
        complaintService.save(complaint);
    }


    //update complaints
    @PutMapping("/complaints/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Complaint> updateComplaint(@PathVariable(value = "id") Long id,
                                                   @RequestBody Complaint complaintDetails) throws ResourceNotFoundException {
        Complaint complaint = complaintService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not found for this id :: " + id));

        complaint.setId(complaintDetails.getId());
        complaint.setComplaintTitle(complaintDetails.getComplaintTitle());
        complaint.setComplaint(complaintDetails.getComplaint());
        complaint.setStatus(complaintDetails.getStatus());

        final Complaint updatedEmployee = complaintService.save(complaint);
        return ResponseEntity.ok(updatedEmployee);
    }

}
