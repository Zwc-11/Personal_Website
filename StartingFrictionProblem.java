package CS.Version1_L;

public class StartingFrictionProblem extends Problem {
    public static String FA = "Fₐ";
    public static String FK = "Fₖ";
    public static String FS = "Fₛ";
    public static String FG = "F₉";
    public static String FN = "Fₙ";

    private boolean ans;

    StartingFrictionProblem(String object, double mass, double appliedForce, double csf) {
        super(Problem.STARTING_FRICTION);

        String problem = "If a " + mass + "kg " + object + " experiences an applied force of " + appliedForce
                + "N, and the coefficient of static friction is " + csf + ", will the object start moving?";

        double gravitationalForce = mass * gStrength;
        double normalForce = gravitationalForce;
        double frictionalForce = Math.round(csf * gravitationalForce * 100.0) / 100.0;

        String res = "";
        if (appliedForce > frictionalForce) {
            res = appliedForce + "N > " + frictionalForce + "N so Fₐ > Fₛ <br>"
                    + "Since the applied force is greater than the starting friction, the object will move.";
        } else {
            res = appliedForce + "N &#60 " + frictionalForce + "N so Fₐ &#60 Fₛ <br>"
                    + "Since the applied force is less than the starting friction, the object will not move.";
        }

        String fullSolution = "m = " + mass + "kg, Fₐ = " + appliedForce + "N, μₛ = " + csf + ", Fₛ = ? <br><br>"
                + "Fₛ = μₛFₙ <br>"
                + "= μₛF₉ <br>"
                + "= μₛmg <br>"
                + "= (" + csf + ")(" + mass + "kg)(9.8N/kg) <br>"
                + "= " + frictionalForce + "N <br><br>"
                + res;

        setProblem(problem);
        setFbd(frictionalForce, appliedForce, normalForce, gravitationalForce, FS, FA, FN, FG);
        setAns(appliedForce > frictionalForce);
        setFullSolution(fullSolution);
    }

    public void setAns(boolean a) {
        ans = a;
    }

    public boolean getAns() {
        return ans;
    }

    public String getStringAns() {
        if (ans) {
            return "yes";
        }
        return "no";
    }

    public boolean checkAns(boolean userAnswer) {
        return ans == userAnswer;
    }
}