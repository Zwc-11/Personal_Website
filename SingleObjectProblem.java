package CS.Version1_L;

public class SingleObjectProblem extends Problem {
    SingleObjectProblem(String object, double mass, double acceleration, double ckf) {
        super(Problem.SINGLE_OBJECT);

        String problem = "A " + mass + "kg " + object + " is pushed along the ground at " + acceleration
                + "m/s². If the coefficient of kinetic friction is " + ckf
                + ", what is the applied force that the object experiences?";

        double netForce = Math.round(mass * acceleration * 100.0) / 100.0;
        double gravitationalForce = mass * gStrength;
        double normalForce = gravitationalForce;
        double frictionalForce = Math.round(ckf * normalForce * 100.0) / 100.0;
        double appliedForce = Math.round(netForce + frictionalForce * 100.0) / 100.0;

        String fullSolution = "m = " + mass + "kg, a = " + acceleration + "m/s², μₖ = " + ckf + ", Fₐ = ? <br><br>"
                + "Fₙₑₜ = ma = " + mass + "kg x " + acceleration + "m/s² = " + frictionalForce + "N <br>"
                + "Fₖ = μₖFₙ <br>"
                + "= μₖF₉ <br>"
                + "= μₖmg <br>"
                + "= (" + ckf + ")(" + mass + "kg)(9.8N/kg) <br>"
                + "= " + frictionalForce + "N <br>"
                + "Fₐ = Fₙₑₜ - Fₖ = " + netForce + "N - " + frictionalForce + "N = " + appliedForce + "N <br><br>"
                + "Therefore, the applied force is " + appliedForce + "N";

        setProblem(problem);
        setFbd(frictionalForce, appliedForce, normalForce, gravitationalForce);
        setAnswer(appliedForce);
        setFullSolution(fullSolution);
    }
}
