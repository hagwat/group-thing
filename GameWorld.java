package swen222_group_project.game;

import java.io.IOException;
import java.util.ArrayList;

import swen222_group_project.block.AirBlock;
import swen222_group_project.block.Block;
import swen222_group_project.control.Player;


public class GameWorld {

	private int ticktock = 1;
	private int playercount = 0;

	public synchronized void clockTick() {

		if (ticktock == 1) {
			ticktock = 2;
			System.out.println("tick");
		} else {
			ticktock = 1;
			System.out.println(" tock");
		}
	}

	public synchronized int registerPlayer() {
		return ++playercount;
	}


	private int state;

	public static final int WAITING = 0;
	public static final int READY = 1;
	public static final int PLAYING = 2;
	public static final int GAMEOVER = 3;
	public static final int GAMEWON = 4;

	public void setState(int state) {
		this.state = state;
	}

	public int state() {
		return state;
	}

	public synchronized void fromByteArray(byte[] bytes) throws IOException {
		//TODO get game state back from the byte array
	}
	public synchronized byte[] toByteArray() throws IOException {
		//TODO return game state as a byte array to be sent through server to clients
		return null;
	}

	private static final int MAP_SIZE = 20;
	private Block[][][] world;
	private ArrayList<Player> players;

	public GameWorld(){
		players = new ArrayList<Player>();
	}

	public int getMapSize(){
		return MAP_SIZE;
	}


	/**
	 * Gets a 3D array of block objects that constitutes what is
	 * visible to the player. This need the players location within
	 * game world and what direction they are facing. Depending
	 * on how large the view of the player is determines the size of
	 * the array. In the situation which this value, coupled with the
	 * players location information results in out of bounds coordinates
	 * the array will be filled with blank blocks.
	 *
	 * @param player
	 * @return Block[][][]
	 */
	public Block[][][] getView(Player player){
		int depth = MAP_SIZE;
		int viewX = (int) player.getX()/50;
		int viewY = (int) player.getY()/50;
		int viewZ = (int) player.getZ()/50;
		int viewSize = player.getViewSize();
		int viewDir = player.getViewDir();

		if(viewDir==0)depth = MAP_SIZE-viewZ;
		else if(viewDir==1) depth = MAP_SIZE-viewX;
		else if(viewDir==2) depth = viewZ+1;
		else if(viewDir==3) depth = viewX+1;
		Block[][][] c = new Block[2*viewSize+1][2*viewSize+1][depth];
		int i=0;
		int j=0;
		int g=0;

		for(int y=viewY-viewSize;y<viewY+viewSize+1;y++){
			i=0;
			if(viewDir==0){
				for(int x=viewX-viewSize;x<viewX+viewSize+1;x++){
					g=0;
					for(int z=viewZ;z<MAP_SIZE;z++){
						if(x>=MAP_SIZE || x<0 || y>=MAP_SIZE || y<0 || z>=MAP_SIZE || z<0)
							c[i][j][g] = new AirBlock(x*50,y*50,z*50,50);
						else {
							c[i][j][g] = world[x][y][z];
						}
						g++;
					}
					i++;
				}
			} else if(viewDir== 1){
				for(int z=viewZ-viewSize;z<viewZ+viewSize+1;z++){
					g=0;
					for(int x=viewX;x<MAP_SIZE;x++){
						if(x>=MAP_SIZE || x<0 || y>=MAP_SIZE || y<0 || z>=MAP_SIZE || z<0)
							c[i][j][g] = new AirBlock(x*50,y*50,z*50,50);
						else {
							c[i][j][g] = world[x][y][z];
						}
						g++;
					}
					i++;
				}
			} else if(viewDir== 2){
				for(int x=viewX+viewSize;x>=viewX-viewSize;x--){
					g=0;
					for(int z=viewZ;z>=0;z--){
						if(x>=MAP_SIZE || x<0 || y>=MAP_SIZE || y<0 || z>=MAP_SIZE || z<0)
							c[i][j][g] = new AirBlock(x*50,y*50,z*50,50);
						else {
							c[i][j][g] = world[x][y][z];
						}
						g++;
					}
					i++;
				}
			} else if(viewDir== 3){
				for(int z=viewZ+viewSize;z>=viewZ-viewSize;z--){
					g=0;
					for(int x=viewX;x>=0;x--){
						if(x>=MAP_SIZE || x<0 || y>=MAP_SIZE || y<0 || z>=MAP_SIZE || z<0)
							c[i][j][g] = new AirBlock(x*50,y*50,z*50,50);
						else {
							c[i][j][g] = world[x][y][z];
						}
						g++;
					}
					i++;
				}
			}
			j++;
		}
		return c;
	}


	/**
	 * Takes a player objects and three integers, these integers represent
	 * how many units the player object wants to move through the game world
	 * on the corresponding axis. Returns a an array of doubles. If no collision
	 * is detected the values of x, y and z are returned in the array indicating
	 * the distances stipulated in the variables are valid, the index is set to 1
	 * indicating a valid move. However if a collision is detected the return
	 * value contains the maximum distances the player can until they encounter
	 * the obstruction, the last index is set to 0 indicating a collision.
	 *
	 * @param player
	 * @param x
	 * @param y
	 * @param z
	 * @return boolean
	 */
	public double[] validMove(Player player, double x, double y, double z){
		double[] direction = new double[4];
		int pX = (int) ((player.getX()+x)/50);
		int pY = (int) ((player.getY()+y)/50);
		int pZ = (int) ((player.getZ()+z)/50);

		Block target = world[pX][pY][pZ];

		if(target.collideable()){
			direction[0] = pX*50-player.getX();
			direction[1] = pY*50-player.getY();
			direction[2] = pZ*50-player.getZ();
			direction[3] = 0;
			return direction;
		}
		else return new double[]{x,y,z,1};
	}


	public Player getPlayer(int id){
		for(Player p:players){
			if(p.getId()==id)return p;
		}
		return null;
	}

	public boolean addPlayer(Player player){
		return players.add(player);
	}
}
