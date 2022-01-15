public class Stuff {
    protected int id;
    protected String name;
    protected RoleUser role;

    public Stuff(int id) {
        this.id = id;
    }

    public Stuff(int id, String name, RoleUser role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }



}

