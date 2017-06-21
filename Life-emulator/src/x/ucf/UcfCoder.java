
package x.ucf;


public class UcfCoder {
    
    //UCF = XXXX XXXX XXXX XXXX;
    public static final int LANDSCAPE_MASK = 0xF000;
    //0x0
    public static final int LANDSCAPE_WATER_LOW = 0x1;
    public static final int LANDSCAPE_WATER_HIGH = 0x2;
    public static final int LANDSCAPE_GROUND_LOW = 0x3;
    public static final int LANDSCAPE_GROUND_HIGH = 0x4;
    public static final int LANDSCAPE_SAND_LOW = 0x5;
    public static final int LANDSCAPE_SAND_HIGH = 0x6;
    public static final int LANDSCAPE_SNOW_LOW = 0x7;
    public static final int LANDSCAPE_SNOW_HIGH = 0x8; 
    public static final int LANDSCAPE_TREE_LOW = 0x9;
    public static final int LANDSCAPE_TREE_HIGH = 0xA;
    public static final int LANDSCAPE_MOUNTAIN_LOW = 0xB;
    public static final int LANDSCAPE_MOUNTAIN_HIGH = 0xC;
    public static final int LANDSCAPE_SWAMP_LOW = 0xD;
    public static final int LANDSCAPE_CAVE = 0xF;
    
    public static final int CREATURE_MASK = 0x0F00;
    //0x0
    public static final int CREATURE_HUMAN = 0x1;
    public static final int CREATURE_CHIKEN = 0x2;
    public static final int CREATURE_WOLF = 0x3;
    public static final int CREATURE_RABBIT = 0x4;
    public static final int CREATURE_FISH = 0x5;
    public static final int CREATURE_SHARK = 0x6;
    public static final int CREATURE_UNICORN = 0x7;
    public static final int CREATURE_SCORPION = 0x8;
    public static final int CREATURE_BEAR = 0x9;
    public static final int CREATURE_HORSE = 0xA;
    public static final int CREATURE_KRAKEN = 0xB;
    //0x...
    
    public static final int decodingLandscape(int ucf){
        return (ucf & LANDSCAPE_MASK) >> 12; 
    }
    
    public static final int encodeLandscape(int ucf, int uf){
        return (ucf & ~LANDSCAPE_MASK) | (uf << 12); 
    }
    
    public static final int decodingCreature(int ucf){
        return (ucf & LANDSCAPE_MASK) >> 8; 
    }
    
    public static final int encodeCreature(int ucf, int uf){
        return (ucf & ~CREATURE_MASK) | (uf << 8); 
    }
}
