package bt.bhudadapter.event.obj;

import java.util.Objects;

public class Agent
{
    private String name;
    private long id;
    private int prof;
    private int elite;
    private int self;
    private int team;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public int getProf()
    {
        return prof;
    }

    public void setProf(int prof)
    {
        this.prof = prof;
    }

    public int getElite()
    {
        return elite;
    }

    public void setElite(int elite)
    {
        this.elite = elite;
    }

    public int getSelf()
    {
        return self;
    }

    public void setSelf(int self)
    {
        this.self = self;
    }

    public int getTeam()
    {
        return team;
    }

    public void setTeam(int team)
    {
        this.team = team;
    }

    public boolean isBoss()
    {
        return this.elite == 0xffffffff
                && this.name != null
                && this.id != 0
                && this.prof != 0;
    }

    public boolean isPlayer()
    {
        return this.elite != 0xffffffff
                && this.name != null
                && this.id != 0;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Agent agent = (Agent)o;
        return id == agent.id && prof == agent.prof && elite == agent.elite && self == agent.self && team == agent.team && Objects.equals(name, agent.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, id, prof, elite, self, team);
    }

    @Override
    public String toString()
    {
        return "Agent{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", prof=" + prof +
                ", elite=" + elite +
                ", self=" + self +
                ", team=" + team +
                '}';
    }
}