package com.recipesbook.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recipesbook.Dto.VoteDto;
import com.recipesbook.Service.VoteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/votes/")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) {
		System.out.println("<<<<<<<<<<<<<<<<<< VoteController " + voteDto.toString());
        voteService.vote(voteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}