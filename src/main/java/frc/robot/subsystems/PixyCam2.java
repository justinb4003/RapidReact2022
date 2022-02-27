// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.links.SPILink;

public class PixyCam2 extends SubsystemBase {
  Pixy2 pixycam;
  boolean isCamera = false;
  int state = -1;
  Block currentBlue = null;
  Block currentRed = null;
  /** Creates a new Pixy2. */
  public PixyCam2() {
    pixycam = Pixy2.createInstance(Pixy2.LinkType.SPI);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (!isCamera) {
      System.out.println(System.currentTimeMillis());
      state = pixycam.init();
    }
    isCamera = state >= 0;
    SmartDashboard.putBoolean("Camera", isCamera);
    if (!isCamera) return;
    
    int blockCountBlue = pixycam.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 15);
    //ArrayList<Block> blueBlocks = pixycam.getCCC().getBlockCache();
    int blockCountRed = pixycam.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG2, 15);
    ArrayList<Block> blocks = pixycam.getCCC().getBlockCache();
    ArrayList<Block> blueBlocks = new ArrayList<Block>();
    ArrayList<Block> redBlocks = new ArrayList<Block>();
    for(Block block : blocks){
      if(block.getSignature() == 1){
        blueBlocks.add(block);
      }
      if(block.getSignature() == 2){
        redBlocks.add(block);
      }
    }


    SmartDashboard.putNumber("number of Blue Blocks", blueBlocks.size());
    SmartDashboard.putNumber("number of Red Blocks", redBlocks.size());
    currentBlue = pickBlock(blueBlocks);
    currentRed = pickBlock(redBlocks);

    if (currentBlue != null) {
      String bluePosition = currentBlue.getX() + " " + currentBlue.getY();
      SmartDashboard.putString("blue block:", bluePosition);
    }
    if (currentRed != null) {
      String redPosition = currentRed.getX() + " " + currentRed.getY();
      SmartDashboard.putString("red block:", redPosition);
    }
  }
  public Block getBlueBlock(){
    return currentBlue;
  }
  
  public Block getRedBlock(){
    return currentRed;
  }

  public Block pickBlock(ArrayList<Block> blocks){
    if(blocks.size() == 0){
      return null;
    }
    ArrayList<Block> squareBlocks = new ArrayList<Block>();
    
    for(Block block : blocks){
      double aspect = (double)block.getWidth() / block.getHeight();
      //System.out.println(aspect);
      if(aspect> .6 && aspect < 1){
        squareBlocks.add(block);
      }
    }
    if(squareBlocks.size() == 0) return null;
    Block current = squareBlocks.get(0);
    for(Block block : squareBlocks){
      if((block.getWidth() * block.getHeight()) > (current.getHeight() * current.getWidth())){
        current = block;
      }
    }

    return current;

  }

}
