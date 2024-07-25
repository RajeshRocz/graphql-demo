package com.graphql.graphql_demo.controller;

import com.graphql.graphql_demo.model.Player;
import com.graphql.graphql_demo.model.Team;
import com.graphql.graphql_demo.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQlController {

    private final PlayerService playerService;

    public GraphQlController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> findAll(){
        return playerService.findAll();
    }

    @QueryMapping
    public Player findOne(@Argument Integer id){
        return playerService.findOne(id);
    }

    @MutationMapping
    public Player delete(@Argument Integer id){
        return playerService.delete(id);
    }

    @MutationMapping
    public Player create(@Argument String name, @Argument Team team){
        return playerService.create(name, team);
    }

    @MutationMapping
    public Player update(@Argument Integer id, @Argument String name, @Argument Team team){
        return playerService.update(id, name, team);
    }

}
