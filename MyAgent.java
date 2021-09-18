package wumpusworld;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.chart.PieChart;

/**
 * Contains starting code for creating your own Wumpus World agent.
 * Currently the agent only make a random decision each turn.
 * 
 * @author Fabia Afzal
 */
public class MyAgent implements Agent
{
    private World w;
    int rnd;

    private String state;
    
    /**
     * Creates a new instance of your solver agent.
     * 
     * @param world Current world state 
     */
    public MyAgent(World world)
    {
        w = world;// World state is assigned to w 
        
    }
    
   
    public void doAction()
    {
         //Location of the player
        int cX = w.getPlayerX();
        int cY = w.getPlayerY();
        
        
        
        
        //Basic action:
        //Grab Gold if we can.
        if (w.hasGlitter(cX, cY))
        {
            state="Gold";
            w.doAction(World.A_GRAB);
            return;
        }
        
        //Basic action:
        //We are in a pit. Climb up.
        if (w.isInPit())
        {
            state="Pit";
            w.doAction(World.A_CLIMB);
            return;
        }
        
        //Test the environment
        if (w.hasBreeze(cX, cY))
        {
            System.out.println("I am in a Breeze");
        }
        if (w.hasStench(cX, cY))
        {
            System.out.println("I am in a Stench");
        }
        if (w.hasPit(cX, cY))
        {
            System.out.println("I am in a Pit");
        }
        if (w.getDirection() == World.DIR_RIGHT)
        {
            System.out.println("I am facing Right");
        }
        if (w.getDirection() == World.DIR_LEFT)
        {
            System.out.println("I am facing Left");
        }
        if (w.getDirection() == World.DIR_UP)
        {
            System.out.println("I am facing Up");
        }
        if (w.getDirection() == World.DIR_DOWN)
        {
            System.out.println("I am facing Down");
        }
        
       
        if (w.hasStench(cX, cY)) {
            state="Stench";
        }
        else if(w.getDirection() == 0) {
            state = "DirectionUp";
        }
        else if(w.getDirection() == 1) {
            state= "DirectionRight";
        }
        else if(w.getDirection() == 2) {
            state="DirectionDown";
        }
        else if(w.getDirection() == 3) {
            state="DirectionLeft";
        }
        else if(w.hasBreeze(cX, cY)) {
            state="Breeze";
        }
        
        //know check in which state we are standing and do action accordingly
        
        if(state=="Stench")
        {
            inStench(cX,cY);
        }
        else if(state=="Breeze")
        {
            inBreeze(cX,cY);
        }
        else if(state=="DirectionUp")
        {
             dirUp(cX,cY);
        }
        else if(state=="DirectionDown")
        {
             dirDown(cX,cY);
        }
        else if(state=="DirectionRight")
        {
             dirRight(cX,cY);
        }
        else if(state=="DirectionLeft")
        {
             dirLeft(cX,cY);
        }
        else
        {
            System.out.println("error");
        }
    }
     
    public void inStench(int cX, int cY) {
        
        
        if(w.hasBreeze(cX, cY) && w.hasStench(cX, cY) && !w.isValidPosition(cX+1, cY))
         {
             
            w.doAction(World.A_TURN_LEFT);
           
             w.doAction(World.A_MOVE);
         }
         if(w.hasBreeze(cX, cY) && w.hasStench(cX, cY) && w.hasArrow() && w.getPlayerX()==1 && w.getPlayerY()==1)
         {
             w.doAction(World.A_TURN_LEFT);
             w.doAction(World.A_SHOOT);
             w.doAction(World.A_MOVE);
         }
          
          if (w.hasStench(cX, cY))
        {
            if(w.hasStench(cX-1, cY-1) && !w.hasArrow()) {
                if(w.getDirection() == 0)
                    w.doAction(World.A_MOVE);
                else if(w.getDirection() == 3)
                    w.doAction(World.A_TURN_RIGHT);
                else if(w.getDirection() == 2)
                    w.doAction(World.A_TURN_RIGHT);
                else
                    w.doAction(World.A_TURN_LEFT);
                return;
            }
            if (w.hasStench(cX, cY+2))  // Wumpus at cX, cY+1
            {
                if (w.getDirection()==0 && w.hasArrow())
                    w.doAction(World.A_SHOOT);
                else if (w.getDirection()==1 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==2 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==0 && !w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (!w.hasArrow() && w.isValidPosition(cX-1, cY))
                    w.doAction(World.A_MOVE);
                else if (!w.hasArrow())
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                }
                else
                    w.doAction(World.A_TURN_RIGHT);
            }
            
            else if (w.hasStench(cX+2, cY))  // Wumpus at cX+1, cY
            {
                if (w.getDirection()==0 && w.hasArrow())
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==1 && w.hasArrow())
                    w.doAction(World.A_SHOOT);
                else if (w.getDirection()==2 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==1 && !w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (!w.hasArrow() && w.isValidPosition(cX, cY+1))
                    w.doAction(World.A_MOVE);
                else if (!w.hasArrow())
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                }
                else
                    w.doAction(World.A_TURN_LEFT);
            }
            
            else if (w.hasStench(cX-2, cY))  // Wumpus at cX-1, cY
            {
                if (w.getDirection()==0 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==1 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==2 && w.hasArrow())
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==3 && !w.hasArrow())
                    w.doAction(World.A_TURN_RIGHT);
                else if (!w.hasArrow() && w.isValidPosition(cX, cY+1))
                    w.doAction(World.A_MOVE);
                else if (!w.hasArrow())
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                }
                else
                    w.doAction(World.A_SHOOT);
            }
            
            else if (w.hasStench(cX, cY-2))  // Wumpus at cX, cY-1
            {
                if (w.getDirection()==0 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==1 && w.hasArrow())
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==2 && w.hasArrow())
                    w.doAction(World.A_SHOOT);
                else if (w.getDirection()==2 && !w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (!w.hasArrow() && w.isValidPosition(cX, cY+1))
                    w.doAction(World.A_MOVE);
                else if (!w.hasArrow())
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                }
                else
                    w.doAction(World.A_TURN_LEFT);
            }
            
            else if (w.hasStench(cX-1, cY+1))
            {
                if (w.isVisited(cX-1, cY))   // Wumpus at cX, cY+1
                {
                if (w.getDirection()==0)
                    w.doAction(World.A_SHOOT);
                else if (w.getDirection()==1)
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==2)
                    w.doAction(World.A_TURN_LEFT);
                else
                    w.doAction(World.A_TURN_RIGHT);
                }
                else if(w.isVisited(cX, cY+1))  // Wumpus at cX-1, cY
                {
                if (w.getDirection()==0 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==1 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==2 && w.hasArrow())
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==3 && !w.hasArrow())
                    w.doAction(World.A_TURN_RIGHT);
                else if (!w.hasArrow() && w.isValidPosition(cX, cY+1))
                    w.doAction(World.A_MOVE);
                else if (!w.hasArrow())
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                }
                else
                    w.doAction(World.A_SHOOT);
                }
            }
            
            else if (w.hasStench(cX+1, cY+1))
            {
                if (w.isVisited(cX, cY+1))  // Wumpus at cX+1, cY
                {
                if (w.getDirection()==0)
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==1)
                   w.doAction(World.A_SHOOT);
                else if (w.getDirection()==2)
                    w.doAction(World.A_TURN_LEFT);
                else
                    w.doAction(World.A_TURN_LEFT);
                }
                
                else if (w.isVisited(cX+1, cY))  // Wumpus at cX, cY+1
                {
                if (w.getDirection()==0)
                    w.doAction(World.A_SHOOT);
                else if (w.getDirection()==1)
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==2)
                    w.doAction(World.A_TURN_LEFT);
                else
                    w.doAction(World.A_TURN_RIGHT);
                }
            }
            
            else if (w.hasStench(cX+1, cY-1))
            {
                if (w.isVisited(cX+1, cY))  // Wumpus at cX, cY-1
                {
                if (w.getDirection()==0)
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==1)
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==2)
                   w.doAction(World.A_SHOOT);
                else
                    w.doAction(World.A_TURN_LEFT);
                }
                
                else if (w.isVisited(cX, cY-1))  // Wumpus at cX+1, cY
                {
                if (w.getDirection()==0)
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==1)
                    w.doAction(World.A_SHOOT);
                else if (w.getDirection()==2)
                    w.doAction(World.A_TURN_LEFT);
                else
                    w.doAction(World.A_TURN_LEFT);
                }
            }
            
            else if (w.hasStench(cX-1, cY-1))
            {
                if (w.isVisited(cX, cY-1))  // Wumpus at cX-1, cY
                {
                if (w.getDirection()==0)
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==1)
                   w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==2)
                    w.doAction(World.A_TURN_RIGHT);
                else
                   w.doAction(World.A_SHOOT);
                }
                
                else if (w.isVisited(cX-1, cY))  // Wumpus at cX, cY-1
                {
                if (w.getDirection()==0 && w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (w.getDirection()==1 && w.hasArrow())
                    w.doAction(World.A_TURN_RIGHT);
                else if (w.getDirection()==2 && w.hasArrow())
                    w.doAction(World.A_SHOOT);
                else if (w.getDirection()==2 && !w.hasArrow())
                    w.doAction(World.A_TURN_LEFT);
                else if (!w.hasArrow() && w.isValidPosition(cX, cY+1))
                    w.doAction(World.A_MOVE);
                else if (!w.hasArrow())
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                }
                else
                    w.doAction(World.A_TURN_LEFT);
                }
            } 
            
            else if (!w.isValidPosition(cX-1, cY) && !w.isValidPosition(cX, cY-1) && w.hasArrow())
            {
                if(w.getDirection()==1)
                    w.doAction(World.A_SHOOT);
                else if(w.getDirection()==3)
                    w.doAction(World.A_TURN_LEFT);
                else if(w.getDirection()==2)
                    w.doAction(World.A_TURN_LEFT);
                else
                    w.doAction(World.A_TURN_RIGHT);
            }
            
            else if (w.isUnknown(cX, cY+1) && w.isUnknown(cX+1, cY) && !w.hasArrow())
            {
                w.doAction(World.A_MOVE);
            }
            
            else if (w.getDirection()==3)
            {
                if (!w.isValidPosition(cX-1, cY))
                {
                    if((!w.isValidPosition(cX, cY-1) || w.isVisited(cX, cY-1)) && (w.isVisited(cX+1, cY) || (w.isUnknown(cX+1, cY) && w.isVisited(cX+1, cY-1)) ))
                    {
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_SHOOT);
                    }
                    else
                    {
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_SHOOT);
                    }
                }
                else if(!w.isValidPosition(cX+1, cY) && w.isVisited(cX-1, cY-1))
                    w.doAction(World.A_MOVE);
                else 
                {
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_TURN_RIGHT);
                    w.doAction(World.A_MOVE);
                }
            }
            
            else if (w.getDirection()==1)
            {
                if (!w.isValidPosition(cX+1, cY))
                {
                    if((!w.isValidPosition(cX, cY-1) || w.isVisited(cX, cY-1)) && (w.isVisited(cX-1, cY) || (w.isUnknown(cX-1, cY) && w.isVisited(cX-1, cY-1))))
                    {
                        w.doAction(World.A_TURN_LEFT);
                        w.doAction(World.A_SHOOT);
                    }
                    else
                    {
                        w.doAction(World.A_TURN_RIGHT);
                        w.doAction(World.A_SHOOT);
                    }
                }
                else if(!w.isValidPosition(cX-1, cY) && w.isVisited(cX+1, cY+1))
                    w.doAction(World.A_MOVE);
                else 
                {
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_TURN_LEFT);
                    w.doAction(World.A_MOVE);
                }
            }
            if(w.getDirection() == 0 && w.isInPit()){
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
            }
            
            return;
        }
     
     }
     public void inBreeze(int cX, int cY) {} 
     public void dirUp(int cX, int cY) {
         
         if(w.hasBreeze(cX, cY))
         {
             ///
             if(w.isValidPosition(cX+1, cY) && w.isVisited(cX, cY-1) && !w.hasBreeze(cX-1, cY-1))
             {
                 w.doAction(World.A_TURN_LEFT);
                 w.doAction(World.A_TURN_LEFT);
                  w.doAction(World.A_MOVE);
                  
             }
             
             
         }
         if (w.getDirection()==0)
      {
          if(w.hasBreeze(cX+1, cY) && w.hasBreeze(cX-1, cY))
          {
              w.doAction(World.A_MOVE);
          }
          if(!w.isValidPosition(cX-1, cY))
          {
            if(w.isVisited(cX+1, cY))
            {
                w.doAction(World.A_MOVE);
                w.doAction(World.A_TURN_RIGHT);
            }
            else
            {
                w.doAction(World.A_TURN_RIGHT);
                w.doAction(World.A_MOVE);
            }
          }
          else if(!w.isValidPosition(cX+1, cY))
          {
            if(w.isVisited(cX-1, cY))
            {
                w.doAction(World.A_MOVE);
                w.doAction(World.A_TURN_LEFT);
            }
            else
            {
                w.doAction(World.A_TURN_LEFT);
                w.doAction(World.A_MOVE);
            }
          }
          else
          {
              w.doAction(World.A_TURN_RIGHT);
          }
        return;  
      }
     }
     
     public void dirDown(int cX, int cY) {
     
         if (w.getDirection()==2)
      {
          if(w.isVisited(cX+1, cY) && w.hasBreeze(cX+1, cY))
          {
                  w.doAction(World.A_TURN_RIGHT);
                  w.doAction(World.A_TURN_RIGHT);
                  w.doAction(World.A_MOVE);
          }
          if(w.isValidPosition(cX, cY-1) && w.isUnknown(cX, cY-1))
          {
                  w.doAction(World.A_MOVE);
                  w.doAction(World.A_TURN_LEFT);
          }
          else if(w.isValidPosition(cX+1, cY) && w.isUnknown(cX+1, cY))
          {
              w.doAction(World.A_TURN_LEFT);
              w.doAction(World.A_MOVE);
          }
          else if(w.isValidPosition(cX-1, cY) && w.isUnknown(cX-1, cY))
          {
              w.doAction(World.A_TURN_RIGHT);
              w.doAction(World.A_MOVE);
          }
          else if(w.isValidPosition(cX+1, cY))
          {
              w.doAction(World.A_TURN_LEFT);
              w.doAction(World.A_MOVE);
          }
          else if(w.isValidPosition(cX-1, cY))
          {
              w.doAction(World.A_TURN_RIGHT);
              w.doAction(World.A_MOVE);
          }
          else
          {
              w.doAction(World.A_TURN_LEFT);
          }
      }
     }
     
     public void dirRight(int cX, int cY) {
         
         
         if(w.hasBreeze(cX, cY))
         {
            
             if(w.hasPit(cX, cY) && w.isValidPosition(cX+1, cY) && w.isVisited(cX-1, cY) && w.hasBreeze(cX-1, cY))
             {
                 w.doAction(World.A_CLIMB);
                 //w.doAction(World.A_TURN_RIGHT);
                
                 w.doAction(World.A_MOVE);
                  w.doAction(World.A_TURN_LEFT);
                  w.doAction(World.A_TURN_LEFT);
                 
             }
             if(!w.isValidPosition(cX+1, cY))
             {
                 w.doAction(World.A_TURN_LEFT);
                 w.doAction(World.A_TURN_LEFT);
                 w.doAction(World.A_MOVE);
             }
             
            if(w.isValidPosition(cX+1, cY) && w.isVisited(cX-1, cY) && w.hasPit(cX-1, cY))
             {
                 
                 w.doAction(World.A_MOVE);
                 w.doAction(World.A_TURN_LEFT);
             }
             //two
             if(w.isValidPosition(cX+1, cY) && w.isVisited(cX-1, cY) && !w.hasBreeze(cX-1, cY+1))
             {
                 w.doAction(World.A_TURN_LEFT);
                 w.doAction(World.A_TURN_LEFT);
                 w.doAction(World.A_MOVE);
             }
             //6
             
             
         }
         
         if (w.getDirection() == 1)
            {
          if(w.isValidPosition(cX+1, cY))
          {
              if(w.isUnknown(cX+1, cY))
                  w.doAction(World.A_MOVE);
              else if(w.hasStench(cX-1, cY) || w.hasBreeze(cX-1, cY))
                  w.doAction(World.A_MOVE);
              else if(w.isVisited(cX+1, cY))
              {
                  w.doAction(World.A_TURN_LEFT);
                  w.doAction(World.A_MOVE);
              }
          }
         else if(!(w.isValidPosition(cX+1, cY) && w.isValidPosition(cX, cY+1)) && w.isVisited(cX-1, cY) && w.isUnknown(cX, cY-1))
          {
              w.doAction(World.A_TURN_RIGHT);
              w.doAction(World.A_MOVE);
          }
          else if(!(w.isValidPosition(cX+1, cY) && w.isValidPosition(cX, cY+1)) && w.isVisited(cX, cY-1) && w.isUnknown(cX-1, cY))
          {
              w.doAction(World.A_TURN_RIGHT);
              w.doAction(World.A_TURN_RIGHT);
              w.doAction(World.A_MOVE);
          }
          else if(!w.isValidPosition(cX-1, cY) && w.isVisited(cX+1, cY) && w.isVisited(cX, cY-1) && !w.isValidPosition(cX, cY+1))
              w.doAction(World.A_MOVE);
          else
          {
              w.doAction(World.A_TURN_LEFT);
             w.doAction(World.A_MOVE);
              w.doAction(World.A_TURN_LEFT);
          }
          
        return;
      }
     }
     
     public void dirLeft(int cX, int cY) {
     
         
         if (w.getDirection() == 3)
      {
          
          if(w.isValidPosition(cX-1, cY))
          {
              if(w.isUnknown(cX-1, cY))
                  w.doAction(World.A_MOVE);
              //zelse if(w.hasStench(cX+1, cY) || w.hasBreeze(cX+1, cY))
                 // w.doAction(World.A_MOVE);
              else if(w.isVisited(cX-1, cY))
              { 
                  w.doAction(World.A_TURN_RIGHT);
                  w.doAction(World.A_MOVE);
              }
          }
          else if(!(w.isValidPosition(cX-1, cY) && w.isValidPosition(cX, cY+1)) && w.isVisited(cX+1, cY) && w.isUnknown(cX, cY-1))
          {
              w.doAction(World.A_TURN_LEFT);
              w.doAction(World.A_MOVE);
          }
          else if(!(w.isValidPosition(cX-1, cY) && w.isValidPosition(cX, cY+1)) && w.isVisited(cX, cY-1) && w.isUnknown(cX+1, cY))
          {
              w.doAction(World.A_TURN_RIGHT);
              w.doAction(World.A_TURN_RIGHT);
              w.doAction(World.A_MOVE);
          }
          else if(w.isVisited(cX-1, cY) && !w.isValidPosition(cX+1, cY) && w.isVisited(cX, cY-1) && !w.isValidPosition(cX, cY+1))
              w.doAction(World.A_MOVE);
          else
          {
              w.doAction(World.A_TURN_RIGHT);
              w.doAction(World.A_MOVE);
              w.doAction(World.A_TURN_RIGHT);
          }
          
        return;
      }
     }
    
}

