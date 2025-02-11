package com.example.nct.nctmembers.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um membro do NCT.
 * Um membro pode estar em várias unidades ao mesmo tempo.
 */
public class Members {
    private String name; // Nome do membro
    private String position; // Posição (ex: Vocal, Rapper, Dancer)
    private List<String> units; // Lista de unidades em que o membro participa

    /**
     * Construtor para criar um novo membro com nome, posição e unidades.
     *
     * @param name Nome do membro
     * @param position Posição do membro
     * @param units Lista de unidades que o membro pertence
     */
    public Members(String name, String position, List<String> units) {
        this.name = name;
        this.position = position;
        this.units = new ArrayList<>(units); // Garante que a lista não seja modificada externamente
    }

    // Métodos Getters (para acessar os atributos)
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
     * Adiciona novas unidades ao membro, evitando duplicatas.
     *
     * @param newUnits Lista de novas unidades a serem adicionadas
     */
    public void addUnits(List<String> newUnits) {
        for (String unit : newUnits) {
            if (!units.contains(unit)) { // Verifica se a unidade já foi adicionada
                units.add(unit);
            }
        }
    }

    /**
     * Remove uma unidade do membro.
     *
     * @param unit Nome da unidade a ser removida
     */
    public void removeUnit(String unit) {
        units.remove(unit);
    }

    /**
     * Representação em String do membro, exibindo seu nome, posição e unidades.
     *
     * @return String formatada com as informações do membro
     */
    @Override
    public String toString() {
        return name + " (" + position + ") - Units: " + units;
    }
}
