package model;

public class Reduction {
    private int reductionId;
    private int seanceId;
    private int reduction;

    public Reduction(int reductionId, int seanceId, int reduction) {
        this.reductionId = reductionId;
        this.seanceId = seanceId;
        this.reduction = reduction;
    }

    public int getReduction() {
        return reduction;
    }
}
