package com.company.commands;

import com.company.speedbaccara.SpeedBaccara;

public class SpeedBaccaraGame extends Command {
    @Override
    public  void execute() {
        SpeedBaccara speedBaccara = new SpeedBaccara();
        speedBaccara.play();
    }
}
