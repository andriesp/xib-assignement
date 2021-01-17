package com.xib.assessment.controller;

import com.xib.assessment.dto.TeamDTO;
import com.xib.assessment.exception.InternalServerException;
import com.xib.assessment.model.Team;
import com.xib.assessment.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeamController extends BaseController {

    private final TeamService teamService;

    @GetMapping("team/{id}")
    public ResponseEntity<Team> findTeamById(@PathVariable("id") Long id) throws InternalServerException {
        return okResponse(teamService.findTeamById(id));
    }

    @GetMapping("team")
    public ResponseEntity<List<Team>> findAllAgents() throws InternalServerException {
        return okResponse(teamService.getAllTeams());
    }

    @PostMapping(value = "team", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Team> createTeam(@RequestBody TeamDTO teamDTO) throws InternalServerException {
        Team team = teamService.createTeam(teamDTO);
        return createdResponse(team.getId());
    }
}
