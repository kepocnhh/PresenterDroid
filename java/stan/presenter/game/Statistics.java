package stan.presenter.game;

import stan.presenter.core.Role;

public class Statistics
{
    int allgames;
        int finished;
            int wins;
            int losts;
        int waskilled;
    Role favorite;
    Role received;
    Role successful;

    public Statistics(int allgames, int wins, int losts, int waskilled, Role favorite, Role received, Role successful)
    {
        this.allgames = allgames;
        this.wins = wins;
        this.losts = losts;
        this.finished = wins + losts;
        this.waskilled = waskilled;
        this.favorite = favorite;
        this.received = received;
        this.successful = successful;
    }
}