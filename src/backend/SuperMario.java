package backend;

import backend.block.Saleable;
import backend.block.mario.*;

import java.util.ArrayList;
import java.util.List;

public class SuperMario {
    static Saleable[] saleable = {new SimpleMario(), new CoinerMario(), new JumperMario(), new KillerMario(), new FasterMario()};
    public final List<User> users = new ArrayList<>();
    public User currentUser;
}
