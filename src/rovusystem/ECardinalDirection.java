// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package rovusystem;

public enum ECardinalDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

	public ECardinalDirection next() {
	    return values()[ordinal() + 1];
	}
};


