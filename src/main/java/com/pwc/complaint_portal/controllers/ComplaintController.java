package com.pwc.complaint_portal.controllers;

import com.pwc.complaint_portal.dto.ComplaintDto;
import com.pwc.complaint_portal.exceptions.ResourceNotFoundException;
import com.pwc.complaint_portal.models.Complaint;
import com.pwc.complaint_portal.models.ComplaintStatus;
import com.pwc.complaint_portal.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    //get all complaints
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<Complaint> getAllComplaints() {
        return complaintService.findAll();
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public Complaint createComplaint(@RequestBody ComplaintDto complaint, Principal principal) {
        return complaintService.save(principal, complaint);
    }

    @GetMapping("userId/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<Complaint> byUserId(@PathVariable("userId") Long userId) {
        return complaintService.findAllByUserId(userId);
    }

    //update complaints
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("{id}/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public Complaint updateComplaint(
            @PathVariable(value = "id") Long id,
            @PathVariable("status") ComplaintStatus status
    ) throws ResourceNotFoundException {
        return complaintService.update(id, status);
    }
}
