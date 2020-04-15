package gtfs_application.file;

/**
 * @author grant
 * @version 1.0
 * @created 15-Apr-2020 1:20:18 PM
 */
public abstract class GTFSElement {

	private Color color;
	private GTFSID id;
	private String name;
	public GTFSID m_GTFSID;

	public GTFSElement(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param id
	 */
	public GTFSElement GTFSElement(GTFSID id){
		return null;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public void GTFSElement(GTFSID id, String name){

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param color
	 */
	public void GTFSElement(GTFSID id, String name, Color color){

	}

	/**
	 * 
	 * @param id
	 * @param color
	 */
	public void GTFSElement(GTFSID id, Color color){

	}

	public Color getColor(){
		return null;
	}

	public GTFSID getID(){
		return null;
	}

	public String getName(){
		return "";
	}

	/**
	 * 
	 * @param color
	 */
	public void setColor(Color color){

	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name){

	}

}