package model;

/**
 * Classe qui représente une réduction pour une séance
 */
public class Reduction {
    private int reductionId;
    private int seanceId;
    private int reduction;

    /**
     * Constructeur de la classe Reduction
     *
     * @param reductionId L'identifiant de la réduction
     * @param seanceId    L'identifiant de la séance associée à la réduction
     * @param reduction   Le montant de la réduction
     */
    public Reduction(int reductionId, int seanceId, int reduction) {
        this.reductionId = reductionId;
        this.seanceId = seanceId;
        this.reduction = reduction;
    }

    /**
     * Méthode qui obtient le montant de la réduction
     *
     * @return Le montant de la réduction
     */
    public int getReduction() {
        return reduction;
    }
}