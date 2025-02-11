package com.example.nct.nctmembers.app;

import com.example.nct.nctmembers.model.Members;

import java.util.*;

/**
 * Main class for registering, listing, and removing NCT members.
 * Uses an interactive menu to manage the data.
 */
public class MembersRegApp {
    private static List<Members> members = new ArrayList<>(); // List of all registered members
    private static Scanner scanner = new Scanner(System.in); // Scanner for user input

    public static void main(String[] args) {
        while (true) { // Infinite loop to keep the program running until the user chooses to exit
            System.out.println("\n=== NCT Member Registry ===");
            System.out.println("1. Add member");
            System.out.println("2. Remove member");
            System.out.println("3. List members by unit");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()

            switch (option) {
                case 1:
                    addMember();
                    break;
                case 2:
                    removeMember();
                    break;
                case 3:
                    listMembersByUnit();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    System.exit(0); // Ends the program
                default:
                    System.out.println("That's a nono! Try again.");
            }
        }
    }

    /**
     * Adds a new member to the system.
     * If the member already exists, only new units are added.
     */
    private static void addMember() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Position: ");
        String position = scanner.nextLine();

        List<String> units = new ArrayList<>();
        while (true) {
            System.out.print("Unit (type 'done' to finish): ");
            String unit = scanner.nextLine();
            if (unit.equalsIgnoreCase("done")) break; // Exits the loop when the user types 'done'
            if (!units.contains(unit)) { // Avoids adding the same unit more than once
                units.add(unit);
            }
        }

        // Checks if the member already exists to add new units without duplicating
        Members member = findMember(name);
        if (member == null) {
            member = new Members(name, position, units);
            members.add(member);
        } else {
            member.addUnits(units);
        }

        System.out.println(name + " added to " + units + "!");
    }

    /**
     * Lists all available units and allows viewing members of a specific unit.
     */
    private static void listMembersByUnit() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }

        System.out.println("\n=== List of NCT Units ===");
        Set<String> allUnits = new HashSet<>();
        for (Members member : members) {
            allUnits.addAll(member.getUnits()); // Collects all unique units
        }
        for (String unit : allUnits) {
            System.out.println("- " + unit);
        }

        System.out.print("Select a unit to view members: ");
        String chosenUnit = scanner.nextLine();

        System.out.println("\n=== " + chosenUnit + " Members ===");
        boolean found = false;
        for (Members member : members) {
            if (member.getUnits().contains(chosenUnit)) {
                System.out.println("- " + member.getName() + " (" + member.getPosition() + ")");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No members found in this unit.");
        }
    }

    /**
     * Removes a member from the system, either from a specific unit or completely.
     */
    private static void removeMember() {
        System.out.print("Enter the name of the member to remove: ");
        String name = scanner.nextLine();

        Members member = findMember(name);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        System.out.println("Member found in units: " + member.getUnits());
        System.out.print("Remove from specific unit (type unit name) or all (type 'all'): ");
        String unit = scanner.nextLine();

        if (unit.equalsIgnoreCase("all")) {
            members.remove(member);
            System.out.println(name + " was removed from all units!");
        } else if (member.getUnits().contains(unit)) {
            member.removeUnit(unit);
            if (member.getUnits().isEmpty()) { // Completely removes if no units remain
                members.remove(member);
            }
            System.out.println(name + " was removed from " + unit + "!");
        } else {
            System.out.println("Member is not in this unit.");
        }
    }

    /**
     * Searches for a member by name in the list.
     *
     * @param name The name of the member to search for
     * @return The `Members` object found, or `null` if it doesn't exist
     */
    private static Members findMember(String name) {
        for (Members member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        return null;
    }
}
