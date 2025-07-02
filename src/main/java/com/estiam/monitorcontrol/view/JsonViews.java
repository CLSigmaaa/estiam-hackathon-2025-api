package com.estiam.monitorcontrol.view;

public class JsonViews {

    // Les vues basiques
    public interface SalleBasic {}
    public interface ClasseBasic {}

    // La vue basique d'Affectation inclut SalleBasic et ClasseBasic
    public interface AffectationBasic extends SalleBasic, ClasseBasic {}

    // Les détails héritent de la vue de base
    public interface SalleDetail extends SalleBasic {}
    public interface ClasseDetail extends ClasseBasic {}
    public interface AffectationDetail extends AffectationBasic {}
}
