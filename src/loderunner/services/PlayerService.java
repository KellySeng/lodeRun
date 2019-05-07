package loderunner.services;

public interface PlayerService extends CharacterService{
	
	/*
	 * Observators
	 */
	public EngineService getEngine();
	public int getScore();
	public void increScore();
	public int getVie();
	public void decreVie();
	public void setPos(int x,int y) ;
	
	//Constructors
	
	
	/**
	 * pre : e!=null
	 * post : getEngine() == e
	 * @param e
	 */
	public void init(EnvironmentService env, int x, int y , EngineService e);
	
	
	
	//Operators 
	
	/**
	 * Le joueur tombe (comme un garde) quand il ne se trouve pas dans une échelle ou un rail et que la case en
	 * dessous de lui est libre et ne contient pas de personnage.
	 * 
	 * post : (\not getEnvi().getCellNature(getWdt(),getHgt()) \in {LDR,HDR} ||
	 * 		  \not getEnvi().getCellNature(getWdt(),getHgt()-1) \in {MTL,PLT,LAD}) &&
	 * 		  \not c:Character \not \exists \in getEnvi().getCellContent(getWdt(),getHgt()-1)
	 * 		  -> getHgt() == getHgt()@pre -1 
	 * 
	 * Sinon le joueur essaye de suivre la commande donnée par Engine::NextCommand, c’est à dire de se
	 * déplacer à gauche, à droite, en haut ou en bas, ou de creuser un trou à gauche ou à droite quand c’est
     * possible.
	 * 
	 * post : !((\not getEnvi().getCellNature(getWdt(),getHgt()) \in {LDR,HDR} ||
	 * 		  \not getEnvi().getCellNature(getWdt(),getHgt()-1) \in {MTL,PLT,LAD}) &&
	 * 		  \not c:Character \not \exists \in getEnvi().getCellContent(getWdt(),getHgt()-1))
	 * 		  -> 
	 * 		  ( getEngine().getNextCommand() == Right ->
	 * 			||
	 * 			getEngine().getNextCommand() == Left ->
	 * 			||
	 * 			getEngine().getNextCommand() == Up ->
	 * 			||
	 * 			getEngine().getNextCommand() == Down ->
	 * 			||
	 * 		    getEngine().getNextCommand() == DigL ->
	 *			||
	 *			getEngine().getNextCommand() == DigR ->
	 */
	
	public void step();
	

}