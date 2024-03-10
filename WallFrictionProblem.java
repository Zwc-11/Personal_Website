package CS.Version1_L;

public class WallFrictionProblem extends Problem {
    public static String FA = "Fₐ";
    public static String FK = "Fₖ";
    public static String FS = "Fₛ";
    public static String FG = "F₉";
    public static String FN = "Fₙ";

    WallFrictionProblem(String object, double mass, double csf) {
        super(Problem.WALL_FRICTION);

        String problem = "A " + mass + "kg " + object + " is being pushed against a vertical wall. "
                + "If the coefficient of static friction is " + csf
                + ", determine the minimum applied force needed to keep the " + object + " from sliding.";

        double gravitationalForce = mass * gStrength;
        double frictionalForce = gravitationalForce;
        frictionalForce = Math.round(frictionalForce * 100.0) / 100.0;

        double normalForce = frictionalForce / csf;
        double appliedForce = normalForce;
        appliedForce = Math.round(appliedForce * 100.0) / 100.0;

        String fullSolution = "m = " + mass + "kg,  = " + "μₛ = " + csf + ", Fₐ = ? <br><br>"
                + "Fₛ = F₉ = mg = (" + mass + "kg)(9.8N/kg) = " + frictionalForce + "N <br>"
                + "Fₐ = Fₙ <br>"
                + "= Fₛ / μₛ" + "<br>"
                + "= " + frictionalForce + "N / " + csf + "<br>"
                + "= " + appliedForce + "N <br><br>"
                + "Therefore, the minimum applied force needed is " + appliedForce + "N";

        setProblem(problem);
        setFbd(normalForce, appliedForce, frictionalForce, gravitationalForce, FN, FA, FS, FG);
        setAnswer(csf);
        setFullSolution(fullSolution);
    }
}
