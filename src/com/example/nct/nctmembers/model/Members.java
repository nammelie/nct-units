package com.example.nct.nctmembers.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a member of NCT.
 * A member can be part of multiple units at the same time.
 */
public class Members {
    private String name; // Member's name
    private String position; // Position (Vocal, Rapper, Dancer)
    private List<String> units; // List of units the member is a part of

    /**
     * Constructor to create a new member with name, position, and units.
     *
     * @param name Member's name
     * @param position Member's position
     * @param units List of units the member belongs to
     */
    public Members(String name, String position, List<String> units) {
        this.name = name;
        this.position = position;
        this.units = new ArrayList<>(units); // Ensures the list is not modified externally
    }

    // Getters (to access the attributes)
    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public List<String> getUnits() {
        return units;
    }

    /**
     * Adds new units to the member, avoiding duplicates.
     *
     * @param newUnits List of new units to be added
     */
    public void addUnits(List<String> newUnits) {
        for (String unit : newUnits) {
            if (!units.contains(unit)) { // Checks if the unit has already been added
                units.add(unit);
            }
        }
    }

    /**
     * Removes a unit from the member.
     *
     * @param unit Name of the unit to be removed
     */
    public void removeUnit(String unit) {
        units.remove(unit);
    }

    /**
     * String representation of the member, displaying their name, position, and units.
     *
     * @return Formatted string with the member's information
     */
    @Override
    public String toString() {
        return name + " (" + position + ") - Units: " + units;
    }
}
