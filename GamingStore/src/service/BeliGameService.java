package service;

import Model.BeliGame;
import java.util.List;

public interface BeliGameService {
    boolean addBeliGame(BeliGame newBeliGame);
    List<BeliGame> getAllBeliGames();
    BeliGame getBeliGameByEmail(String email);
    boolean updateBeliGame(BeliGame updatedBeliGame);
    boolean deleteBeliGameByEmail(String email);
}
