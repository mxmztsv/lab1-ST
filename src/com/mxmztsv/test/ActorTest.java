package com.mxmztsv.test;

import com.mxmztsv.main.Actor;
import com.mxmztsv.main.Condition;
import com.mxmztsv.main.Gender;
import com.mxmztsv.main.Location;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    Location a = new Location("Локация А", "Некая локация за пределами сцены");
    Location b = new Location("Локация Б", "Основное место действия");
    Actor Artur = new Actor("Артур", 22, Gender.MALE, a);

    @org.junit.jupiter.api.Test
    void moveTest() {
        Artur.move(a, b);
        assertEquals("Локация Б", Artur.getLocation().getName());
    }

    @org.junit.jupiter.api.Test
    void setAgeTest() {
        Artur.setAge(18);
        assertEquals(18, Artur.getAge());
    }

    @org.junit.jupiter.api.Test
    void setIncorrectAgeTest() {
        Exception exception = assertThrows(Exception.class, () -> Artur.setAge(-18));
        assertEquals("Возраст должен быть положительным", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void inspectPlaceTest() {
        System.setOut(new PrintStream(output));
        String expResult = "Артур осматривает место Локация А" + "\n" +
                "Артур получает информацию о месте: Некая локация за пределами сцены" + "\n";

        Artur.inspectPlace();
        String sout = output.toString();
        expResult = expResult.replaceAll("\n", "").replaceAll("\r", "");
        sout = sout.replaceAll("\n", "").replaceAll("\r", "");
        assertEquals(expResult, sout);
        System.setOut(null);
    }

    @org.junit.jupiter.api.Test
    void setConditionTest() {
        System.setOut(new PrintStream(output));
        String expResult = "У Артур отвисла челюсть";

        Artur.setCondition(Condition.SHOCKED);
        String sout = output.toString();
        sout = sout.replaceAll("\n", "").replaceAll("\r", "");

        assertEquals(expResult, sout);
        System.setOut(null);
    }
}