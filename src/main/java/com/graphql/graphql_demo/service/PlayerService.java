package com.graphql.graphql_demo.service;

import com.graphql.graphql_demo.model.Player;
import com.graphql.graphql_demo.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static com.graphql.graphql_demo.model.Team.*;

@Service
public class PlayerService {
    private List<Player> playerList=new ArrayList<>();
    AtomicInteger id =new AtomicInteger(0);

    public List<Player> findAll(){
        return playerList;
    }

    public Player findOne(Integer id){
        return playerList.stream().filter(player -> Objects.equals(player.id(), id))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Player not found"));
    }

    public Player create(String name, Team team){
        Player player=new Player(id.incrementAndGet(), name,team);
        playerList.add(player);
        return player;
    }

    public Player update(Integer id, String name, Team team){
        Player updatedPlayer= new Player(id, name,team);
        Player currentPlayer= playerList.stream().filter(player->player.id().equals(id))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("player not found"));
        int index= playerList.indexOf(currentPlayer);
        playerList.set(index, updatedPlayer);
        return updatedPlayer;
    }

    public Player delete(Integer id){
        Player player=playerList.stream().filter(p->p.id().equals(id))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Player not found"));
        playerList.remove(player);
        return player;
    }

    @PostConstruct
    public void init(){
        playerList.add(new Player(id.incrementAndGet(),"MS Dhoni",CSK));
        playerList.add(new Player(id.incrementAndGet(),"Rohit",MI));
        playerList.add(new Player(id.incrementAndGet(),"Gill",GT));
    }

}
