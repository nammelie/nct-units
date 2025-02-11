package com.example.nct.nctmembers.app;

import com.example.nct.nctmembers.model.Members;

import java.util.*;

/**
 * Classe principal para registrar, listar e remover membros do NCT.
 * Usa um menu interativo para manipular os dados.
 */
public class MembersRegApp {
    private static List<Members> members = new ArrayList<>(); // Lista de todos os membros cadastrados
    private static Scanner scanner = new Scanner(System.in); // Scanner para entrada do usuário

    public static void main(String[] args) {
        while (true) { // Loop infinito para manter o programa rodando até o usuário escolher sair
            System.out.println("\n=== NCT Member Registry ===");
            System.out.println("1. Add member");
            System.out.println("2. Remove member");
            System.out.println("3. List members by unit");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha deixada pelo nextInt()

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
                    System.exit(0); // Encerra o programa
                default:
                    System.out.println("That's a nono! Try again.");
            }
        }
    }

    /**
     * Adiciona um novo membro ao sistema.
     * Se o membro já existir, adiciona apenas novas unidades.
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
            if (unit.equalsIgnoreCase("done")) break; // Sai do loop quando o usuário digita 'done'
            if (!units.contains(unit)) { // Evita adicionar a mesma unidade mais de uma vez
                units.add(unit);
            }
        }

        // Verifica se o membro já existe para adicionar novas unidades sem duplicar
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
     * Lista todas as unidades disponíveis e permite visualizar os membros de uma unidade específica.
     */
    private static void listMembersByUnit() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }

        System.out.println("\n=== List of NCT Units ===");
        Set<String> allUnits = new HashSet<>();
        for (Members member : members) {
            allUnits.addAll(member.getUnits()); // Coleta todas as unidades únicas
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
     * Remove um membro do sistema, podendo remover de uma unidade específica ou de todas.
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
            if (member.getUnits().isEmpty()) { // Remove completamente se não estiver mais em nenhuma unidade
                members.remove(member);
            }
            System.out.println(name + " was removed from " + unit + "!");
        } else {
            System.out.println("Member is not in this unit.");
        }
    }

    /**
     * Procura um membro pelo nome na lista.
     *
     * @param name Nome do membro a ser procurado
     * @return O objeto `Members` encontrado, ou `null` se não existir
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
