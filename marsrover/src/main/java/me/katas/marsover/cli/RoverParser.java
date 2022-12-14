package me.katas.marsover.cli;

import static me.katas.marsrover.core.Orientation.EAST;
import static me.katas.marsrover.core.Orientation.NORTH;
import static me.katas.marsrover.core.Orientation.SOUTH;
import static me.katas.marsrover.core.Orientation.WEST;
import static me.katas.marsrover.utils.Checks.notNull;

import java.util.HashMap;
import java.util.Map;

import me.katas.marsrover.core.Orientation;
import me.katas.marsrover.core.Point;
import me.katas.marsrover.core.Rover;

public class RoverParser implements LineParser {

    private static final Map<String, Orientation> ORIENTATIONS = new HashMap<>();
    static {
        ORIENTATIONS.put("N", NORTH);
        ORIENTATIONS.put("W", WEST);
        ORIENTATIONS.put("E", EAST);
        ORIENTATIONS.put("S", SOUTH);
    }
        
    @Override
    public void ingest(Mission mission, String line) {
        try {
            String[] tokens = line.split(" ");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            Orientation o = notNull(ORIENTATIONS.get(tokens[2]));
            mission.load(new Rover(new Point(x,y), o, mission.plateau()));
        } catch (Exception ex) {
            System.err.println("Invalid rover input: '"+line+"'");
        }
    }

    @Override
    public LineParser next() {
        return LineParser.INSTRUCTIONS;
    }
}
