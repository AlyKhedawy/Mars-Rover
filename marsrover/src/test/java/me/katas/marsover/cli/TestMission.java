package me.katas.marsover.cli;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import me.katas.marsrover.core.Orientation;
import me.katas.marsrover.core.Plateau;
import me.katas.marsrover.core.Point;
import me.katas.marsrover.core.Rover;
import me.katas.marsrover.core.instructions.Instruction;
import me.katas.marsrover.core.instructions.Left;
import me.katas.marsrover.core.instructions.Move;
import me.katas.marsrover.core.instructions.Right;
import me.katas.marsrover.core.instructions.Back;


public class TestMission {

    @Test
    public void shouldLoadPlateauAsFirstLine() {
        Mission mission = new Mission();

        mission.process("5 5");

        assertEquals(new Plateau(5, 5), mission.plateau());
    }

    @Test
    public void shouldLoadRobotAsSecondLine() {
        Mission mission = new Mission();

        mission.process("5 5");
        mission.process("1 2 N");

        assertEquals(new Rover(new Point(1, 2), Orientation.NORTH, mission.plateau()), mission.rover());
    }

    @Test
    public void shouldLoadInstructionsAsThirdLine() {
        Mission mission = new Mission();

        mission.process("5 5");
        mission.process("1 2 N");
        mission.process("LFRB");

        List<Instruction> expected = Arrays.asList(new Left(), new Move(), new Right(),new Back());
        assertEquals(expected, mission.instructions());
    }

    @Test
    public void shouldProduceOutputAfterThirdLine() {
        Mission mission = new Mission();

        mission.process("5 5");
        mission.process("1 2 N");
        mission.process("LFR");

        assertEquals("0 2 N\n", mission.output());
    }

    @Test
    public void shouldProduceOutputWithMultipleRobots() {
        Mission mission = new Mission();

        mission.process("5 5");
        mission.process("1 2 N");
        mission.process("LFR");
        mission.process("3 3 S");
        mission.process(" RRF");

        assertEquals("0 2 N\n3 4 N\n", mission.output());
    }

    @Test
    public void shouldProduceOutputWithOriginalProblemStatementInput() {
        Mission mission = new Mission();

        mission.process("5 5");
        mission.process("1 2 N");
        mission.process("LFLFLFLFF");
        
        
        mission.process("4 2 E");
        mission.process("FLFFFRFLB");

        assertEquals("1 3 N\n5 5 N\n", mission.output());
    }
    
   

}
